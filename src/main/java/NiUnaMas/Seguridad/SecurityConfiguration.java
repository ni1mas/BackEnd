package NiUnaMas.Seguridad;

import NiUnaMas.Varios.Uris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Robert on 09/04/2017.
 */

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); //mejor delegar que hacer en memoria.
        //userDetailsService autentica, password codifica
	    /*auth.inMemoryAuthentication()
                .withUser("admin@policia.es").password("hasdi712n310fmasda43").authorities("USER", "ADMIN");*/
    }

    @Override
    protected  void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(Uris.SERVLET_MAP + Uris.USER + "/**").permitAll()
                .antMatchers(Uris.SERVLET_MAP + Uris.USER + Uris.ID + "/**").authenticated()
                .and().httpBasic();
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
