package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wcs")
public class WcController {

    @Autowired
    private ToiletService toiletService;

    @GetMapping
    public List<WcInfo> getWcs(@RequestParam(required = false) String address,
                               @RequestParam(required = false) Integer level) {
        if (address != null) {
            return toiletService.searchToiletsByAddress(address);
        } else if (level != null) {
            return toiletService.searchToiletsByLevel(level);
        } else {
            return toiletService.getAllToilets(); // 모든 화장실 조회 메서드
        }
    }

    @PostMapping
    public WcInfo addWc(@RequestBody WcInfo wcInfo) {
        return toiletService.addWcInfo(wcInfo);
    }

    @PutMapping("/{id}")
    public WcInfo updateWc(@PathVariable int id, @RequestBody WcInfo wcInfo) {
        return toiletService.updateWcInfo(id, wcInfo);
    }

    @DeleteMapping("/{id}")
    public void deleteWc(@PathVariable int id) {
        toiletService.deleteWcInfo(id);
    }
}
