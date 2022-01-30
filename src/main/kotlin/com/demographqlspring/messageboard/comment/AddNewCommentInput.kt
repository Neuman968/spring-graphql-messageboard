package com.demographqlspring.messageboard.comment

data class AddNewCommentInput(
    val postId: Int,
    val text: String,
)