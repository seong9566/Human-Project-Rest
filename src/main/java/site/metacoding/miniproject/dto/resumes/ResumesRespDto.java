package site.metacoding.miniproject.dto.resumes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.career.Career;
import site.metacoding.miniproject.domain.category.Category;
import site.metacoding.miniproject.domain.portfolio.Portfolio;
import site.metacoding.miniproject.domain.resumes.Resumes;

public class ResumesRespDto {

    @NoArgsConstructor
    @Setter
    @Getter
    public static class ResumesInsertRespDto {
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

        public ResumesInsertRespDto(Resumes resumes, Category category, Career career, Portfolio portfolio) {
            this.resumesTitle = resumes.getResumesTitle();
            this.resumesPicture = resumes.getResumesPicture();
            this.resumesIntroduce = resumes.getResumesIntroduce();
            this.resumesPlace = resumes.getResumesPlace();
            this.categoryFrontend = category.getCategoryFrontend();
            this.categoryBackend = category.getCategoryBackend();
            this.categoryDevops = category.getCategoryDevops();
            this.oneYearLess = career.getOneYearLess();
            this.twoYearOver = career.getTwoYearOver();
            this.threeYearOver = career.getThreeYearOver();
            this.fiveYearOver = career.getFiveYearOver();
            this.portfolioSource = portfolio.getPortfolioSource();
            this.portfolioFile = portfolio.getPortfolioFile();
        }

    }

    @NoArgsConstructor
    @Setter
    @Getter
    public static class ResumesAllRespDto {
        private String resumesTitle;
        private String resumesPlace;

        private Integer personalId;

        public ResumesAllRespDto(Resumes resumes) {
            this.resumesTitle = resumes.getResumesTitle();
            this.resumesPlace = resumes.getResumesPlace();
        }
    }

}
