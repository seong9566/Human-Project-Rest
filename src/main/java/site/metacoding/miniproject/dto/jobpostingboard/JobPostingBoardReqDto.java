package site.metacoding.miniproject.dto.jobpostingboard;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.career.Career;
import site.metacoding.miniproject.domain.category.Category;
import site.metacoding.miniproject.domain.jobpostingboard.JobPostingBoard;

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

                public JobPostingBoard JobPostingBoardInsertReqDtoJobPostingBoardToEntity() {
                        return JobPostingBoard.builder()
                                        .companyId(companyId)
                                        .jobPostingBoardTitle(jobPostingBoardTitle)
                                        .jobPostingBoardContent(jobPostingBoardContent)
                                        .jobPostingBoardPlace(jobPostingBoardPlace)
                                        .jobPostingSalary(jobPostingSalary)
                                        .jobPostingBoardDeadline(jobPostingBoardDeadline)
                                        .build();
                }

                public Category JobPostingBoardInsertRespDtoToCategoryEntity() {
                        return Category.builder()
                                        .categoryFrontend(categoryFrontend)
                                        .categoryBackend(categoryBackend)
                                        .categoryDevops(categoryDevops)
                                        .build();
                }

                public Career JobPostingBoardInsertRespDtoToCareerEntity() {
                        return Career.builder()
                                        .oneYearLess(oneYearLess)
                                        .twoYearOver(twoYearOver)
                                        .threeYearOver(threeYearOver)
                                        .fiveYearOver(fiveYearOver)
                                        .build();
                }
        }

        @Getter
        @Setter
        public static class JobPostingBoardUpdateReqDto {

                // postingBoard 테이블

                private Integer jobPostingBoardId;

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

                private Integer CategoryId;
                private Integer CareerId;

                public JobPostingBoard jobPostingBoardUpdate() {
                        return JobPostingBoard.builder()
                                        .jobPostingBoardTitle(jobPostingBoardTitle)
                                        .jobPostingBoardContent(jobPostingBoardContent)
                                        .jobPostingSalary(jobPostingSalary)
                                        .jobPostingBoardPlace(jobPostingBoardPlace)
                                        .jobPostingBoardDeadline(jobPostingBoardDeadline)
                                        .jobPostingBoardId(jobPostingBoardId)
                                        .build();
                }

                public Category jobPostingUpdateReqDtoToCategoryEntity() {
                        return Category.builder()
                                        .categoryBackend(categoryBackend)
                                        .categoryDevops(categoryDevops)
                                        .categoryFrontend(categoryFrontend)
                                        .build();
                }

                public Career jobPostingUpdateReqDtoToCareerEntity() {
                        return Career.builder()
                                        .oneYearLess(oneYearLess)
                                        .twoYearOver(twoYearOver)
                                        .threeYearOver(threeYearOver)
                                        .fiveYearOver(fiveYearOver)

                                        .build();
                }

        }

}
