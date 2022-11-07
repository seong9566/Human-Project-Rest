package site.metacoding.miniproject.dto.personal;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.utill.SHA256;

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

        public Personal personalJoinDtoToPersonalEntity() {
            return Personal.builder()
                    .personalName(personalName)
                    .personalEmail(personalEmail)
                    .personalEducation(personalEducation)
                    .personalAddress(personalAddress)
                    .personalPhoneNumber(personalPhoneNumber)
                    .build();
        }

        public Users personalJoinDtoToUserEntity() {

            SHA256 sha256 = new SHA256();
            this.loginPassword = sha256.encrypt(this.loginPassword);

            return Users.builder()
                    .loginId(loginId)
                    .loginPassword(loginPassword)
                    .personalId(personalId)
                    .build();
        }
    }

    @Getter
    @Setter

    public static class PersonalUpdatReqDto { // PersonalUpdateDto -> PersonalUpdateReqDto

        private String personalName;
        private String personalEmail;
        private String personalEducation;

        private String personalPhoneNumber;
        private String personalAddress;

        private String loginPassword;

        public Personal personalUpdateDtoToPersonalEntity() {
            return Personal.builder()
                    .personalName(personalName)
                    .personalEmail(personalEmail)
                    .personalEducation(personalEducation)
                    .personalPhoneNumber(personalPhoneNumber)
                    .personalAddress(personalAddress)
                    .build();
        }

        public Users personalPassWordUpdateReqDto() {

            SHA256 sha256 = new SHA256();
            this.loginPassword = sha256.encrypt(this.loginPassword);

            return Users.builder()
                    .loginPassword(loginPassword)
                    .build();
        }

    }

}
