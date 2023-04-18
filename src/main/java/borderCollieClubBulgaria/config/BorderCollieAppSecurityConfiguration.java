package borderCollieClubBulgaria.config;

import borderCollieClubBulgaria.domain.enums.UserRoles;
import borderCollieClubBulgaria.repository.UserRepository;
import borderCollieClubBulgaria.service.BorderCollieAppDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BorderCollieAppSecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll().
                requestMatchers("/pages/admins").hasRole(UserRoles.ADMIN.name()).
                anyRequest().authenticated().
                and().
                formLogin().
                loginPage("/users/login").usernameParameter("username").passwordParameter("password").
                defaultSuccessUrl("/").
                failureForwardUrl("/users/login-error").
                and().
                logout().
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").
                deleteCookies("JSESSIONID").invalidateHttpSession(true);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new BorderCollieAppDetailsService(userRepository);
    }


}