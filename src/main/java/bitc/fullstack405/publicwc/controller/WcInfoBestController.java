package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.dto.WcInfoWithBestDTO;
import bitc.fullstack405.publicwc.service.WcInfoBestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class WcInfoBestController {

    @Autowired
    private WcInfoBestService wcInfoBestService;

    @GetMapping("/{id}")
    public WcInfoWithBestDTO getWcInfoWithBestDetail(@PathVariable int id) {
        return wcInfoBestService.getWcInfoWithBestDetails(id);
    }
}
