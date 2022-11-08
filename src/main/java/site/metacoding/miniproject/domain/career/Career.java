package site.metacoding.miniproject.domain.career;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardInsertReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardUpdateReqDto;
import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesUpdateReqDto;

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
	public void updateCareer(ResumesUpdateReqDto resumesUpdateReqDto) {
		this.oneYearLess = resumesUpdateReqDto.getOneYearLess();
		this.twoYearOver = resumesUpdateReqDto.getTwoYearOver();
		this.threeYearOver = resumesUpdateReqDto.getThreeYearOver();
		this.fiveYearOver = resumesUpdateReqDto.getFiveYearOver();
	}

	public Career(JobPostingBoardInsertReqDto jobPostingBoardInsertReqDto) {
		this.oneYearLess = jobPostingBoardInsertReqDto.getOneYearLess();
		this.twoYearOver = jobPostingBoardInsertReqDto.getTwoYearOver();
		this.threeYearOver = jobPostingBoardInsertReqDto.getThreeYearOver();
		this.fiveYearOver = jobPostingBoardInsertReqDto.getFiveYearOver();
	}

	private Integer jobPostingBoardCareerId;

	// 채용 공고 수정
	public void updateCareer(JobPostingBoardUpdateReqDto jobPostingBoardUpdateReqDto) {
		this.oneYearLess = jobPostingBoardUpdateReqDto.getOneYearLess();
		this.twoYearOver = jobPostingBoardUpdateReqDto.getTwoYearOver();
		this.threeYearOver = jobPostingBoardUpdateReqDto.getThreeYearOver();
		this.fiveYearOver = jobPostingBoardUpdateReqDto.getFiveYearOver();
	}
}
