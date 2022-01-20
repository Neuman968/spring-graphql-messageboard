package com.demographqlspring.messageboard.user

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class UserInitializer(val userRepository: UserRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        userRepository.saveAll(
            listOf(
                UserEntity(1, "Author"),
                UserEntity(2, "Spectator-1"),
                UserEntity(3, "Spectator-2"),
                UserEntity(4, "Spectator-3"),
            )
        )
    }

}