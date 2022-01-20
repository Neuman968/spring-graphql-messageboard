package com.demographqlspring.messageboard.user

import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.graphql.data.GraphQlRepository

@GraphQlRepository
interface UserRepository : CrudRepository<UserEntity, Int>,
    QuerydslPredicateExecutor<UserEntity>