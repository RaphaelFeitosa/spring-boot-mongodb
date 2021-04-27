package com.springmongodb.springbootmongodb.resources;

import com.springmongodb.springbootmongodb.domain.User;
import com.springmongodb.springbootmongodb.dto.UserDTO;
import com.springmongodb.springbootmongodb.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

        List<User> list = userService.findAll();
        List<UserDTO> listDto = list.stream().map( x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
