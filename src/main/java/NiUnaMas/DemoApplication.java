package NiUnaMas;
/*
import NiUnaMas.Models.Authorization;

import NiUnaMas.Daos.AuthorizationDao;*/
import NiUnaMas.Models.User;
import NiUnaMas.Daos.UserDao;
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
	CommandLineRunner init(final UserDao userDao) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				User user = new NiUnaMas.Models.User("74384760T", "Robert", "Esclapez García",
						669754254, 616600727, "admin@policia.es",
                        "Daimes","hasdi712n310fmasda43");
				userDao.save(user);
			}
		};
	}
}


