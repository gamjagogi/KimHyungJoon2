package shop.mtcoding.kimhyungjoon2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//여기서 프론트엔드 도메인만 열어주어야 한다는거 같다.
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //Cors 매핑 추가.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .exposedHeaders("Authorization");
    }

    //인터셉터 추가
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("loginForm");
    }
}
