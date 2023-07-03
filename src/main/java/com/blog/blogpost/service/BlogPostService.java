package com.blog.blogpost.service;

import com.blog.blogpost.exception.ResourceNotFoundException;
import com.blog.blogpost.model.BlogPost;
import com.blog.blogpost.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    // Create a new blog post
    public BlogPost createBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    // Get all blog posts
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    // Get a blog post by id
    public BlogPost getBlogPostById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost not found with id " + id));
    }

    // Update a blog post
    public BlogPost updateBlogPost(Long id, BlogPost updatedBlogPost) {
        return blogPostRepository.findById(id)
                .map(blogPost -> {
                    blogPost.setTitle(updatedBlogPost.getTitle());
                    blogPost.setContent(updatedBlogPost.getContent());
                    blogPost.setAuthor(updatedBlogPost.getAuthor());
                    return blogPostRepository.save(blogPost);
                })
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost not found with id " + id));
    }

    // Delete a blog post by id
    public List<BlogPost> deleteBlogPost(Long id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("BlogPost not found with id " + id);
        }
       return getAllBlogPosts();
    }

}
