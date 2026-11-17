package com.example.demo.service;

import com.example.demo.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PostService {
    private final Map<Long, Post> postStorage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public PostService() {
        // Sample seed data
        saveSeed(new Post(null, "Getting Started with Spring Boot", "Spring Boot makes building microservices and REST APIs delightful.", 1L));
        saveSeed(new Post(null, "Java 21 Features You Should Know", "Exploring Virtual Threads, Pattern Matching, and Record Patterns.", 2L));
        saveSeed(new Post(null, "Building Scalable Web APIs", "Best practices for designing clean, restful endpoints and error handling.", 1L));
    }

    private void saveSeed(Post post) {
        long id = idGenerator.getAndIncrement();
        post.setId(id);
        postStorage.put(id, post);
    }

    public List<Post> getAllPosts() {
        return new ArrayList<>(postStorage.values());
    }

    public Optional<Post> getPostById(Long id) {
        return Optional.ofNullable(postStorage.get(id));
    }

    public List<Post> getPostsByAuthorId(Long authorId) {
        return postStorage.values().stream()
                .filter(post -> post.getAuthorId().equals(authorId))
                .toList();
    }

    public Post createPost(Post post) {
        long id = idGenerator.getAndIncrement();
        post.setId(id);
        if (post.getCreatedAt() == null) {
            post.setCreatedAt(java.time.LocalDateTime.now());
        }
        postStorage.put(id, post);
        return post;
    }

    public Optional<Post> updatePost(Long id, Post updatedPost) {
        Post existing = postStorage.get(id);
        if (existing == null) {
            return Optional.empty();
        }
        updatedPost.setId(id);
        if (updatedPost.getCreatedAt() == null) {
            updatedPost.setCreatedAt(existing.getCreatedAt());
        }
        postStorage.put(id, updatedPost);
        return Optional.of(updatedPost);
    }

    public boolean deletePost(Long id) {
        return postStorage.remove(id) != null;
    }
}
