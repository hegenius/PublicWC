package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.dto.WcInfoWithBestDTO;
import bitc.fullstack405.publicwc.service.WcInfoBestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wcinfo")
public class WcInfoBestController {

    @Autowired
    private WcInfoBestService wcInfoBestService;

    // 화장실의 좋아요/싫어요 정보 가져오기
    @GetMapping("/{id}")
    public WcInfoWithBestDTO getWcInfoWithBestDetail(@PathVariable int id) {
        return wcInfoBestService.getWcInfoWithBestDetails(id);
    }

    // 좋아요 추가
    @PostMapping("/{wcId}/like/{userId}")
    public ResponseEntity<String> likeWc(@PathVariable int wcId, @PathVariable String userId) {
        wcInfoBestService.addBest(wcId, userId, true); // 좋아요 추가
        return ResponseEntity.ok("Like added successfully"); // 성공 메시지 반환
    }

    // 싫어요 추가
    @PostMapping("/{wcId}/dislike/{userId}")
    public ResponseEntity<String> dislikeWc(@PathVariable int wcId, @PathVariable String userId) {
        wcInfoBestService.addBest(wcId, userId, false); // 싫어요 추가
        return ResponseEntity.ok("Dislike added successfully"); // 성공 메시지 반환
    }

    // 특정 사용자의 좋아요/싫어요 상태 확인
    @GetMapping("/{wcId}/status/{userId}")
    public ResponseEntity<String> getStatus(@PathVariable int wcId, @PathVariable String userId) {
        String status = wcInfoBestService.getBestStatus(wcId, userId);
        return ResponseEntity.ok(status); // 상태 반환
    }
}
