package fr.backend.security

import com.nimbusds.jose.jwk.JWK
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.handler.HandlerMappingIntrospector


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

    @Autowired
    lateinit var rsaKeyConfigProperties: RsaKeyConfigProperties

    @Autowired
    lateinit var userDetailsService: JpaUserDetailsService


    @Bean
    fun authManager(): AuthenticationManager {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return ProviderManager(authProvider)
    }


    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity, introspector: HandlerMappingIntrospector?): SecurityFilterChain {
        return http
            .csrf { csrf: CsrfConfigurer<HttpSecurity> ->
                csrf.disable()
            }
            .cors { cors: CorsConfigurer<HttpSecurity> -> cors.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/error/**").permitAll()
                auth.requestMatchers("/api/auth/**").permitAll()
                auth.requestMatchers("/**").permitAll()
                auth.requestMatchers("/h2-console/**").permitAll()
                auth.anyRequest().authenticated()
            }.headers { headers ->
                headers.frameOptions { it.sameOrigin() }
            }
            .sessionManagement { s: SessionManagementConfigurer<HttpSecurity?> ->
                s.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .oauth2ResourceServer { oauth2: OAuth2ResourceServerConfigurer<HttpSecurity?> ->
                oauth2.jwt { jwt ->
                    jwt.decoder(
                        jwtDecoder()
                    )
                }
            }
            .userDetailsService(userDetailsService)
            .httpBasic(Customizer.withDefaults())
            .build()
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(rsaKeyConfigProperties.publicKey).build()
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk: JWK =
            RSAKey.Builder(rsaKeyConfigProperties.publicKey).privateKey(rsaKeyConfigProperties.privateKey).build()

        val jwks: JWKSource<SecurityContext> = ImmutableJWKSet(JWKSet(jwk))
        return NimbusJwtEncoder(jwks)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(SecurityConfig::class.java)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("localhost:3000")
        configuration.allowedMethods = listOf("GET", "FETCH", "POST", "PUT", "DELETE")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}