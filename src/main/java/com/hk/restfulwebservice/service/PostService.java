package com.hk.restfulwebservice.service;

import com.hk.restfulwebservice.constants.Constants;
import com.hk.restfulwebservice.exception.types.UserNotFoundException;
import com.hk.restfulwebservice.model.Post;
import com.hk.restfulwebservice.model.User;
import com.hk.restfulwebservice.repository.PostRepository;
import com.hk.restfulwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getPostsByUser(Integer userId) {

        Optional<User> retrievedUserOptional = userRepository.findById(userId);

        if (!retrievedUserOptional.isPresent())
            throw new UserNotFoundException(Constants.USER_NOT_FOUND);

        return retrievedUserOptional.get().getPosts();
    }

    public Post createPost(Integer userId, Post post) {

        Optional<User> retrievedUserOptional = userRepository.findById(userId);

        if (!retrievedUserOptional.isPresent())
            throw new UserNotFoundException(Constants.USER_NOT_FOUND);

        User retrievedUser = retrievedUserOptional.get();

        post.setUser(retrievedUser);

        return postRepository.save(post);
    }

}
