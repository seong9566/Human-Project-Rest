package site.metacoding.miniproject.domain.career;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardInsertReqDto;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardUpdateDto;

@NoArgsConstructor
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
	@Builder
	public Career(Boolean oneYearLess, Boolean twoYearOver, Boolean threeYearOver, Boolean fiveYearOver) {
		this.oneYearLess = oneYearLess;
		this.twoYearOver = twoYearOver;
		this.threeYearOver = threeYearOver;
		this.fiveYearOver = fiveYearOver;
	}

	// 이력서 수정
	@Builder
	public Career(Integer careerId, Boolean oneYearLess, Boolean twoYearOver, Boolean threeYearOver,
			Boolean fiveYearOver) {
		this.careerId = careerId;
		this.oneYearLess = oneYearLess;
		this.twoYearOver = twoYearOver;
		this.threeYearOver = threeYearOver;
		this.fiveYearOver = fiveYearOver;
	}

	public Career(JobPostingBoardInsertReqDto jobPostingBoardInsertReqDto) {
		this.oneYearLess = jobPostingBoardInsertReqDto.getOneYearLess();
		this.twoYearOver = jobPostingBoardInsertReqDto.getTwoYearOver();
		this.threeYearOver = jobPostingBoardInsertReqDto.getThreeYearOver();
		this.fiveYearOver = jobPostingBoardInsertReqDto.getFiveYearOver();
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
