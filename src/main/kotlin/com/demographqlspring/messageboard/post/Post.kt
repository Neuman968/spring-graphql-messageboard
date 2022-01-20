package com.demographqlspring.messageboard.post

import com.demographqlspring.messageboard.user.UserEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "post")
class Post {

    constructor() {}

    constructor(id: Int, authorUserId: Int, text: String) {
        this.id = id
        this.authorUserId = authorUserId
        this.text = text
    }

    @Id
    var id: Int = 0

    @Column
    var authorUserId: Int = 0

    @Column
    lateinit var text: String

}