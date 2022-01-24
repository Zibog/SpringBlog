package com.zibog.blog.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BlogController {
    @GetMapping("/blog")
    fun blogMain(model: Model): String {
        return "blog-main"
    }
}