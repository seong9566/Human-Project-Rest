package site.metacoding.miniproject.dto.user;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.personal.Personal;

public class UserRespDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SignedDto<T> implements Serializable {
        private Integer usersId;
        private String loginId;
        // 구독정보 및 좋아요 정보 넣기
        private T userInfo;

        public SignedDto(Integer usersId, String loginId, T userInfo) {
            this.usersId = usersId;
            this.loginId = loginId;
            this.userInfo = userInfo;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SignCompanyDto implements Serializable {
        private Integer companyId;

        public SignCompanyDto(Company company) {
            this.companyId = company.getCompanyId();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SignPersonalDto implements Serializable {
        private Integer personalId;

        public SignPersonalDto(Personal personal) {
            this.personalId = personal.getPersonalId();
        }
    }

}
