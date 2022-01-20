package com.demographqlspring.messageboard.comment

import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.graphql.data.GraphQlRepository

@GraphQlRepository
interface CommentRepository : CrudRepository<Comment, Int>, QuerydslPredicateExecutor<Comment>