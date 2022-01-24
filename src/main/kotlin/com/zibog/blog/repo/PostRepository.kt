package com.zibog.blog.repo

import com.zibog.blog.models.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository: CrudRepository<Post, Long>