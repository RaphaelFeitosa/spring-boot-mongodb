package com.springmongodb.springbootmongodb.resources;

import com.springmongodb.springbootmongodb.domain.Post;
import com.springmongodb.springbootmongodb.domain.User;
import com.springmongodb.springbootmongodb.dto.UserDTO;
import com.springmongodb.springbootmongodb.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResouce {

    private final UserService userService;

    public UserResouce(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){

        List<User> list = this.userService.findAll();
        List<UserDTO> listDto = list.stream().map( x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){

        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDto){
        User user = this.userService.fromDTO(userDto);
        user = this.userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
       this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO userDto, @PathVariable String id){
        User user = this.userService.fromDTO(userDto);
        user.setId(id);
        this.userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){

        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user.getPosts());
    }

}
