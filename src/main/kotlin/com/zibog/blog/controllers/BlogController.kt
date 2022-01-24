package com.zibog.blog.controllers

import com.zibog.blog.repo.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

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
}