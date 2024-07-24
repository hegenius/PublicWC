package bitc.fullstack405.publicwc.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoMapComponent {

    @Value("${kakaoMap.key}")
    private String kakaoKey;

}
