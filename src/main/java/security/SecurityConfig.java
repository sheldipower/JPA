package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                // .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .sessionManagement(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        matcherRegistry ->
                                matcherRegistry
                                        .requestMatchers(HttpMethod.POST, "/employee/**", "/report/**")
                                        .hasRole(Role.ADMIN.name())
                                        .requestMatchers(HttpMethod.PUT, "/employee/**").hasRole(Role.ADMIN.name())
                                        .requestMatchers(HttpMethod.DELETE, "/employee/**").hasRole(Role.ADMIN.name())
                                        .requestMatchers(HttpMethod.GET, "/employee/**", "/report/**")
                                        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                                        .requestMatchers("/**").permitAll()
                )
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
