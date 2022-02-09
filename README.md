# Spring-Graphql JPA Sample Application

![Kotlin](https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![build status](https://github.com/Neuman968/spring-graphql-messageboard/actions/workflows/ci.yml/badge.svg) [![PR's Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat)](http://makeapullrequest.com)

 This project aims to be an example project using the latest [spring-graphql](https://github.com/spring-projects/spring-graphql) libraries with a modest JPA data model.
 
## Running the project

running the project is simple and does not require any database or external dependencies other than Java 11
 as the DB is entirely using H2

To run, simply run 

```bash
./gradlew bootRun
```

GraphQLi client is available at 

`http://localhost:8811/graphiql?path=/graphql`

The application server will run on post **8811** 
By default, query logging and Hibernate statistics are turned on in order to visualize some of the performance benefits that
can be found using Data Loaders/Data Fetchers. This also demonstrates how N+1 problems can be avoided.

The application is a simple message board, with user, post, and comment entities.
The graphql schema can be found in _src/resources/graphql/schema.graphqls_
The most complicated query, is getPostComments, where the post, comments, and authors can all be returned in one query.

```graphql
query {
    getPostComments(postId: 40) {
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
```

## Example Request using curl

```bash
curl http://localhost:8811/graphql -H 'Content-Type: application/json' -d '{"query": "query { getUsers { id name } }"}'
```