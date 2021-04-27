package com.springmongodb.springbootmongodb.config;

import com.springmongodb.springbootmongodb.domain.Post;
import com.springmongodb.springbootmongodb.domain.User;
import com.springmongodb.springbootmongodb.repository.PostRepository;
import com.springmongodb.springbootmongodb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation  implements CommandLineRunner {

    private final UserRepository  userRepository;
    private final PostRepository  postRepository;


    public Instantiation(UserRepository  userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        this.userRepository.deleteAll();
        this.postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, sdf.parse("21/03/2021"), "Partiu viagem",
                "Vou viajar para São Paulo!", maria);
        Post post2 = new Post(null, sdf.parse("21/03/2021"), "Bom dia",
                "Acordei feliz hoje!", maria);

        this.userRepository.saveAll(Arrays.asList(maria, alex, bob));
        this.postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
