package com.blog.blogpost.repository;

import com.blog.blogpost.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {}
