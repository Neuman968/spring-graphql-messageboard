package com.demographqlspring.messageboard.comment

import com.demographqlspring.messageboard.post.Post
import com.demographqlspring.messageboard.user.UserEntity
import javax.persistence.*

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

    //    @ManyToOne
//    @JoinColumn(name = "authorUserId", referencedColumnName = "id", insertable = false, updatable = false)
    @Transient
    lateinit var authorUser: UserEntity

//    @ManyToOne
//    @JoinColumn(name = "postId", referencedColumnName = "id", insertable = false, updatable = false)

    @Transient
    lateinit var post: Post

    @Column
    lateinit var text: String
}