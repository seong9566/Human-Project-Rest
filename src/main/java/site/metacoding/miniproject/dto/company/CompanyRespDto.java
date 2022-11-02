package site.metacoding.miniproject.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        private Integer count;

        private Integer companyForAddressId;
        private String zoneCode;
        private String roadJibunAddr;
        private String detailAddress;

        public CompanyDetailRespDto(CompanyDetailRespDto companyDetailRespDto,
                CompanyAddressRespDto companyAddressRespDto) {
            this.companyId = companyDetailRespDto.getCompanyId();
            this.companyName = companyDetailRespDto.getCompanyName();
            this.companyPhoneNumber = companyDetailRespDto.getCompanyPhoneNumber();
            this.companyEmail = companyDetailRespDto.getCompanyEmail();
            this.companyPicture = companyDetailRespDto.getCompanyPicture();
            this.count = companyDetailRespDto.getCount();

            this.companyForAddressId = companyAddressRespDto.getCompanyId();
            this.zoneCode = companyAddressRespDto.getZoneCode();
            this.roadJibunAddr = companyAddressRespDto.getRoadJibunAddr();
            this.detailAddress = companyAddressRespDto.getDetailAddress();
        }

    }

}
