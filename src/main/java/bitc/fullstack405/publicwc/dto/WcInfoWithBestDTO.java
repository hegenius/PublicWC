package bitc.fullstack405.publicwc.dto;

import lombok.Data;

@Data
public class WcInfoWithBestDTO {
    private int wcInfoId;
    private int wcInfoLevel;
    private String wcInfoName;
    private String wcInfoAddr1;
    private String wcInfoDetailAddr;
    private String wcInfoTime;
    private String wcInfoComment;
    private String wcInfoWcPass;
    private long likes;
    private long dislikes;

    public WcInfoWithBestDTO(int wcInfoId, int wcInfoLevel, String wcInfoName, String wcInfoAddr1, String wcInfoDetailAddr, String wcInfoTime, String wcInfoComment, String wcInfoWcPass, long likes, long dislikes) {
        this.wcInfoId = wcInfoId;
        this.wcInfoLevel = wcInfoLevel;
        this.wcInfoName = wcInfoName;
        this.wcInfoAddr1 = wcInfoAddr1;
        this.wcInfoDetailAddr = wcInfoDetailAddr;
        this.wcInfoTime = wcInfoTime;
        this.wcInfoComment = wcInfoComment;
        this.wcInfoWcPass = wcInfoWcPass;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
