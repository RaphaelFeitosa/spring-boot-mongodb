package com.springmongodb.springbootmongodb.resources;

import com.springmongodb.springbootmongodb.domain.Post;
import com.springmongodb.springbootmongodb.domain.User;
import com.springmongodb.springbootmongodb.dto.UserDTO;
import com.springmongodb.springbootmongodb.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/posts")
public class PostResouce {

    private final PostService postService;

    public PostResouce(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findAll(){

        List<Post> list = this.postService.findAll();
       // List<Post> posts = list.stream().map( x -> new Post(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id){

        Post post = this.postService.findById(id);
        return ResponseEntity.ok().body(post);
    }



}
