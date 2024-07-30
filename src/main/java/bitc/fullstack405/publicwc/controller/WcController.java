package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location/wcs")
public class WcController {

    @Autowired
    private ToiletService toiletService;

    @GetMapping
    public ResponseEntity<List<WcInfo>> getWcs(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer level) {
        List<WcInfo> wcInfos;
        if (address != null) {
            wcInfos = toiletService.searchToiletsByAddress(address);
        } else if (level != null) {
            wcInfos = toiletService.searchToiletsByLevel(level);
        } else {
            wcInfos = toiletService.getAllToilets(); // 모든 화장실 조회 메서드
        }
        return new ResponseEntity<>(wcInfos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WcInfo> addWc(@RequestBody WcInfo wcInfo) {
        WcInfo createdWc = toiletService.addWcInfo(wcInfo);
        return new ResponseEntity<>(createdWc, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WcInfo> updateWc(@PathVariable int id, @RequestBody WcInfo wcInfo) {
        WcInfo updatedWc = toiletService.updateWcInfo(id, wcInfo);
        if (updatedWc != null) {
            return new ResponseEntity<>(updatedWc, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWc(@PathVariable int id) {
        boolean isDeleted = toiletService.deleteWcInfo(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

