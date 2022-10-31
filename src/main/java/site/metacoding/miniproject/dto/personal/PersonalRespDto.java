package site.metacoding.miniproject.dto.personal;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class PersonalRespDto {

    @Getter
    @Setter
    public static class PersonalAddressDto { // PersonalAddressDto -> PersonalAddressRespDto
        private Integer personalId;
        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

    }

    @Getter
    @Setter
    public static class PersonalFormDto { // PersonalFormDto -> PersonalFormRespDto
        private String personalName;
        private String personalEmail;
        private String personalPhoneNumber;
        private String personalEducation;
    }

    @Setter
    @Getter
    public static class PersonalInfoDto { // PersonalInfoDto -> PersonalInfoRespDto
        // 이력서 작성 시 보여 줄 개인정보
        private String personalName;
        private String personalEmail;
        private String personalEducation;
        private String personalPhoneNumber;
        private String personalAddress;
    }

    @Setter
    @Getter
    public static class PersonalMainDto { // PersonalMainDto -> PersonalMainRespDto
        private Integer jobPostingBoardId;
        private String jobPostingBoardTitle;
        private Timestamp jobPostingBoardDeadline;
        // 모집중, 모집 마감
        private String state;
        // TimeStamp > String
        private String formatDeadLine;

        // 드롭다운 리스트
        private Boolean categoryFrontend;
        private Boolean categoryBackend;
        private Boolean categoryDevops;
        private String categoryAll;

        private Integer jobPostingBoardCategoryId;
        private Integer categoryId;
        private Integer id;
    }

}
