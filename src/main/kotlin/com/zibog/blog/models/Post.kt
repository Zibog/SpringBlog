package com.zibog.blog.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Post(var title: String?, var anons: String?, var fullText: String?) {
    constructor() : this(null, null, null)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var views: Int = 0
}