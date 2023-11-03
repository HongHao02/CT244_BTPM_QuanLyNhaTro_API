package B2012202.CT240.webppdemo.SbQuanlynhatro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories(basePackages = "B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories")
public class SbQuanlynhatroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbQuanlynhatroApplication.class, args);
	}

}
