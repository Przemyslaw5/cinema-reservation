package pl.theliver.cinemabackend.config

import com.fasterxml.classmate.TypeResolver
import org.hibernate.usertype.DynamicParameterizedType.ParameterType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.RequestParameterBuilder
import springfox.documentation.schema.AlternateTypeRules
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        val parameterBuilder = RequestParameterBuilder()
        val typeResolver = TypeResolver()
        return Docket(DocumentationType.SWAGGER_2)
            .alternateTypeRules(
                AlternateTypeRules.newRule(
                    typeResolver.resolve(MutableList::class.java, LocalTime::class.java),
                    typeResolver.resolve(MutableList::class.java, String::class.java), Ordered.HIGHEST_PRECEDENCE
                ),
                AlternateTypeRules.newRule(
                    typeResolver.resolve(MutableList::class.java, LocalDate::class.java),
                    typeResolver.resolve(MutableList::class.java, Date::class.java), Ordered.HIGHEST_PRECEDENCE
                ),
                AlternateTypeRules.newRule(
                    typeResolver.resolve(MutableList::class.java, LocalDateTime::class.java),
                    typeResolver.resolve(MutableList::class.java, Date::class.java), Ordered.HIGHEST_PRECEDENCE
                )
            )
            .directModelSubstitute(LocalDateTime::class.java, Date::class.java)
            .directModelSubstitute(LocalDate::class.java, Date::class.java)
            .directModelSubstitute(LocalTime::class.java, String::class.java)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
    }
}