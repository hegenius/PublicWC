package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToiletServiceImpl implements ToiletService {

    @Autowired
    private WcInfoRepository wcInfoRepository;

    @Override
    public List<WcInfo> searchToiletsByAddress(String address) {
        String[] addressParts = address.split(" ");
        String addr1 = addressParts.length > 0 ? addressParts[0] : "";
        String addr2 = addressParts.length > 1 ? addressParts[1] : "";

        return wcInfoRepository.findByAddr1ContainingOrAddr2Containing(addr1, addr2);
    }

    @Override
    public List<WcInfo> getAllToilets() {
        return wcInfoRepository.findAll();
    }
}
