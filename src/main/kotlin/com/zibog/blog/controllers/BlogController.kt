package com.zibog.blog.controllers

import com.zibog.blog.models.Post
import com.zibog.blog.repo.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class BlogController(
    @Autowired
    private val postRepository: PostRepository
) {
    @GetMapping("/blog")
    fun blogMain(model: Model): String {
        val posts = postRepository.findAll()
        model.addAttribute("posts", posts)
        return "blog-main"
    }

    @GetMapping("/blog/add")
    fun blogAdd(model: Model): String {
        return "blog-add"
    }

    @PostMapping("/blog/add")
    fun blogPostAdd(
        @RequestParam title: String,
        @RequestParam anons: String,
        @RequestParam fullText: String,
        model: Model
    ): String {
        val post = Post(title, anons, fullText)
        postRepository.save(post)
        return "redirect:/blog"
    }
}