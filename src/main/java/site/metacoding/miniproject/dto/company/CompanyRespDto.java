package site.metacoding.miniproject.dto.company;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;

public class CompanyRespDto {

    @Getter
    @Setter
    public static class CompanyAddressRespDto {
        private Integer companyId;
        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

        @Builder
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
        private Integer usersId;
        private Integer companyId;
        private String loginId;
        private String loginPassword;
        private String companyName;
        private String companyPhoneNumber;
        private String companyEmail;
        private String companyPicture;
        private String companyAddress;
        private Integer count;

        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

        public CompanyDetailRespDto(Company company, CompanyAddressRespDto companyAddressRespDto) {
            this.companyId = company.getCompanyId();
            this.companyName = company.getCompanyPhoneNumber();
            this.companyPhoneNumber = company.getCompanyPhoneNumber();
            this.companyEmail = company.getCompanyEmail();
            this.companyPicture = company.getCompanyPicture();
            this.companyAddress = company.getCompanyAddress();
            this.zoneCode = companyAddressRespDto.getZoneCode();
            this.roadJibunAddr = companyAddressRespDto.getRoadJibunAddr();
            this.detailAddress = companyAddressRespDto.getDetailAddress();
        }

    }

}
