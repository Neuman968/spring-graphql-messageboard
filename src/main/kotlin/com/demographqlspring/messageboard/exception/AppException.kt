package com.demographqlspring.messageboard.exception

import graphql.GraphQLError
import graphql.GraphQLException
import graphql.GraphqlErrorException
import java.lang.RuntimeException

data class AppException(
    val errors: List<GraphQLError>,
    override val cause: Throwable? = null,
): GraphQLException() {
    companion object {
        fun of(vararg error: AppError) = AppException(errors = error.toList())
    }
}