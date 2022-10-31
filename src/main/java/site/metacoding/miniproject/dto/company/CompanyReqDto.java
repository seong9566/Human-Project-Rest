package site.metacoding.miniproject.dto.company;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CompanyReqDto {

    @Getter
    @Setter
    public static class CompanyJoinDto { // CompanyJoinDto -> CompanyJoinReqDto
        private String loginId;
        private String loginPassword;
        private String companyName;
        private String companyEmail;
        private String companyPicture;
        private String companyAddress;
        private String companyPhoneNumber;
        // 계정생성용
        private Integer companyId;
        private Integer categoryId;

    }

    @Getter
    @Setter
    public static class CompanyLikeDto { // CompanyLikeDto -> CompanyLikeReqDto
        private Integer companyId;
        private String companyName;
        private String companyPicture;
        private String companyEmail;
        private String companyPhoneNumber;
        private String companyAddress;
        private Timestamp createdAt;

        private Integer personalId;
        private Integer alarmId;
    }

    @Getter
    @Setter
    public static class CompanyUpdateDto { // CompanyUpdateDto -> CompanyUpdateReqDto
        // Company업데이트
        private String companyName;
        private String companyPicture;
        private String companyEmail;
        private String companyPhoneNumber;
        private String companyAddress;
        // Users 업데이트
        private String loginPassword;
    }

}
