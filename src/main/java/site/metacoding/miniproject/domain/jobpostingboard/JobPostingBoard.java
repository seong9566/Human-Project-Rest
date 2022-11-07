package site.metacoding.miniproject.domain.jobpostingboard;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardUpdateReqDto;

@Getter
@Setter
@NoArgsConstructor
public class JobPostingBoard {
	private Integer jobPostingBoardId;
	private Integer companyId;
	private Integer jobPostingSalary;
	private Integer jobPostingBoardCareerId;
	private Integer jobPostingBoardCategoryId;
	private String jobPostingBoardTitle;
	private String jobPostingBoardContent;
	private String jobPostingBoardPlace;
	private Timestamp jobPostingBoardDeadline;
	private Timestamp createdAt;

	// TimeStamp > String
	private String formatDeadLine;
	private String state;

	@Builder
	public JobPostingBoard(Integer jobPostingBoardId, Integer companyId, Integer jobPostingSalary,
			Integer jobPostingBoardCareerId, Integer jobPostingBoardCategoryId, String jobPostingBoardTitle,
			String jobPostingBoardContent, String jobPostingBoardPlace, Timestamp jobPostingBoardDeadline,
			Timestamp createdAt) {
		this.jobPostingBoardId = jobPostingBoardId;
		this.companyId = companyId;
		this.jobPostingSalary = jobPostingSalary;
		this.jobPostingBoardCareerId = jobPostingBoardCareerId;
		this.jobPostingBoardCategoryId = jobPostingBoardCategoryId;
		this.jobPostingBoardTitle = jobPostingBoardTitle;
		this.jobPostingBoardContent = jobPostingBoardContent;
		this.jobPostingBoardPlace = jobPostingBoardPlace;
		this.jobPostingBoardDeadline = jobPostingBoardDeadline;
		this.createdAt = createdAt;
	}

	public JobPostingBoard(Integer jobPostingBoardId, JobPostingBoardUpdateReqDto jobPostingBoardUpdateReqDto) {
		this.jobPostingBoardId = jobPostingBoardId;
		this.jobPostingSalary = jobPostingBoardUpdateReqDto.getJobPostingSalary();
		this.jobPostingBoardTitle = jobPostingBoardUpdateReqDto.getJobPostingBoardTitle();
		this.jobPostingBoardContent = jobPostingBoardUpdateReqDto.getJobPostingBoardContent();
		this.jobPostingBoardPlace = jobPostingBoardUpdateReqDto.getJobPostingBoardPlace();
		this.jobPostingBoardDeadline = jobPostingBoardUpdateReqDto.getJobPostingBoardDeadline();
	}

	public JobPostingBoard(Integer jobPostingBoardId, Integer companyId, Integer jobPostingBoardCareerId,
			Integer jobPostingBoardCategoryId, String jobPostingBoardTitle, Timestamp jobPostingBoardDeadline,
			Timestamp createdAt) {

		this.jobPostingBoardId = jobPostingBoardId;
		this.companyId = companyId;
		this.jobPostingBoardCareerId = jobPostingBoardCareerId;
		this.jobPostingBoardCategoryId = jobPostingBoardCategoryId;
		this.jobPostingBoardTitle = jobPostingBoardTitle;
		this.jobPostingBoardDeadline = jobPostingBoardDeadline;
		this.createdAt = createdAt;

	}

	public void updateJobPostingBoard(JobPostingBoardUpdateReqDto jobPostingBoardUpdateReqDto) {
		this.jobPostingBoardTitle = jobPostingBoardUpdateReqDto.getJobPostingBoardTitle();
		this.jobPostingSalary = jobPostingBoardUpdateReqDto.getJobPostingSalary();
		this.jobPostingBoardContent = jobPostingBoardUpdateReqDto.getJobPostingBoardContent();
		this.jobPostingBoardDeadline = jobPostingBoardUpdateReqDto.getJobPostingBoardDeadline();
	}

}
