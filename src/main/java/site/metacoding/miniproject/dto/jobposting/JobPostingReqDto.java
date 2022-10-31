package site.metacoding.miniproject.dto.jobposting;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class JobPostingReqDto {

    @Getter
    @Setter
    public static class JobPostingBoardInsertDto { // JobPostingBoardInsertDto -> JobPostingInsertReqDto
        private Integer companyId;
        private Integer jobPostingSalary;
        private String jobPostingBoardTitle;
        private String jobPostingBoardContent;
        private String jobPostingBoardPlace;
        // 경력
        private Integer jobPostingBoardCareerId;
        private Boolean oneYearLess;
        private Boolean twoYearOver;
        private Boolean threeYearOver;
        private Boolean fiveYearOver;
        // 관심 카테고리
        private Integer jobPostingBoardCategoryId;
        private Boolean categoryFrontend;
        private Boolean categoryBackend;
        private Boolean categoryDevops;

        private Timestamp jobPostingBoardDeadline;
    }

    @Getter
    @Setter
    public static class JobPostingBoardUpdateDto { // JobPostingBoardUpdateDto -> JobPostingUpdateReqDto
        // postingBoard 테이블
        private String jobPostingBoardTitle;
        private String jobPostingBoardContent;
        private Integer jobPostingSalary;
        private String jobPostingBoardPlace;
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

        private Integer jobPostingBoardCategoryId;
        private Integer jobPostingBoardCareerId;
    }
}