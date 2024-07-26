package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.WcInfo;

import java.util.List;

public interface ToiletService {
    List<WcInfo> searchToiletsByAddress(String address);
    List<WcInfo> searchToiletsByLevel(int level);
    List<WcInfo> getAllToilets(); // 모든 화장실 조회 메서드 추가
    WcInfo addWcInfo(WcInfo wcInfo);
    WcInfo updateWcInfo(int id, WcInfo wcInfo);
    void deleteWcInfo(int id);
}
