package com.demographqlspring.messageboard

import com.demographqlspring.messageboard.user.UserEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.graphql.test.tester.WebGraphQlTester
import org.springframework.test.web.servlet.client.MockMvcWebTestClient
import org.springframework.web.context.WebApplicationContext


@SpringBootTest
class UserControllerTest {

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
    fun `test getUsers expecting 4 test users returned`() {
        graphqlTester.query(
            """
            query {
              getUsers {
                id
                name
              }
            }
        """.trimIndent()
        )
            .execute()
            .path("getUsers")
            .entityList(UserEntity::class.java)
            .hasSize(4)
    }

    @Test
    fun `test getUser by id passing id for user expecting user returned`() {
        graphqlTester.query(
            """
                query {
                    getUser(id: 4) {
                        id
                        name
                    }
                }
            """.trimIndent()
        ).execute()
            .path("getUser")
            .entity(UserEntity::class.java)
            .isEqualTo(UserEntity(4, "Jamie"))
    }

    @Test
    fun `test getUser by id passing not found id expecting not found error`() {
        graphqlTester.query(
            """
                query {
                    getUser(id: 99) {
                        id
                        name
                    }
                }
            """.trimIndent()
        ).execute()
            .path("getUsers")
            .valueDoesNotExist()
    }
}