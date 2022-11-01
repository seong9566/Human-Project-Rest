package site.metacoding.miniproject.dto.company;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;

public class CompanyRespDto {
    @Setter
    @Getter
    public static class CompanyAddressDto { // CompanyAddressDto -> CompanyAddressRespDto
        private Integer companyId;
        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;
    }

    @Setter
    @Getter
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

    @Setter
    @Getter
    public static class CompanyMainDto { // CompanyMainDto -> CompanyMainRespDto
        private Integer resumesId;
        private String resumesTitle;
        private String resumesPlace;
    }

}
