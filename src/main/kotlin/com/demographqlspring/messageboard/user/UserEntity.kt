package com.demographqlspring.messageboard.user

import javax.persistence.*

@Entity
@Table(name = "user_entity")
class UserEntity {

    constructor() {}

    constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }

    @Id
    @GeneratedValue
    var id: Int = 0

    @Column
    lateinit var name: String


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        return result
    }


}