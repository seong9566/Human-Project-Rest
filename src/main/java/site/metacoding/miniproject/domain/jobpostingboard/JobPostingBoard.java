package site.metacoding.miniproject.domain.jobpostingboard;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardUpdateDto;

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

	public JobPostingBoard(Integer jobPostingBoardId, JobPostingBoardUpdateDto updateDto) {
		this.jobPostingBoardId = jobPostingBoardId;
		this.jobPostingSalary = updateDto.getJobPostingSalary();
		this.jobPostingBoardTitle = updateDto.getJobPostingBoardTitle();
		this.jobPostingBoardContent = updateDto.getJobPostingBoardContent();
		this.jobPostingBoardPlace = updateDto.getJobPostingBoardPlace();
		this.jobPostingBoardDeadline = updateDto.getJobPostingBoardDeadline();
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

}
