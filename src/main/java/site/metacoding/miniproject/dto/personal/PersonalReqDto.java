package site.metacoding.miniproject.dto.personal;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.personal.Personal;

public class PersonalReqDto {
        @Getter
        @Setter
        public static class PersonalJoinDto {
            private String loginId;
            private String loginPassword;
            private String personalName;
            private String personalEmail;
            private String personalPhoneNumber;
            private String personalEducation;
            private String personalAddress;

        // Users계정 생성용
        private Integer personalId;

        public Personal personalJoinDtoToEntity() {
            return null;
        }
        }

}
