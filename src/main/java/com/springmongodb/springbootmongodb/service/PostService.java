package com.springmongodb.springbootmongodb.service;
import com.springmongodb.springbootmongodb.domain.Post;
import com.springmongodb.springbootmongodb.domain.User;
import com.springmongodb.springbootmongodb.repository.PostRepository;
import com.springmongodb.springbootmongodb.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    public Post findById(String id) {
            Optional<Post> post = this.postRepository.findById(id);
            return post.orElseThrow(() -> new ObjectNotFoundException("Post not Found"));
    }

    public List<Post> findByTitle(String text) {
      return this.postRepository.findByTitle(text);

    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);

        return this.postRepository.fullSearch(text, minDate, maxDate);

    }
    
}
