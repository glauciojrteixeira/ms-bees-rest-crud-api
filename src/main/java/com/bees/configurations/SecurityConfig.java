package com.bees.configurations;

import com.bees.securities.JWTAuthenticationFilter;
import com.bees.securities.JWTAuthorizationFilter;
import com.bees.securities.JWTUtil;
import com.bees.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment; // Configuração especifica para liberar o acesso ao H2

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JWTUtil jwtUtil;

    /**
     * Vetor de strings para definir quais os caminhos
     * estarão liberados por padrão
     */
    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };

    /**
     * Vetor de strings para definir quais os caminhos
     * estarão liberados somente para leitura
     */
    private static final String[] PUBLIC_MATCHERS_GET = {
            "/marcas/**"
    };

    /**
     * Vetor de strings para definir quais os caminhos
     * estarão liberados somente para inserção
     */
    private static final String[] PUBLIC_MATCHERS_POST = {
            "/usuarios",
            "/authentication/forgot/**"
    };

    /**
     * Vetor de string para definir quais os caminhos
     * estarão liberados para o Swagger UI.
     */
    private static final String[] PUBLIC_MATCHERS_SWAGGER = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };


    /**
     * Libera acessos aos caminhos do Swagger
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * Configuração especifica para liberar o acesso ao H2
         */
        if (Arrays.asList(environment.getActiveProfiles()).contains("local")) {
            http.headers().frameOptions().disable();
        }

        /**
         * Habilita o Bean Cors, desabilita a proteção de ataques do
         * tipo CSRF (Esse sistema será Stateless - não armazena sessão de usuario
         */
        http.cors().and().csrf().disable();


        /*
         * Libera os caminhos padrões e bloqueia todo o resto e permite GET somente nos
         * path do vetor
         */

        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_SWAGGER).permitAll()
                .anyRequest().authenticated();

        /**
         * Define o sistema como Stateless, garantindo que o backend não ira criar sessão de usuario.
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /**
         * Registra filtro de autenticação
         */
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));

        /**
         * Registra filtro de autorização
         */
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailService));

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }


    /**
     * Permite acesso de multiplas fontes (cross-origem) com as configurações basicas
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    /**
     * Encripta a senha informada
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
