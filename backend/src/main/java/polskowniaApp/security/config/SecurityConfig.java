package polskowniaApp.security.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import polskowniaApp.security.filter.CsrfCookieFilter;
import polskowniaApp.security.filter.JwtAuthFilter;

import java.util.Collections;

@EnableWebSecurity
@Configuration
class SecurityConfig
{
    private final JwtAuthFilter authFilter;

    SecurityConfig(final JwtAuthFilter authFilter)
    {
        this.authFilter = authFilter;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
    {
        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http.securityContext(context -> context.requireExplicitSave(false))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource()
                {
                    @Override
                    public CorsConfiguration getCorsConfiguration(final HttpServletRequest request)
                    {
                        var config = new CorsConfiguration();

                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Collections.singletonList(HttpHeaders.CONTENT_DISPOSITION));
                        config.setMaxAge(3600L);

                        return config;
                    }
                }))
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers(
                        "/register", "/authenticate", "/dashboard"
                                , "/createCourse", "/myCourses/**", "/allCourses"
                                , "/shop", "/getShopItemCategories", "/addShopItem/**", "/shopItem/**", "/getDiscountCodes"
                                , "/sendMail", "/getStudents", "/generateDiscountCode", "/getDiscount"
                        ,"/restorePassword", "/changePassword", "/downloadFile/**", "/fileManager", "/uploadFile**", "/getFiles"
                                , "/createOrder", "/orders", "/myShelf", "/allOrders", "/order/**")
//                        , "/addShopItem/**", "/getShopItem/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(this.authFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/register", "/authenticate"
                                , "/shop", "/shopItem/**", "/getShopItemCategories"
                                , "/sendMail", "/getDiscount", "/restorePassword").permitAll()
//                        .requestMatchers("/register", "/authenticate", "/shop", "/getShopItemCategories").permitAll()
                        .requestMatchers("/dashboard"
                                , "/createCourse", "/myCourses/**", "/allCourses"
                                , "/addShopItem/**"
                                , "/getDiscountCodes", "/getStudents", "/generateDiscountCode", "/changePassword", "/downloadFile/**", "/fileManager", "/uploadFile**"
                                , "/getFiles", "/createOrder", "/orders", "/myShelf", "/allOrders", "/order/**").authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}