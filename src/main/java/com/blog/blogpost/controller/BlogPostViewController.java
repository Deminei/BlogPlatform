package com.blog.blogpost.controller;

import com.blog.blogpost.exception.ResourceNotFoundException;
import com.blog.blogpost.model.BlogPost;
import com.blog.blogpost.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class BlogPostViewController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("/posts")
    public String listPosts(Model model) {
        List<BlogPost> posts = blogPostService.getAllBlogPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }


    @GetMapping("/posts/new")
    public String showNewPostForm(Model model) {
        model.addAttribute("post",
                new BlogPost());
        return "create-post";
    }

    @PostMapping("/posts")
    public String savePost(@ModelAttribute BlogPost post) {
        blogPostService.createBlogPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditPostForm(@PathVariable Long id, Model model) {
        BlogPost existingPost = blogPostService.getBlogPostById(id);
        model.addAttribute("post", existingPost);
        return "edit-post";
    }

    @PostMapping("/posts/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute BlogPost post) {
        blogPostService.updateBlogPost(id, post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id, @ModelAttribute BlogPost post) {
        blogPostService.deleteBlogPost(id);
        return "redirect:/posts";
    }
}


