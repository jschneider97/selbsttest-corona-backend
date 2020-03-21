package com.wirvsvirus.selftest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author Justus Schneider
 */

@Configuration
@EnableWebFlux
public class WebfluxConfig implements WebFluxConfigurer {

}