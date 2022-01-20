package com.demographqlspring.messageboard.comment

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "comment")
class Comment {

    constructor() {

    }

    constructor(id: Int, postId: Int, authorUserId: Int, text: String) {
        this.id = id
        this.postId = postId
        this.authorUserId = authorUserId
        this.text = text
    }

    @Id
    var id: Int = 0

    @Column
    var postId: Int = 0

    @Column
    var authorUserId: Int = 0

    @Column
    lateinit var text: String
}