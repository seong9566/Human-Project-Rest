package site.metacoding.miniproject.dto.resume;

import lombok.Getter;
import lombok.Setter;

public class ResumeReqDto {

    @Setter
    @Getter
    public class ResumesInsertDto { // ResumesInsertDto -> ResumeInsertReqDto
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

    @Setter
    @Getter
    public static class ResumesUpdateDto { // ResumesUpdateDto -> ResumesUpdateReqDto
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

        private Integer resumesId;
        private Integer careerId;
        private Integer portfolioId;
        private Integer categoryId;
        private Integer resumesCategoryId;

        // public Resumes updateResumesEntity() {
        // Resumes resumes = new Resumes();
        // resumes.setResumesTitle(this.resumesTitle);
        // resumes.setResumesPicture(this.resumesPicture);
        // resumes.setResumesIntroduce(this.resumesIntroduce);
        // return resumes;
        // }

    }

}
