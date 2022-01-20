package com.demographqlspring.messageboard

import com.demographqlspring.messageboard.comment.Comment
import com.demographqlspring.messageboard.comment.CommentRepository
import com.demographqlspring.messageboard.post.Post
import com.demographqlspring.messageboard.post.PostRepository
import com.demographqlspring.messageboard.user.UserEntity
import com.demographqlspring.messageboard.user.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataInit(
    val userRepository: UserRepository,
    val postRepository: PostRepository,
    val commentRepository: CommentRepository,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        userRepository.saveAll(
            listOf(
                UserEntity(1, "Author"),
                UserEntity(2, "Pat"),
                UserEntity(3, "Riley"),
                UserEntity(4, "Jamie"),
            )
        )
        postRepository.saveAll(
            listOf(
                Post(10, 1, "New pair of shoes!"),
                Post(20, 1, "I am at the beach!"),
                Post(30, 2, "Me and my boo"),
                Post(40, 3, "Just emptied my retirement into Dodgecoin"),
            )
        )

        commentRepository.saveAll(
            listOf(
                Comment(110, 10, 4, "Love them!"),
                Comment(120, 10, 3, "I have the same pair!"),
                Comment(130, 10, 1, "Thank you for the positive vibes!"),


                Comment(140, 20, 4, "I was there last week."),
                Comment(150, 20, 1, "Wow where did you stay?"),
                Comment(160, 20, 4, "I stayed in an AirBnB off Ocean Avenue."),

                Comment(170, 30, 3, "So beautiful!"),

                Comment(180, 40, 1, "To the moon!"),
                Comment(190, 40, 2, "Diamond hands go brrrr"),
                Comment(200, 40, 3, "How do I buy?"),
                Comment(210, 40, 1, "Go to cryptoscam dot com and give them all your money"),
                Comment(220, 40, 4, "I mean come on scam is literally in the name, please don't use them!"),
                Comment(
                    230,
                    40,
                    1,
                    "I think you need to do your own research, I had a lot of good luck with them, they are legit."
                ),
                Comment(
                    240,
                    40,
                    3,
                    "Wow I just made a ton of money!!"
                )
            )
        )

    }
}