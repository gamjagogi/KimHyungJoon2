package shop.mtcoding.kimhyungjoon2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shop.mtcoding.kimhyungjoon2.model.orderproduct.OrderProductRepository;
import shop.mtcoding.kimhyungjoon2.model.ordersheet.OrderSheetRepository;
import shop.mtcoding.kimhyungjoon2.model.product.ProductRepository;
import shop.mtcoding.kimhyungjoon2.model.user.User;
import shop.mtcoding.kimhyungjoon2.model.user.UserRepository;

@SpringBootApplication
public class Kimhyungjoon2Application {

	@Bean
	CommandLineRunner initDate(UserRepository userRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository, OrderSheetRepository orderSheetRepository){
		return (args)->{
			//이곳에 save
			// bulk collector는 savaAll하면 됨
			User ssar = User.builder().username("ssar").password("1234").email("ssar@naver.com").role("USER").build();
			userRepository.save(ssar);
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(Kimhyungjoon2Application.class, args);
	}

}
