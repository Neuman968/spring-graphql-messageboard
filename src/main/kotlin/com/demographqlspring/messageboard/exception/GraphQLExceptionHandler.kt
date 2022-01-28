package com.demographqlspring.messageboard.exception

import graphql.GraphQLException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler

@Component
class GraphQLExceptionHandler {

    val logger = LoggerFactory.getLogger(GraphQLExceptionHandler::class.java)

    @ExceptionHandler(GraphQLException::class)
    fun handle(e: GraphQLException) {
        logger.error("GQL Error: ", e)
    }

    @ExceptionHandler(Exception::class)
    fun handle(e: Exception) {
        logger.error("Exception: ", e)
    }
}