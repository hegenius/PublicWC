package bitc.fullstack405.publicwc.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.query.sqm.mutation.internal.Handler;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션에서 사용자 정보를 가져옵니다.
        Object userId = request.getSession().getAttribute("userId");

        // 로그인 페이지와 회원가입 페이지는 예외로 합니다.
        String uri = request.getRequestURI();
        if (userId == null && !uri.equals("/login") && !uri.equals("/signup")) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
