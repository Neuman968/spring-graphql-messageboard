package com.demographqlspring.messageboard.exception

import graphql.ErrorClassification
import graphql.GraphQLError
import graphql.language.SourceLocation
import org.springframework.graphql.execution.ErrorType
import javax.xml.transform.Source

data class AppError(
    private val message: String,
    private val locations: List<SourceLocation> = listOf(),
    private val errorType: ErrorClassification = ErrorType.BAD_REQUEST,
): GraphQLError {
    override fun getMessage(): String = this.message

    override fun getLocations(): List<SourceLocation> = this.locations

    override fun getErrorType(): ErrorClassification = this.errorType

}