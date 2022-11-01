package site.metacoding.miniproject.dto.personal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.personal.Personal;

public class PersonalRespDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PersonalDetailRespDto {
        private Integer personalId;
        private String personalName;
        private String personalEmail;
        private String personalPhoneNumber;
        private String personalEducation;
        private String personalAddress;

        public PersonalDetailRespDto(Personal personal) {
            this.personalId = personal.getPersonalId();
            this.personalName = personal.getPersonalName();
            this.personalEmail = personal.getPersonalEmail();
            this.personalPhoneNumber = personal.getPersonalPhoneNumber();
            this.personalEducation = personal.getPersonalEducation();
            this.personalAddress = personal.getPersonalAddress();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PersonalUpdateFormRespDto {
        private Integer personalId;
        private String personalName;
        private String personalEmail;
        private String personalPhoneNumber;
        private String personalEducation;
        private String personalAddress;

        private String loginPassword;

        public PersonalUpdateFormRespDto(PersonalUpdateFormRespDto personalUpdateFormRespDto) {
            this.personalId = personalUpdateFormRespDto.getPersonalId();
            this.personalName = personalUpdateFormRespDto.getPersonalName();
            this.personalEmail = personalUpdateFormRespDto.getPersonalEmail();
            this.personalPhoneNumber = personalUpdateFormRespDto.getPersonalPhoneNumber();
            this.personalAddress = personalUpdateFormRespDto.getPersonalAddress();

            this.loginPassword = personalUpdateFormRespDto.getLoginPassword();

        }
    }

}
