package com.zibog.blog.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("title", "Home page")
        return "home"
    }

    @GetMapping("/about")
    fun about(model: Model): String {
        model.addAttribute("title", "About us")
        return "about"
    }
}