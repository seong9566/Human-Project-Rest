package site.metacoding.miniproject.dto.jobposting;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class JobPostingRespDto {

    @Getter
    @Setter
    public static class JobPostingBoardDetailDto { // JobPostingBoardDetailDto -> JobPostingDetailRespDto
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

    }

    @Getter
    @Setter
    public class JobPostingBoardListDto { // JobPostingBoardListDto -> JobPostingListRespDto
        // postingBoard 테이블
        private Integer jobPostingBoardId;
        private Integer companyId;
        private Integer jobPostingBoardCategoryId;
        private Integer jobPostingBoardCareerId;
        private String jobPostingBoardTitle;
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

        private String formatDeadLine;

    }

}