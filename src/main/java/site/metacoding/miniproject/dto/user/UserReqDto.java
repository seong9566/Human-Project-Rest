package site.metacoding.miniproject.dto.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;

public class UserReqDto {

    @Getter
    @NoArgsConstructor
    public static class LoginDto { // LoginDto -> LoginReqDto
        private String loginId;
        private String loginPassword;

        public LoginDto(PersonalJoinDto joindto) {
            this.loginId = joindto.getLoginId();
            this.loginPassword = joindto.getLoginPassword();
        }

        public LoginDto(CompanyJoinDto joindto) {
            this.loginId = joindto.getLoginId();
            this.loginPassword = joindto.getLoginPassword();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignedDto<T> implements Serializable {
        private Integer usersId;
        private String loginId;
        private String loginPassword;
        private Integer personalId;
        private Integer companyId;
        private T Userinfo;

        private static final long serialVersionUID = -242078978525600927L;
    }

}
