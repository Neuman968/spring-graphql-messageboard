package com.demographqlspring.messageboard

import com.demographqlspring.messageboard.comment.AddNewCommentInput
import com.demographqlspring.messageboard.comment.Comment
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.graphql.test.tester.WebGraphQlTester
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureHttpGraphQlTester
class CommentControllerTest {
    @Autowired
    lateinit var context: WebApplicationContext

    @Autowired
    lateinit var graphqlTester: WebGraphQlTester

    @Test
    fun `test get comments expecting 13 comments returned`() {
        graphqlTester.document(
            """
            query {
                getComments {
                    id
                    text
                    authorUser {
                        id
                        name
                    }
                    post {
                        id
                        text
                        authorUser {
                            id
                            name
                        }
                    }
                }
            }
        """.trimIndent()
        )
            .execute()
            .path("getComments")
            .entityList(Comment::class.java)
            .hasSizeGreaterThan(13)
    }

    @Test
    fun `test get comment by id expecting 1 comment returned`() {
        val comment = graphqlTester.documentName("getComment")
            .variable("id", 180)
            .execute()
            .path("getComment")
            .entity(Comment::class.java)
            .get()

        assert(comment.text == "To the moon!") { "comment was ${comment.text}" }
        assert(comment.authorUser.name == "Author") { "Comment author was ${comment.authorUser.name}" }

        assert(comment.post.text == "Just emptied my retirement into Dodgecoin") {
            "Comment post text was ${comment.post.text}"
        }

        assert(comment.post.authorUser.name == "Riley") {
            "Post Author was ${comment.post.authorUser.name}"
        }
    }

    @Test
    fun `test get post comment expecting 3 post comments returned`() {
        graphqlTester.documentName("getPostComments")
            .variable("postId", 20)
            .execute()
            .path("getPostComments")
            .entityList(Comment::class.java)
            .hasSize(3)
    }

    @Test
    fun `test add post comment expecting comment added`() {
        val addedComment = graphqlTester.documentName("addComment")
            .variable("add", AddNewCommentInput(postId = 10, "Wow very cool!"))
            .execute()
            .path("addComment")
            .entity(Comment::class.java)
            .get()

        assert(addedComment.text == "Wow very cool!") {"Added comment was ${addedComment.text}"}
        assert(addedComment.post.id == 10) { "Post Id was ${addedComment.post.id}" }
    }

    @Test
    fun `test add post comment with post not found expecting comment not added`() {
        val errors = graphqlTester.documentName("addComment")
            .variable("add", AddNewCommentInput(postId = 1000, "Wow very cool!"))
            .execute()
            .errors().expect { it.message == "Post: 1000 was not found" }
    }

}