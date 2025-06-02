package com.uade.tpo.E_Commerce.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.uade.tpo.E_Commerce.entity.Role;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {})
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/basic_users/").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE,"/basic_users/{id}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/buyer_user").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/buyer_user/{id}").hasAnyAuthority(Role.BUYER.name())
                        .requestMatchers(HttpMethod.GET,"/buyer_user/{id}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST,"/buyer_user").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/buyer_user").hasAnyAuthority(Role.BUYER.name())
                        .requestMatchers(HttpMethod.DELETE,"/buyer_user/{id}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE,"/buyer_user/{id}").hasAnyAuthority(Role.BUYER.name())
                        .requestMatchers(HttpMethod.GET,"/categories").permitAll()
                        .requestMatchers(HttpMethod.GET,"/categories/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/categories").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE,"/categories/{id_category}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/delivery-status/{id_delivery}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/delivery-status").hasAnyAuthority(Role.BUYER.name())
                        .requestMatchers(HttpMethod.PUT,"/delivery-status/{id_delivery}").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.DELETE,"/delivery-status/{id_delivery}").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.DELETE,"/delivery-status/{id_delivery}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,
                                "/product",
                                "/product/productById/{id}",
                                "/product/byCategoryid/{id}",
                                "/product/bySubCategoryid/{id}"
                        ).permitAll()
                        //.requestMatchers(HttpMethod.POST,"/product").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.POST,"/product").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.PUT,"/product").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.DELETE,"/product/{id}").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.DELETE,"/product/{id}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/review/{id_product}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/review").hasAnyAuthority(Role.BUYER.name())
                        .requestMatchers(HttpMethod.GET,"/roles").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/roles/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/roles").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT,"/roles").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE,"/roles/{id}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/roles/user/{userId}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/roles/assign/{roleId}/{userId}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/roles/user/{userId}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/shops/all/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/shops/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/shops").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.PUT,"/shops").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.DELETE,"/shops/{id}").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.GET,"/stock/getLocation/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/stock").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.POST,"/stock/shop_stock").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.PUT,"/stock").permitAll()
                        .requestMatchers(HttpMethod.GET,"/stock/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/stock/stockWarning").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.GET, "/sale/{id}").hasAnyAuthority(Role.BUYER.name())
                        .requestMatchers(HttpMethod.POST, "/sale").hasAnyAuthority(Role.BUYER.name())
                        .requestMatchers(HttpMethod.PUT, "/sale/{id}").hasAnyAuthority(Role.BUYER.name())
                        .requestMatchers(HttpMethod.DELETE, "/sale/{id}").hasAnyAuthority(Role.BUYER.name())
                        .requestMatchers(HttpMethod.DELETE, "/sale/{id}").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.GET, "/seller_user").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/seller_user/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/seller_user").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/seller_user").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.DELETE, "/seller_user/{id}").hasAnyAuthority(Role.SELLER.name())
                        .requestMatchers(HttpMethod.GET, "/sub_categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sub_categories/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/sub_categories").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/sub_categories/{id}").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }


}
