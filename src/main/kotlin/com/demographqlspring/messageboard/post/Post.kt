package com.demographqlspring.messageboard.post

import com.demographqlspring.messageboard.user.UserEntity
import javax.persistence.*

@Entity
@Table(name = "post")
open class Post {

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

//    @ManyToOne
//    @JoinColumn(name = "authorUserId", referencedColumnName = "id", insertable = false, updatable = false)
    @Transient
    lateinit var authorUser: UserEntity


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (authorUserId != other.authorUserId) return false
        if (text != other.text) return false
        if (authorUser != other.authorUser) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + authorUserId
        result = 31 * result + text.hashCode()
        result = 31 * result + authorUser.hashCode()
        return result
    }


}