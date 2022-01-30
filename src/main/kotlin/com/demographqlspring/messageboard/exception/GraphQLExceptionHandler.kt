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
class GraphQLExceptionHandler: DataFetcherExceptionResolverAdapter() {

    val logger = LoggerFactory.getLogger(GraphQLExceptionHandler::class.java)

    override fun resolveToMultipleErrors(ex: Throwable, env: DataFetchingEnvironment): MutableList<GraphQLError>? {
        logger.error("Resolving to error.. ", ex)
        return super.resolveToMultipleErrors(ex, env)
    }

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        logger.error("Resolving to error.. ", ex)
        return super.resolveToSingleError(ex, env)
    }

    //    @ExceptionHandler(GraphQLException::class)
//    fun handle(e: GraphQLException) {
//        logger.error("GQL Error: ", e)
//    }
//
//    @ExceptionHandler(Exception::class)
//    fun handle(e: Exception) {
//        logger.error("Exception: ", e)
//    }

//    override fun onException(handlerParameters: DataFetcherExceptionHandlerParameters): DataFetcherExceptionHandlerResult {
//        logger.error("Got Error In GraphQL Request ${handlerParameters.exception}")
//        TODO("Not yet implemented")
//    }
}