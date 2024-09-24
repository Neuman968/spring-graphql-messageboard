package com.demographqlspring.messageboard.config

import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@ConfigurationProperties
class CorsConfig : WebMvcConfigurer {

    val logger = LoggerFactory.getLogger(CorsConfig::class.java)

    lateinit var corsOrigins: String

    override fun addCorsMappings(registry: CorsRegistry) {
        logger.info("Cors Configuration is $corsOrigins")
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins(*corsOrigins.split(",").toTypedArray())
    }
}
