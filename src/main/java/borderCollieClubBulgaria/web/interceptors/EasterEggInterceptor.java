package borderCollieClubBulgaria.web.interceptors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class EasterEggInterceptor implements HandlerInterceptor {

    private static final String EGG = "If_you_want_a_good_employee_contact_me_at_nikolai_ba4@abv.bg";

    private static final String COOKIE_NAME = "easterEgg";

    private static final String LOG_INFO = "Easter Egg was deployed";
    private final Logger logger = LoggerFactory.getLogger(EasterEggInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookie = new Cookie(COOKIE_NAME, EGG);
        cookie.setMaxAge(50);
        response.addCookie(cookie);

        logger.info(LOG_INFO);

        return true;
    }
}
