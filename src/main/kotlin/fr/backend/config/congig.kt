package fr.backend.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://srv2-vm-2121.sts-sio-caen.info")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}
