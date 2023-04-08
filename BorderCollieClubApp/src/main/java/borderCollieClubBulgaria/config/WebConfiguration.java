package borderCollieClubBulgaria.config;

import borderCollieClubBulgaria.web.interceptor.MethodInvokeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final MethodInvokeInterceptor methodInvokeInterceptor;

    public WebConfiguration(MethodInvokeInterceptor methodInvokeInterceptor) {
        this.methodInvokeInterceptor = methodInvokeInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(methodInvokeInterceptor);

    }
}
