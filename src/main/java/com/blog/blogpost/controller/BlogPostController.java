package com.blog.blogpost.controller;

import com.blog.blogpost.model.BlogPost;
import com.blog.blogpost.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @PostMapping
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost) {
        return blogPostService.createBlogPost(blogPost);
    }

    @GetMapping("/{id}")
    public BlogPost getBlogPostById(@PathVariable Long id) {
        return blogPostService.getBlogPostById(id);
    }

    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable Long id, @RequestBody BlogPost updatedBlogPost) {
        return blogPostService.updateBlogPost(id, updatedBlogPost);
    }

    @DeleteMapping("/{id}")
    public List<BlogPost> deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return blogPostService.getAllBlogPosts();
    }
}