package NiUnaMas;
/*
import NiUnaMas.Models.Authorization;

import NiUnaMas.Daos.AuthorizationDao;*/
import NiUnaMas.Daos.UserWebDao;
import NiUnaMas.Models.User;
import NiUnaMas.Daos.UserDao;
import NiUnaMas.Models.UserWeb;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "NiUnaMas")
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(final UserWebDao userWebDao) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				UserWeb user = new NiUnaMas.Models.UserWeb("admin@policia.es", "74384760T", "Esclapez García" , "Robert", "hasdi712n310fmasda43", 2);
				userWebDao.save(user);
				User userN = new NiUnaMas.Models.User();
			}
		};

	}
}


