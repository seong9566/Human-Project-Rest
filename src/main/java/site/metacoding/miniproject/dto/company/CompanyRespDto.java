package site.metacoding.miniproject.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;

public class CompanyRespDto {

    @Setter
    @Getter
    @NoArgsConstructor
    public static class CompanyInfoRespDto { // CompanyInfoDto -> CompanyInfoRespDto
        private Integer usersId;
        private Integer companyId;
        private String loginId;
        private String loginPassword;
        private String companyName;
        private String companyPhoneNumber;
        private String companyEmail;
        private String companyPicture;
        private String companyAddress;

        public CompanyInfoRespDto(Company company) {
            this.companyId = company.getCompanyId();
            this.companyName = company.getCompanyPhoneNumber();
            this.companyPhoneNumber = company.getCompanyPhoneNumber();
            this.companyEmail = company.getCompanyEmail();
            this.companyPicture = company.getCompanyPicture();
            this.companyAddress = company.getCompanyAddress();
        }

        private Integer count;
    }

}
