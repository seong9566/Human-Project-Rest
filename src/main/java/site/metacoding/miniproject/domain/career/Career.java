package site.metacoding.miniproject.domain.career;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesInsertReqDto;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardInsertDto;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardUpdateDto;
import site.metacoding.miniproject.web.dto.request.resume.ResumesUpdateDto;

@Getter
@Setter
public class Career {
	private Integer careerId;
	private Boolean oneYearLess;
	private Boolean twoYearOver;
	private Boolean threeYearOver;
	private Boolean fiveYearOver;
	private Timestamp createdAt;

	// 이력서 작성
	public Career(ResumesInsertReqDto resumesInsertReqDto) {
		this.oneYearLess = resumesInsertReqDto.getOneYearLess();
		this.twoYearOver = resumesInsertReqDto.getTwoYearOver();
		this.threeYearOver = resumesInsertReqDto.getThreeYearOver();
		this.fiveYearOver = resumesInsertReqDto.getFiveYearOver();
	}

	// 이력서 수정
	public Career(Integer careerId, ResumesUpdateDto updateResumesDto) {
		this.careerId = careerId;
		this.oneYearLess = updateResumesDto.getOneYearLess();
		this.twoYearOver = updateResumesDto.getTwoYearOver();
		this.threeYearOver = updateResumesDto.getThreeYearOver();
		this.fiveYearOver = updateResumesDto.getFiveYearOver();
	}

	public Career(JobPostingBoardInsertDto joinDto) {
		this.oneYearLess = joinDto.getOneYearLess();
		this.twoYearOver = joinDto.getTwoYearOver();
		this.threeYearOver = joinDto.getThreeYearOver();
		this.fiveYearOver = joinDto.getFiveYearOver();
	}

	private Integer jobPostingBoardCareerId;

	// 채용 공고 수정
	public Career(JobPostingBoardUpdateDto updateDto) {
		this.jobPostingBoardCareerId = updateDto.getJobPostingBoardCareerId();
		this.oneYearLess = updateDto.getOneYearLess();
		this.twoYearOver = updateDto.getTwoYearOver();
		this.threeYearOver = updateDto.getThreeYearOver();
		this.fiveYearOver = updateDto.getFiveYearOver();
	}
}
