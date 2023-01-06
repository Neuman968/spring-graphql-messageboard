package com.demographqlspring.messageboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageboardApplication

fun main(args: Array<String>) {
	runApplication<MessageboardApplication>(*args)
}