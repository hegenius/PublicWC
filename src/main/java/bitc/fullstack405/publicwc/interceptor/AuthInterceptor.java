package bitc.fullstack405.publicwc.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션에서 사용자 정보 확인
        Object user = request.getSession().getAttribute("user");

        // 인증되지 않은 사용자 처리
        if (user == null) {
            response.sendRedirect("/login"); // 로그인 페이지로 리다이렉트
            return false; // 요청을 진행하지 않음
        }

        return true; // 요청을 진행
    }
}
