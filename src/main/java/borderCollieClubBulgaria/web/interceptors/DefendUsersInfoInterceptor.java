package borderCollieClubBulgaria.web.interceptors;

import borderCollieClubBulgaria.domain.entities.UserEntity;
import borderCollieClubBulgaria.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class DefendUsersInfoInterceptor implements HandlerInterceptor {

    private static final String REDIRECT_URL = "/";

    private static final String URL = "/pages/admins/allUsers";

    private static final String LOGGER_INFO = "preHandle invoked..{}:";
    private Logger LOG = LoggerFactory.getLogger(DefendUsersInfoInterceptor.class);

    private final AdminService adminService;

    public DefendUsersInfoInterceptor(AdminService adminService) {
        this.adminService = adminService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LOG.info(LOGGER_INFO + request.getRequestURI(), request.getMethod());

        if (request.getRequestURI().equals(URL)) {

            UserEntity user = this.adminService.findUserByUsername(request.getUserPrincipal().getName());

            if (user.getRoles().size() == 1) {

                this.adminService.deleteUserById(user.getId());

                request.logout();
                request.getSession().invalidate();
                response.sendRedirect(REDIRECT_URL);


            }
        }

        return true;
    }

}
