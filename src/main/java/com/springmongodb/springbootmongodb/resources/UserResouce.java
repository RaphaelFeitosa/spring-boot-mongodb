package com.springmongodb.springbootmongodb.resources;

import com.springmongodb.springbootmongodb.domain.User;
import com.springmongodb.springbootmongodb.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResouce {

    private final UserService userService;

    public UserResouce(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll(){

        List<User> list = userService.findAll();

        return ResponseEntity.ok().body(list);
    }
}
