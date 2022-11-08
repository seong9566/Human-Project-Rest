package site.metacoding.miniproject.dto.resumes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.career.Career;
import site.metacoding.miniproject.domain.category.Category;
import site.metacoding.miniproject.domain.personal.Personal;
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
    public static class ResumesAllByIdRespDto {
        private String resumesTitle;
        private String resumesPlace;

        private Integer personalId;

        public ResumesAllByIdRespDto(Resumes resumes) {
            this.resumesTitle = resumes.getResumesTitle();
            this.resumesPlace = resumes.getResumesPlace();
            this.personalId = resumes.getPersonalId();
        }
    }

    @NoArgsConstructor
    @Setter
    @Getter
    public static class ResumesDetailRespDto {
        // personal
        private String personalName;
        private String personalEmail;
        private String personalEducation;
        private String personalPhoneNumber;
        // resumes
        private String resumesTitle;
        private String resumesPicture;
        private String resumesIntroduce;
        private String resumesPlace;
        // career
        private Boolean oneYearLess;
        private Boolean twoYearOver;
        private Boolean threeYearOver;
        private Boolean fiveYearOver;
        // portfolio
        private String portfolioSource;
        private String portfolioFile;
        // category
        private Boolean categoryFrontend;
        private Boolean categoryBackend;
        private Boolean categoryDevops;

        private Integer personalId;
        private Integer resumesId;
        private Integer careerId;
        private Integer portfolioId;
        private Integer resumesCategoryId;

        public ResumesDetailRespDto(Resumes resumes, Category category, Career career, Portfolio portfolio,
                Personal personal) {
            this.personalId = personal.getPersonalId();
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
            this.personalName = personal.getPersonalName();
            this.personalEmail = personal.getPersonalEmail();
            this.personalEducation = personal.getPersonalEducation();
            this.personalPhoneNumber = personal.getPersonalPhoneNumber();
        }
    }

    @NoArgsConstructor
    @Setter
    @Getter
    public static class ResumesUpdateRespDto {
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

        public ResumesUpdateRespDto(Resumes resumes, Category category, Career career, Portfolio portfolio) {
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

        private Integer page;
        private Integer startNum;
        private String keyword;

        private Integer id;

        public ResumesAllRespDto(Resumes resumes) {
            this.resumesTitle = resumes.getResumesTitle();
            this.resumesPlace = resumes.getResumesPlace();
        }
    }

    @Setter
    @Getter
    public static class PagingDto {
        private Integer totalCount;
        private Integer totalPage;
        private Integer currentPage;
        // private boolean isFirst;
        // private boolean isLast;

        private Integer blockCount;
        private Integer currentBlock;
        private Integer startPageNum;
        private Integer lastPageNum;

        private String keyword;

        public void makeBlockInfo(String keyword) {
            this.keyword = keyword;
            this.blockCount = 5;
            this.currentBlock = currentPage / blockCount;
            this.startPageNum = 1 + blockCount * currentBlock;
            this.lastPageNum = 5 + blockCount * currentBlock;

            if (totalPage < lastPageNum) {
                this.lastPageNum = totalPage;
            }
        }
    }
}
