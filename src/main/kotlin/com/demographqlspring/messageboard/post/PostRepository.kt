package com.demographqlspring.messageboard.post

import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.graphql.data.GraphQlRepository

@GraphQlRepository
interface PostRepository : CrudRepository<Post, Int>, QuerydslPredicateExecutor<Post>