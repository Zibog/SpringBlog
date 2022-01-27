package com.zibog.blog.controllers

import com.zibog.blog.models.Post
import com.zibog.blog.repo.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*
import kotlin.collections.ArrayList

@Controller
class BlogController(
    @Autowired
    private val postRepository: PostRepository
) {
    @GetMapping("/blog")
    suspend fun blogMain(model: Model): String {
        /*val posts = runBlocking {
                withContext(Dispatchers.IO) {
                    postRepository.findAll()
            }
        }*/
        val posts = withContext(Dispatchers.IO) {
            postRepository.findAll()
        }
        model.addAttribute("posts", posts)
        return "blog-main"
    }

    @GetMapping("/blog/add")
    fun blogAdd(model: Model): String {
        return "blog-add"
    }

    @PostMapping("/blog/add")
    suspend fun blogPostAdd(
        @RequestParam title: String,
        @RequestParam anons: String,
        @RequestParam fullText: String,
        model: Model
    ): String {
        val post = Post(title, anons, fullText)
        withContext(Dispatchers.IO) {
            postRepository.save(post)
        }
        return "redirect:/blog"
    }

    @GetMapping("/blog/{id}")
    suspend fun blogDetails(@PathVariable("id") id: Long, model: Model): String {
        var post: Optional<Post> = Optional.empty()
        withContext(Dispatchers.IO) {
            if (!postRepository.existsById(id)) {
                return@withContext "redirect:/blog"
            }

            post = postRepository.findById(id)
        }
        val res = ArrayList<Post>()
        post.ifPresent(res::add)
        // Increment views counter
        res.forEach { elem -> ++elem.views }
        model.addAttribute("post", res)
        // Update DB
        withContext(Dispatchers.IO) {
            postRepository.saveAll(res)
        }
        return "blog-details"
    }

    @GetMapping("/blog/{id}/edit")
    suspend fun blogEdit(@PathVariable("id") id: Long, model: Model): String {
        var post: Optional<Post> = Optional.empty()
        withContext(Dispatchers.IO) {
            if (!postRepository.existsById(id)) {
                return@withContext "redirect:/blog"
            }

            post = postRepository.findById(id)
        }
        val res = ArrayList<Post>()
        post.ifPresent(res::add)
        model.addAttribute("post", res)
        return "blog-edit"
    }

    @PostMapping("/blog/{id}/edit")
    suspend fun blogPostEdit(
        @PathVariable("id") id: Long,
        @RequestParam title: String,
        @RequestParam anons: String,
        @RequestParam fullText: String,
        model: Model
    ): String {
        withContext(Dispatchers.IO) {
            val post = postRepository.findById(id).orElseThrow()
            post.title = title
            post.anons = anons
            post.fullText = fullText
            postRepository.save(post)
        }

        return "redirect:/blog/{id}"
    }

    @PostMapping("/blog/{id}/delete")
    suspend fun blogPostDelete(
        @PathVariable("id") id: Long,
        model: Model
    ): String {
        withContext(Dispatchers.IO) {
            val post = postRepository.findById(id).orElseThrow()
            postRepository.delete(post)
        }

        return "redirect:/blog"
    }
}