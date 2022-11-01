package site.metacoding.miniproject.dto.resumes;

import lombok.Getter;
import lombok.Setter;

public class ResumesReqDto {

    @Setter
    @Getter
    public class ResumesInsertReqDto {
        private Boolean oneYearLess;
        private Boolean twoYearOver;
        private Boolean threeYearOver;
        private Boolean fiveYearOver;
        private String portfolioSource;
        private String portfolioFile;
        private String resumesTitle;
        private String resumesPicture;
        private String resumesIntroduce;
        private String resumesPlace;
        private Boolean categoryFrontend;
        private Boolean categoryBackend;
        private Boolean categoryDevops;

        private Integer personalId;
        private Integer CareerId;
        private Integer PortfolioId;
        private Integer resumesCategoryId;
    }
}
