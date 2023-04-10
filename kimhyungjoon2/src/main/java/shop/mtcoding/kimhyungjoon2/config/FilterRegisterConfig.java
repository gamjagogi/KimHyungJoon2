package shop.mtcoding.kimhyungjoon2.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.mtcoding.kimhyungjoon2.core.filter.JwtVerifyFilter;

//이곳에서 필터를 추가하고, 설정한다.
//bean으로 관리되는 메서드. 초기화시 자동으로 ioc에서 등록한 필터를 등록(설정)해준다.
@Configuration
public class FilterRegisterConfig {

    //jwt 필터
    @Bean
    public FilterRegistrationBean<?> jwtVerifyFilterAdd() {
        FilterRegistrationBean<JwtVerifyFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new JwtVerifyFilter());
        registration.addUrlPatterns("/user/*");
        registration.setOrder(1);
        return registration;
    }
}
