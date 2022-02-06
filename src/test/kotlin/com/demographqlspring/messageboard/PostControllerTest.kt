package com.demographqlspring.messageboard

import com.demographqlspring.messageboard.post.Post
import com.demographqlspring.messageboard.post.PostRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.graphql.test.tester.WebGraphQlTester
import org.springframework.test.web.servlet.client.MockMvcWebTestClient
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
class PostControllerTest {

    @Autowired
    lateinit var context: WebApplicationContext

    lateinit var graphqlTester: WebGraphQlTester

    @BeforeEach
    fun setup() {
        val client = MockMvcWebTestClient.bindToApplicationContext(context)
            .configureClient()
            .baseUrl("/graphql")
            .build()

        graphqlTester = WebGraphQlTester.builder(client).build()
    }

    @Test
    fun `test getPosts expecting 4 posts returned`() {
        graphqlTester.query(
            """
            query {
                getPosts {
                    id
                    text
                    authorUser {
                        id
                        name
                    }
                }
            }
        """.trimIndent()
        )
            .execute()
            .path("getPosts")
            .entityList(Post::class.java)
            .hasSizeGreaterThan(3)
    }

    @Test
    fun `test getPosts expecting post with joins returned`() {
        val postList = graphqlTester.query(
            """
            query {
                getPosts {
                    id
                    text
                    authorUser {
                        id
                        name
                    }
                    comments(limit: 5) {
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
        ).execute()
            .path("getPosts")
            .entityList(Post::class.java)
            .get()

        assert (postList.size > 3) { "There were not at least 4 posts returned" }

        // Assert against post 40
        val post = postList.first { it.id == 40 }

        assert(post.text == "Just emptied my retirement into Dodgecoin") {
            "text was not correct was ${post.text}"
        }

        assert(post.authorUser.id == 3) { "Author user was not 3 was ${post.authorUser.id}" }
        assert(post.authorUser.name == "Riley") { "Name was not correct was ${post.authorUser.name}" }

        assert(post.comments.size == 5) { "There were not exactly 5 comments returned ${post.comments.size}" }
    }

    @Test
    fun `test addPost expecting new post added`() {
        val savedPostId = graphqlTester.query(
            """
            mutation {
                addPost(add: { text: "Tests are goood!" }) {
                    id
                    text
                }
            }
        """.trimIndent()
        )
            .execute()
            .path("addPost.id")
            .entity(Int::class.java)
            .get()

        val savedPost = context.getBean(PostRepository::class.java).findByIdOrNull(savedPostId)
        assert(savedPost?.text == "Tests are goood!") {
            "saved post had incorrect text, was ${savedPost?.text}"
        }
    }

    @Test
    fun `test get users post passing user expecting posts returned`() {
        val posts = graphqlTester.queryName("getUserPosts")
            .variable("userId", 1)
            .execute()
            .path("getUserPosts")
            .entityList(Post::class.java)
            .get()
        assert(posts.size == 2) { "There were not 2 posts ${posts.size}" }

        val post1 = posts.firstOrNull { it.id == 10 }
        val post2 = posts.firstOrNull { it.id == 20 }

        assert(post1 != null) { "Post 10 was not returned" }
        assert(post2 != null) { "Post 20 was not returned" }
        assert(post1?.comments?.size!! >= 3) { "Post 10 did not have 3 comments" }
        assert(post2?.comments?.size!! >= 3) { "Post 20 did not have 3 comments" }

    }
}