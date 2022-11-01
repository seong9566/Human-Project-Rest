package site.metacoding.miniproject.web.dto.response.jobpostingboard;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobPostingBoardDetailDto {

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
