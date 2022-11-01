package site.metacoding.miniproject.web.dto.response.jobpostingboard;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobPostingBoardListDto {
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
