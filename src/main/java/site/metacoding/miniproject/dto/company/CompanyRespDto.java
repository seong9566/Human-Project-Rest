package site.metacoding.miniproject.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.users.Users;

public class CompanyRespDto {

    @Getter
    @Setter
    public static class CompanyAddressRespDto {
        private Integer companyId;
        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

        public CompanyAddressRespDto(Integer companyId, String zoneCode, String roadJibunAddr, String detailAddress) {
            this.companyId = companyId;
            this.zoneCode = zoneCode;
            this.roadJibunAddr = roadJibunAddr;
            this.detailAddress = detailAddress;
        }

    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class CompanyDetailRespDto { // CompanyInfoDto -> CompanyDetailRespDto
        private Integer companyId;
        private String companyName;
        private String companyPhoneNumber;
        private String companyEmail;
        private String companyPicture;
        private String companyAddress;
        private Integer count;

        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

        public CompanyDetailRespDto(CompanyDetailRespDto companyDetailRespDto) {
            this.companyId = companyDetailRespDto.getCompanyId();
            this.companyName = companyDetailRespDto.getCompanyName();
            this.companyPhoneNumber = companyDetailRespDto.getCompanyPhoneNumber();
            this.companyEmail = companyDetailRespDto.getCompanyEmail();
            this.companyPicture = companyDetailRespDto.getCompanyPicture();
            this.count = companyDetailRespDto.getCount();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CompanyUpdateFormRespDto {
        private Integer companyId;
        private String companyName;
        private String companyPhoneNumber;
        private String companyEmail;
        private String companyPicture;
        private String companyAddress;
        private String loginPassword;

        public CompanyUpdateFormRespDto(CompanyUpdateFormRespDto companyUpdateFormRespDto) {

            this.companyId = companyUpdateFormRespDto.getCompanyId();
            this.companyName = companyUpdateFormRespDto.getCompanyName();
            this.companyEmail = companyUpdateFormRespDto.getCompanyEmail();
            this.companyPhoneNumber = companyUpdateFormRespDto.getCompanyPhoneNumber();
            this.companyAddress = companyUpdateFormRespDto.getCompanyAddress();
            this.companyPicture = companyUpdateFormRespDto.getCompanyPicture();
            this.loginPassword = companyUpdateFormRespDto.getLoginPassword();

        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CompanyUpdateRespDto {
        private Integer companyId;
        private String companyName;
        private String companyPhoneNumber;
        private String companyEmail;
        private String companyPicture;
        private String companyAddress;
        private String loginPassword;

        public CompanyUpdateRespDto(Company company, Users users) {
            this.companyId = company.getCompanyId();
            this.companyName = company.getCompanyName();
            this.companyEmail = company.getCompanyEmail();
            this.companyPhoneNumber = company.getCompanyPhoneNumber();
            this.companyPicture = company.getCompanyPicture();
            this.companyAddress = company.getCompanyAddress();

            this.loginPassword = users.getLoginPassword();
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class CompanyDetailWithPerRespDto {
        private Integer companyId;
        private String companyName;
        private String companyPhoneNumber;
        private String companyEmail;
        private String companyPicture;
        private String companyAddress;
        private Integer count;

        private Integer companyForAddressId;
        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

        public CompanyDetailWithPerRespDto(CompanyDetailWithPerRespDto companyDetailWithPerRespDto) {
            this.companyId = companyDetailWithPerRespDto.getCompanyId();
            this.companyName = companyDetailWithPerRespDto.getCompanyName();
            this.companyPhoneNumber = companyDetailWithPerRespDto.getCompanyPhoneNumber();
            this.companyEmail = companyDetailWithPerRespDto.getCompanyEmail();
            this.companyPicture = companyDetailWithPerRespDto.getCompanyPicture();
            this.companyAddress = companyDetailWithPerRespDto.getCompanyAddress();
            this.count = companyDetailWithPerRespDto.getCount();
        }

    }
}
