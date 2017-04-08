package NiUnaMas;

import NiUnaMas.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(final UserDao userDao) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				NiUnaMas.Models.User user = new NiUnaMas.Models.User("74384760T", "Robert", "Esclapez García", 669754254, 616600727, "admin@policia.es",
                        "Daimes", "hasdi712n310fmasda43");
				userDao.save(user);
			}

		};
	}
}

@Configuration
class WebSecirityConfiguration extends GlobalAuthenticationConfigurerAdapter {
	@Autowired
	UserDao userDao;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	UserDetailsService userDetailsService(){
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
				NiUnaMas.Models.User user = userDao.findByEmail(s);
				if(user != null){
				    String role;
				    if(user.getEmail()=="admin@policia.es"){
				        role="ADMIN";
                    }else{
				        role="USER";
                    }
					return new User(user.getEmail(), user.getPassword(),true , true, true, true,
							AuthorityUtils.createAuthorityList(role));
				} else {
					throw new UsernameNotFoundException("could not find the user '" + s + "'");
				}
			}
		};
	}
}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected  void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().anyRequest().permitAll().and().
		httpBasic().and().csrf().disable();
        /*http.authorizeRequests().antMatchers("/login").permitAll().and().csrf().disable();*/
	}
}
