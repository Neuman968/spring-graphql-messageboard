package com.demographqlspring.messageboard.post

import com.demographqlspring.messageboard.comment.Comment
import com.demographqlspring.messageboard.comment.CommentRepository
import com.demographqlspring.messageboard.comment.QComment
import com.demographqlspring.messageboard.user.UserEntity
import com.demographqlspring.messageboard.user.UserRepository
import org.dataloader.DataLoader
import org.springframework.data.domain.Pageable
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.graphql.execution.BatchLoaderRegistry
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

@Controller
open class PostController(
    val batchLoaderRegistry: BatchLoaderRegistry,
    val userRepository: UserRepository,
    val postRepository: PostRepository,
    val commentRepository: CommentRepository,
) {
    init {
        batchLoaderRegistry.forTypePair(Int::class.java, UserEntity::class.java).registerMappedBatchLoader { ids, _ ->
            Mono.just(userRepository.findAllByIdIn(ids).associateBy { it.id })
        }
    }

    @QueryMapping
    fun getPosts(): List<Post> = postRepository.findAll().toList()

    @MutationMapping
    fun addPost(@Argument add: AddNewPostInput): Post = postRepository.save(Post().apply {
        this.text = add.text ?: ""
        // We assume you are logged in as user 1 :)
        this.authorUserId = 1
    })

    @SchemaMapping
    fun authorUser(post: Post, loader: DataLoader<Int, UserEntity>): CompletableFuture<UserEntity>? =
        loader.load(post.authorUserId)

    @SchemaMapping
    fun comments(post: Post, @Argument limit: Int): List<Comment> = commentRepository.findAll(
            QComment.comment.postId.eq(post.id),
            Pageable.ofSize(limit),
        ).toList()
}