package com.demographqlspring.messageboard.comment

import com.demographqlspring.messageboard.post.Post
import com.demographqlspring.messageboard.post.PostRepository
import com.demographqlspring.messageboard.user.UserEntity
import org.dataloader.DataLoader
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.graphql.execution.BatchLoaderRegistry
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

@Controller
class CommentController(
    val batchLoaderRegistry: BatchLoaderRegistry,
    val postRepository: PostRepository,
    val commentRepository: CommentRepository
) {

    init {
        batchLoaderRegistry.forTypePair(Int::class.java, Post::class.java).registerMappedBatchLoader { ids, _ ->
            Mono.just(postRepository.findAllByIdIn(ids).associateBy { it.id })
        }
    }

    @QueryMapping
    fun getComments(): List<Comment> = commentRepository.findAll().toList()

    @SchemaMapping
    fun post(comment: Comment, loader: DataLoader<Int, Post>): CompletableFuture<Post>? = loader.load(comment.postId)

    @SchemaMapping
    fun authorUser(comment: Comment, loader: DataLoader<Int, UserEntity>): CompletableFuture<UserEntity>? = loader.load(comment.authorUserId)

}