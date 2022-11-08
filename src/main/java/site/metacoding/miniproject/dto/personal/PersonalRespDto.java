package site.metacoding.miniproject.dto.personal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.users.Users;

public class PersonalRespDto {

    @Getter
    @Setter
    public static class PersonalAddressRespDto {
        private Integer personalId;
        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

        public PersonalAddressRespDto(Integer personalId, String zoneCode, String roadJibunAddr, String detailAddress) {
            this.personalId = personalId;
            this.zoneCode = zoneCode;
            this.roadJibunAddr = roadJibunAddr;
            this.detailAddress = detailAddress;

        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PersonalDetailRespDto {
        private Integer personalId;
        private String personalName;
        private String personalEmail;
        private String personalPhoneNumber;
        private String personalEducation;

        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

        public PersonalDetailRespDto(Personal personal) {

            this.personalId = personal.getPersonalId();
            this.personalName = personal.getPersonalName();
            this.personalEmail = personal.getPersonalEmail();
            this.personalPhoneNumber = personal.getPersonalPhoneNumber();
            this.personalEducation = personal.getPersonalEducation();

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

        private String loginPassword;

        private Integer personalForAddressId;
        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

        public PersonalUpdateFormRespDto(PersonalUpdateFormRespDto personalUpdateFormRespDto,
                PersonalAddressRespDto personalAddressRespDto) {

            this.personalId = personalUpdateFormRespDto.getPersonalId();
            this.personalName = personalUpdateFormRespDto.getPersonalName();
            this.personalEmail = personalUpdateFormRespDto.getPersonalEmail();
            this.personalPhoneNumber = personalUpdateFormRespDto.getPersonalPhoneNumber();
            this.personalEducation = personalUpdateFormRespDto.getPersonalEducation();

            this.loginPassword = personalUpdateFormRespDto.getLoginPassword();

            this.personalForAddressId = personalAddressRespDto.getPersonalId();
            this.zoneCode = personalAddressRespDto.getZoneCode();
            this.roadJibunAddr = personalAddressRespDto.getRoadJibunAddr();
            this.detailAddress = personalAddressRespDto.getDetailAddress();

        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PersonalUpdateRespDto {
        private Integer personalId;
        private String personalName;
        private String personalEmail;
        private String personalPhoneNumber;
        private String personalEducation;
        private String personalAddress;
        private String loginPassword;

        public PersonalUpdateRespDto(Personal personal, Users users) {
            this.personalId = personal.getPersonalId();
            this.personalName = personal.getPersonalName();
            this.personalEmail = personal.getPersonalEmail();
            this.personalPhoneNumber = personal.getPersonalPhoneNumber();
            this.personalEducation = personal.getPersonalEducation();
            this.personalAddress = personal.getPersonalAddress();
            this.loginPassword = users.getLoginPassword();
        }

    }
}