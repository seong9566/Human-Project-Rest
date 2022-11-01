package site.metacoding.miniproject.dto.user;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.personal.Personal;

public class UserRespDto {


    @Getter
    @Setter
    public static class SignedDto<T> {
        private Integer usersId;
        private String loginId;
        private T userInfo;

        public SignedDto(Integer usersId, String loginId, T userInfo) {
            this.usersId = usersId;
            this.loginId = loginId;
            this.userInfo = userInfo;
        }

        
    }

    @Getter
    @Setter
    public static class SignCompanyDto {
        private Integer companyId;

        public SignCompanyDto(Company company) {
            this.companyId = company.getCompanyId();
        }
    }

    @Getter
    @Setter
    public static class SignPersonalDto {
        private Integer personalId;

        public SignPersonalDto(Personal personal) {
            this.personalId = personal.getPersonalId();
        }
    }

}
