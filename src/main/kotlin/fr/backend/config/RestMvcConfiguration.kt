package fr.backend.config

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Configuration
internal class RestMvcConfiguration {

    @Autowired
    private lateinit var em: EntityManager

    @Bean
    fun repositoryRestConfigurer(): RepositoryRestConfigurer {
        return object : RepositoryRestConfigurer {

            override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration, cors: CorsRegistry) {
                config.exposeIdsFor(
                    *em.metamodel.entities
                        .map { it.javaType }
                        .toTypedArray()
                )
                config.setBasePath("/api")
            }
        }
    }
}
