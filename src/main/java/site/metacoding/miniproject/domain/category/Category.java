package site.metacoding.miniproject.domain.category;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardInsertReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardUpdateReqDto;
import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesUpdateReqDto;

@Getter
@Setter
@NoArgsConstructor
public class Category {
	private Integer categoryId;
	private Boolean categoryFrontend;
	private Boolean categoryBackend;
	private Boolean categoryDevops;
	private Timestamp createdAt;

	// 이력서 작성
	@Builder
	public Category(Boolean categoryFrontend, Boolean categoryBackend, Boolean categoryDevops) {
		this.categoryFrontend = categoryFrontend;
		this.categoryBackend = categoryBackend;
		this.categoryDevops = categoryDevops;
	}

	// 이력서 수정
	public void updateCategory(ResumesUpdateReqDto resumesUpdateReqDto) {
		this.categoryFrontend = resumesUpdateReqDto.getCategoryFrontend();
		this.categoryBackend = resumesUpdateReqDto.getCategoryBackend();
		this.categoryDevops = resumesUpdateReqDto.getCategoryDevops();
	}

	// 채용 공고 작성
	public Category(JobPostingBoardInsertReqDto jobPostingBoardInsertReqDto) {
		this.categoryFrontend = jobPostingBoardInsertReqDto.getCategoryFrontend();
		this.categoryBackend = jobPostingBoardInsertReqDto.getCategoryBackend();
		this.categoryDevops = jobPostingBoardInsertReqDto.getCategoryDevops();
	}

	private Integer jobPostingBoardCategoryId;

	// 채용 공고 수정
	public Category(JobPostingBoardUpdateReqDto jobPostingBoardUpdateReqDto) {
		this.categoryFrontend = jobPostingBoardUpdateReqDto.getCategoryFrontend();
		this.categoryBackend = jobPostingBoardUpdateReqDto.getCategoryBackend();
		this.categoryDevops = jobPostingBoardUpdateReqDto.getCategoryDevops();
	}

}
