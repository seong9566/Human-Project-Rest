package site.metacoding.miniproject.dto.jobpostingboard;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class JobPostingBoardReqDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class JobPostingBoardInsertReqDto {
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

}
