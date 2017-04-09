package NiUnaMas;

import NiUnaMas.Models.Authorization;
import NiUnaMas.Models.User;
import NiUnaMas.daos.AuthorizationDao;
import NiUnaMas.daos.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(final UserDao userDao, final AuthorizationDao authDao) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
			    Authorization auth = new Authorization("ADMIN");
                authDao.save(new Authorization("USER"));
                authDao.save(auth);
                List<Authorization> list = new ArrayList<>();
                list.add(auth);
				User user = new NiUnaMas.Models.User("74384760T", "Robert", "Esclapez García", 669754254, 616600727, "admin@policia.es",
                        "Daimes","hasdi712n310fmasda43", list);
				userDao.save(user);
			}

		};
	}
}

/**
 * Para las sesiones.
 */
/*@Configuration
@EnableSpringHttpSession
class HttpSessionConfig {
    @Bean
    SessionRepository<ExpiringSession> inmemorySessionRepository(){
        return new MapSessionRepository();
    }
    @Bean
    HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }
}*/


