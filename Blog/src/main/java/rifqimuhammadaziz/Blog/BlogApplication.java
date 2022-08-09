package rifqimuhammadaziz.Blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"rifqimuhammadaziz.Library.*", "rifqimuhammadaziz.Blog.*"})
@EnableJpaRepositories(value = "rifqimuhammadaziz.Library.repository")
@EntityScan(value = "rifqimuhammadaziz.Library.model")
public class BlogApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
