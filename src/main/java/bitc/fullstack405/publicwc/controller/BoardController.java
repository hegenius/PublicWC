package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ToiletService toiletService;

    @GetMapping("/write")
    public String boardWrite(Model model) {
        return "board/boardWrite"; // boardWrite.html을 렌더링
    }

    @PostMapping("/write")
    public String submitPost(@RequestParam Map<String, String> allParams, Model model) {
        // 로그로 파라미터 확인
        allParams.forEach((key, value) -> System.out.println(key + ": " + value));

        // 파라미터를 WcInfo 객체로 변환
        String title = allParams.getOrDefault("title", "");
        String content = allParams.getOrDefault("content", "");
        String addr1 = allParams.getOrDefault("addr1", "");
        String addr2 = allParams.getOrDefault("addr2", "");
        String time = allParams.getOrDefault("time", "");
        String latitude = allParams.getOrDefault("latitude", "");
        String longitude = allParams.getOrDefault("longitude", "");
        String createUserId = allParams.getOrDefault("createUserId", "");
        String wcpass = allParams.getOrDefault("wcpass", "");

        // 데이터 검증 (필수 항목 확인)
        if (addr1.isEmpty() || createUserId.isEmpty()) {
            model.addAttribute("error", "도로명 주소와 사용자 ID는 필수 입력 사항입니다.");
            return "board/boardWrite"; // 입력 폼으로 다시 리턴
        }

        WcInfo wcInfo = new WcInfo();
        wcInfo.setName(title);
        wcInfo.setComment(content);
        wcInfo.setAddr1(addr1);
        wcInfo.setAddr2(addr2);
        wcInfo.setTime(time);
        wcInfo.setLatitude(latitude);
        wcInfo.setLongitude(longitude);
        wcInfo.setCreateUserId(createUserId);
        wcInfo.setWcpass(wcpass);

        toiletService.addWcInfo(wcInfo);

        // 저장 완료 후 목록 페이지로 리다이렉트
        return "redirect:/board/list?message=게시물이%20성공적으로%20등록되었습니다.";
    }

    @GetMapping("/list")
    public String boardList(Model model) {
        // 모든 화장실 정보를 가져와서 모델에 추가
        model.addAttribute("wcList", toiletService.getAllToilets());
        return "board/boardList"; // boardList.html을 렌더링
    }
}

