package com.zibog.blog.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var title: String? = null
    var anons: String? = null
    var fullText: String? = null
    val views: Int? = null
}