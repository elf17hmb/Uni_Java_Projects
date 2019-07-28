package whz.pti.eva.config;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/h2-console/**").antMatchers("/console/**");;
//        web.ignoring().anyRequest();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/login","/css/**","/cart/**","/pizzas/**","/users/**","/customers/**").permitAll()
//                .antMatchers("/b**").permitAll()
//                .antMatchers("/users/m**").hasAuthority("ADMIN")
//                .antMatchers("/users/c**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
//                    .loginProcessingUrl("my login")
                    .defaultSuccessUrl("/first", true)
                    .failureUrl("/login?error")
                    .usernameParameter("email")
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutUrl("/logout")
                    .permitAll()
                    .and()
                .rememberMe()
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
                 auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}