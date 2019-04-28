package hello.config;

import hello.function.JWTAuthenticationFilter;
import hello.function.JWTAuthorizationFilter;
import hello.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET,"/TShirts/user/tshirts").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/TShirts/**","/feedback/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/TShirts/delete/**","/feedback/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/users/activity/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/users/activity/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/admin/**").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/login", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
