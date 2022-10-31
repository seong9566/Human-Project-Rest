package site.metacoding.miniproject.domain.jobpostingboard;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.jobposting.JobPostingReqDto.JobPostingBoardInsertDto;
import site.metacoding.miniproject.dto.jobposting.JobPostingReqDto.JobPostingBoardUpdateDto;

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

	public JobPostingBoard(JobPostingBoardInsertDto jobPostingBoardInsertDto) {
		this.jobPostingSalary = jobPostingBoardInsertDto.getJobPostingSalary();
		this.jobPostingBoardTitle = jobPostingBoardInsertDto.getJobPostingBoardTitle();
		this.jobPostingBoardContent = jobPostingBoardInsertDto.getJobPostingBoardContent();
		this.jobPostingBoardPlace = jobPostingBoardInsertDto.getJobPostingBoardPlace();
		this.jobPostingBoardDeadline = jobPostingBoardInsertDto.getJobPostingBoardDeadline();
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
