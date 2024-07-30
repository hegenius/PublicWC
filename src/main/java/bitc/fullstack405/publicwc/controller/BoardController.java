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
    public String submitPost(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("addr1") String addr1,
                             @RequestParam("addr2") String addr2,
                             @RequestParam("detailAddr") String detailAddr,
                             @RequestParam("latitude") String latitude,
                             @RequestParam("longitude") String longitude,
                             @RequestParam("createUserId") String createUserId,
                             Model model) {
        WcInfo wcInfo = new WcInfo();
        wcInfo.setName(title);
        wcInfo.setComment(content);
        wcInfo.setAddr1(addr1);
        wcInfo.setAddr2(addr2);
        wcInfo.setDetailAddr(detailAddr);
        wcInfo.setLatitude(latitude);
        wcInfo.setLongitude(longitude);
        wcInfo.setCreateUserId(createUserId);

        toiletService.addWcInfo(wcInfo);

        model.addAttribute("message", "게시물이 성공적으로 등록되었습니다.");
        return "redirect:/board/list";
    }
}
