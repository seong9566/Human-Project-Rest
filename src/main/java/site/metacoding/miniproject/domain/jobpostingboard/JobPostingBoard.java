package site.metacoding.miniproject.domain.jobpostingboard;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardInsertReqDto;
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
	public JobPostingBoard(JobPostingBoardInsertReqDto jobPostingBoardInsertReqDto) {
		this.jobPostingSalary = jobPostingBoardInsertReqDto.getJobPostingSalary();
		this.jobPostingBoardTitle = jobPostingBoardInsertReqDto.getJobPostingBoardTitle();
		this.jobPostingBoardContent = jobPostingBoardInsertReqDto.getJobPostingBoardContent();
		this.jobPostingBoardPlace = jobPostingBoardInsertReqDto.getJobPostingBoardPlace();
		this.jobPostingBoardDeadline = jobPostingBoardInsertReqDto.getJobPostingBoardDeadline();
	}

	public JobPostingBoard(Integer jobPostingBoardId, JobPostingBoardUpdateDto updateDto) {
		this.jobPostingBoardId = jobPostingBoardId;
		this.jobPostingSalary = updateDto.getJobPostingSalary();
		this.jobPostingBoardTitle = updateDto.getJobPostingBoardTitle();
		this.jobPostingBoardContent = updateDto.getJobPostingBoardContent();
		this.jobPostingBoardPlace = updateDto.getJobPostingBoardPlace();
		this.jobPostingBoardDeadline = updateDto.getJobPostingBoardDeadline();
	}
}
