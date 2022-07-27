package rifqimuhammadaziz.Dashboard;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"rifqimuhammadaziz.Library.*", "rifqimuhammadaziz.Dashboard.*"})
@EnableJpaRepositories(value = "rifqimuhammadaziz.Library.repository")
@EntityScan(value = "rifqimuhammadaziz.Library.model")
public class DashboardApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

}
