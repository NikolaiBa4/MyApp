package borderCollieClubBulgaria.config;

import borderCollieClubBulgaria.web.interceptors.DefendUsersInfoInterceptor;
import borderCollieClubBulgaria.web.interceptors.EasterEggInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final EasterEggInterceptor egg;
    private final DefendUsersInfoInterceptor defendUsersInfoInterceptor;

    public WebConfiguration(EasterEggInterceptor egg, DefendUsersInfoInterceptor defendUsersInfoInterceptor) {
        this.egg = egg;
        this.defendUsersInfoInterceptor = defendUsersInfoInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(egg);
        registry.addInterceptor(defendUsersInfoInterceptor);

    }
}
