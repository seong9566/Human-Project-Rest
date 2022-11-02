package site.metacoding.miniproject.dto.jobpostingboard;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.career.Career;
import site.metacoding.miniproject.domain.category.Category;
import site.metacoding.miniproject.domain.jobpostingboard.JobPostingBoard;

public class JobPostingBoardRespDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class JobPostingBoardDetailRespDto {

        // postingBoard 테이블
        private Integer jobPostingBoardId;
        private Integer companyId;
        private Integer jobPostingBoardCategoryId;
        private Integer jobPostingBoardCareerId;
        private String jobPostingBoardTitle;
        private String jobPostingBoardContent;
        private Integer jobPostingSalary;
        private String jobPostingBoardPlace;
        private String jobPostingBoardPicture;
        private Timestamp jobPostingBoardDeadline;

        // Category테이블
        private Boolean categoryFrontend;
        private Boolean categoryBackend;
        private Boolean categoryDevops;

        // Career테이블
        private Boolean oneYearLess;
        private Boolean twoYearOver;
        private Boolean threeYearOver;
        private Boolean fiveYearOver;
        // company테이블
        private String companyPicture;
        private String companyName;
        private String companyEmail;
        private String companyPhoneNumber;
        // company_like 테이블
        private Integer companyLikeId;
        // TimeStamp > String
        private String formatDeadLine;

        // ID
        private Integer categoryId;
        private Integer careerId;

        public JobPostingBoardDetailRespDto(JobPostingBoard jobPostingBoard) {
            this.jobPostingBoardId = jobPostingBoard.getJobPostingBoardId();
            this.companyId = jobPostingBoard.getCompanyId();
            this.jobPostingBoardCategoryId = jobPostingBoard.getJobPostingBoardCategoryId();
            this.jobPostingBoardCareerId = jobPostingBoard.getJobPostingBoardCareerId();
            this.jobPostingBoardTitle = jobPostingBoard.getJobPostingBoardTitle();
            this.jobPostingBoardContent = jobPostingBoard.getJobPostingBoardContent();
            this.jobPostingSalary = jobPostingBoard.getJobPostingSalary();
            this.jobPostingBoardPlace = jobPostingBoard.getJobPostingBoardPlace();
            this.jobPostingBoardDeadline = jobPostingBoard.getJobPostingBoardDeadline();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class JobPostingBoardInsertRespDto {
        private Integer jobPostingSalary;
        private String jobPostingBoardTitle;
        private String jobPostingBoardContent;
        private String jobPostingBoardPlace;
        // 경력
        private Boolean oneYearLess;
        private Boolean twoYearOver;
        private Boolean threeYearOver;
        private Boolean fiveYearOver;
        // 관심 카테고리
        private Boolean categoryFrontend;
        private Boolean categoryBackend;
        private Boolean categoryDevops;

        private Timestamp jobPostingBoardDeadline;

        public JobPostingBoardInsertRespDto(JobPostingBoard jobPostingBoard, Category category, Career career) {
            this.jobPostingSalary = jobPostingBoard.getJobPostingSalary();
            this.jobPostingBoardTitle = jobPostingBoard.getJobPostingBoardTitle();
            this.jobPostingBoardContent = jobPostingBoard.getJobPostingBoardContent();
            this.jobPostingBoardPlace = jobPostingBoard.getJobPostingBoardPlace();
            this.oneYearLess = career.getOneYearLess();
            this.twoYearOver = career.getTwoYearOver();
            this.threeYearOver = career.getThreeYearOver();
            this.fiveYearOver = career.getFiveYearOver();
            this.categoryFrontend = category.getCategoryFrontend();
            this.categoryBackend = category.getCategoryBackend();
            this.categoryDevops = category.getCategoryDevops();
            this.jobPostingBoardDeadline = jobPostingBoard.getJobPostingBoardDeadline();
        }

    }
}