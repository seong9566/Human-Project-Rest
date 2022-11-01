package site.metacoding.miniproject.domain.category;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardInsertDto;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardUpdateDto;
import site.metacoding.miniproject.web.dto.request.resume.ResumesInsertDto;
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
	public Category(ResumesInsertDto insertResumesDto) {
		this.categoryFrontend = insertResumesDto.getCategoryFrontend();
		this.categoryBackend = insertResumesDto.getCategoryBackend();
		this.categoryDevops = insertResumesDto.getCategoryDevops();
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
