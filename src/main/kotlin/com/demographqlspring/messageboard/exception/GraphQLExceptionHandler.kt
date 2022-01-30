package com.demographqlspring.messageboard.exception

import graphql.GraphQLError
import graphql.GraphQLException
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import graphql.schema.DataFetchingEnvironment
import org.slf4j.LoggerFactory
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler

@Component
class GraphQLExceptionHandler : DataFetcherExceptionResolverAdapter() {

    val logger = LoggerFactory.getLogger(GraphQLExceptionHandler::class.java)

    override fun resolveToMultipleErrors(ex: Throwable, env: DataFetchingEnvironment): List<GraphQLError> = when (ex) {
        is AppException -> ex.errors
        else -> super.resolveToMultipleErrors(ex, env) ?: listOf()
    }

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? = when (ex) {
        is AppException -> ex.errors.firstOrNull()
        else -> super.resolveToSingleError(ex, env)
    }
}