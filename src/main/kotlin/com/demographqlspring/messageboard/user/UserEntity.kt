package com.demographqlspring.messageboard.user

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class UserEntity {

    constructor() {}

    constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }

    @Id
    var id: Int = 0

    @Column
    lateinit var name: String

}