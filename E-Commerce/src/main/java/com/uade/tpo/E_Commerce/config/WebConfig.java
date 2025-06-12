package com.uade.tpo.E_Commerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Aquí mapeamos la URL /images/** a la carpeta donde están las imágenes
        registry.addResourceHandler("/images/products/**")
                .addResourceLocations("file:C:/Users/Admin/Documents/carpeta agus/uade/apis/api/E-Commerce/src/main/java" +
                        "/com/uade/tpo/E_Commerce/images/products/")
                .addResourceLocations("file:C:/Users/Tomas/Escritorio/tpo_apis/api/E-Commerce/src/main/java" +
                        "/com/uade/tpo/E_Commerce/images/products/");
        registry.addResourceHandler("/images/buyer_user/**")
                .addResourceLocations("file:C:/Users/Admin/Documents/carpeta agus/uade/apis/api/E-Commerce/src/main/java" +
                        "/com/uade/tpo/E_Commerce/images/buyer_user/")
        .addResourceLocations("file:C:/Users/Tomas/Escritorio/tpo_apis/api/E-Commerce/src/main/java" +
                "/com/uade/tpo/E_Commerce/images/buyer_user/");
        registry.addResourceHandler("/images/seller_user/**")
                .addResourceLocations("file:C:/Users/Admin/Documents/carpeta agus/uade/apis/api/E-Commerce/src/main/java" +
                        "/com/uade/tpo/E_Commerce/images/seller_users/")
                .addResourceLocations("file:C:/Users/Tomas/Escritorio/tpo_apis/api/E-Commerce/src/main/java" +
                        "/com/uade/tpo/E_Commerce/images/seller_users/");
    }
}

