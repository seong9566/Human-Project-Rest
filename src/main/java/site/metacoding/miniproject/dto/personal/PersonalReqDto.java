package site.metacoding.miniproject.dto.personal;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class PersonalReqDto {

    @Getter
    @Setter
    public static class PersonalJoinDto { // PersonalJoinDto -> PersonalJoinReqDto
        private String loginId;
        private String loginPassword;
        private String personalName;
        private String personalEmail;
        private String personalPhoneNumber;
        private String personalEducation;
        private String personalAddress;

        // Users계정 생성용
        private Integer personalId;
    }

    @Getter
    @Setter
    public static class PersonalLikeDto { // PersonalLikeDto -> PersonalLikeReqDto
        // resumes
        private Integer resumesId;
        private Integer personalId;
        private String resumesTitle;
        private String resumesPicture;
        private String resumesIntroduce;
        private Timestamp createdAt;

        private Integer personalLikeId;
        private Integer companyId;
        private Integer alarmId;
    }

    @Getter
    @Setter
    public static class PersonalUpdateDto { // PersonalUpdateDto -> PersonalUpdateReqDto
        private Integer personalId;
        private String personalName;
        private String personalEmail;
        private String personalEducation;

        private String personalPhoneNumber;
        private String personalAddress;

        private String loginId;
        private String loginPassword;

    }

}
