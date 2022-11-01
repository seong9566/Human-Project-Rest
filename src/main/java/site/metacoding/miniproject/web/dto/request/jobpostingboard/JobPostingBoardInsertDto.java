package site.metacoding.miniproject.web.dto.request.jobpostingboard;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.jobpostingboard.JobPostingBoard;

@Getter
@Setter
public class JobPostingBoardInsertDto {
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
