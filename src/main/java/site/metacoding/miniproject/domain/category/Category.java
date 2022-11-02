package site.metacoding.miniproject.domain.category;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardInsertDto;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardUpdateDto;
import site.metacoding.miniproject.web.dto.request.resume.ResumesUpdateDto;

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
	public Category(Integer categoryId, ResumesUpdateDto updateResumesDto) {
		this.categoryId = categoryId;
		this.categoryFrontend = updateResumesDto.getCategoryFrontend();
		this.categoryBackend = updateResumesDto.getCategoryBackend();
		this.categoryDevops = updateResumesDto.getCategoryDevops();
	}

	// 채용 공고 작성
	public Category(JobPostingBoardInsertDto insertDto) {
		this.categoryFrontend = insertDto.getCategoryFrontend();
		this.categoryBackend = insertDto.getCategoryBackend();
		this.categoryDevops = insertDto.getCategoryDevops();
	}

	private Integer jobPostingBoardCategoryId;

	// 채용 공고 수정
	public Category(JobPostingBoardUpdateDto updateDto) {
		this.jobPostingBoardCategoryId = updateDto.getJobPostingBoardCategoryId();
		this.categoryFrontend = updateDto.getCategoryFrontend();
		this.categoryBackend = updateDto.getCategoryBackend();
		this.categoryDevops = updateDto.getCategoryDevops();
	}
}
