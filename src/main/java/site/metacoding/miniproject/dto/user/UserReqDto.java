package site.metacoding.miniproject.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserReqDto {
    @Getter
    @NoArgsConstructor
    public static class LoginDto {
        private String loginId;
        private String loginPassword;


        public LoginDto(String loginId, String loginPassword) {
            this.loginId = loginId;
            this.loginPassword = loginPassword;
        }


    }


}
