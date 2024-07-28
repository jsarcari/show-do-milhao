package showdomilhao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
public class ShowdomilhaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowdomilhaoApplication.class, args);
	}

}
