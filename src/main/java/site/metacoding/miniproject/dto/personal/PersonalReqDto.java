package site.metacoding.miniproject.dto.personal;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.personal.Personal;

public class PersonalReqDto {

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

        public Personal toEntity() {
            return Personal.
        }

    }

}
