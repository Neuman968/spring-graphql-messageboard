# Spring-Graphql JPA Sample Application

 This project aims to be an example project using spring-graphql with a modest JPA data model.
 
## Running the project

running the project is simple and does not require any database or external dependencies other than Java 11
 as the DB is entirely using H2

To run, simply run 

```bash
./gradlew bootRun
```

The application server will run on post **8811** but can be changed by updating the _server.port_ inside of _src/main/resources/application.properties_
By default, query logging and Hibernate statistics are turned on in order to visualize some of the performance benefits that
can be found using Data Loaders/Data Fetchers. This also demonstrates how N+1 problems can be avoided.

The application is a simple message board, with users, posts, and comments. 
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

