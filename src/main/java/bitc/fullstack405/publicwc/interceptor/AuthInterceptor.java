package bitc.fullstack405.publicwc.interceptor;

import bitc.fullstack405.publicwc.entity.Users;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션에서 사용자 정보 확인
        Users user = (Users) request.getSession().getAttribute("user"); // Object에서 Users로 캐스팅

        // 인증되지 않은 사용자 처리
        if (user == null) {
            response.sendRedirect("/board2/login"); // 로그인 페이지로 리다이렉트
            return false; // 요청을 진행하지 않음
        }

        return true; // 요청을 진행
    }
}
