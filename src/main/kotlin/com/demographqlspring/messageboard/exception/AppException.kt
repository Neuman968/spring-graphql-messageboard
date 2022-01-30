package com.demographqlspring.messageboard.exception

import java.lang.RuntimeException

data class AppException(
    val errors: List<AppError>,
    override val cause: Throwable? = null,
): RuntimeException(cause) {
    companion object {
        fun of(vararg error: AppError) = AppException(errors = error.toList())
    }
}