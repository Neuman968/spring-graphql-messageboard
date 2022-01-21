package com.demographqlspring.messageboard.post

import com.demographqlspring.messageboard.user.UserEntity
import com.demographqlspring.messageboard.user.UserRepository
import org.dataloader.DataLoader
import org.springframework.graphql.data.method.annotation.BatchMapping
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
    val postRepository: PostRepository
) {
    init {
        batchLoaderRegistry.forTypePair(Int::class.java, UserEntity::class.java).registerMappedBatchLoader { ids, _ ->
            Mono.just(userRepository.findAllByIdIn(ids).associateBy { it.id })
        }
    }

    @QueryMapping
    fun getPosts(): List<Post>  {
        return postRepository.findAll().toList()
    }

    @SchemaMapping
    fun authorUser(post: Post, loader: DataLoader<Int, UserEntity>): CompletableFuture<UserEntity>? =
        loader.load(post.authorUserId)

    // todo does not work for some mysterious reason...
//    @BatchMapping
//    open fun authorUser(posts: List<Post>): Mono<Map<Post, UserEntity>> {
//        val authors = userRepository.findAllByIdIn(posts.map { it.authorUserId }.toSet())
//        return Mono.just(posts.associateWith {
//            authors.first { author -> it.authorUserId == author.id }
//        })
//    }
}