package site.metacoding.miniproject.domain.resumes;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.resume.ResumesUpdateDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resumes {
	private Integer resumesId;
	private Integer personalId;
	private Integer careerId;
	private Integer portfolioId;
	private String resumesTitle;
	private String resumesPicture;
	private String resumesIntroduce;
	private Integer resumesCategoryId;
	private String resumesPlace;
	private Timestamp createdAt;

	// 이력서 작성
	@Builder
	public Resumes(String resumesTitle, String resumesPicture, String resumesIntroduce, String resumesPlace) {
		this.resumesTitle = resumesTitle;
		this.resumesPicture = resumesPicture;
		this.resumesIntroduce = resumesIntroduce;
		this.resumesPlace = resumesPlace;
	}

	// 이력서 수정
	public Resumes(int resumesId, ResumesUpdateDto updateResumesDto) {
		this.resumesId = resumesId;
		this.resumesTitle = updateResumesDto.getResumesTitle();
		this.resumesPicture = updateResumesDto.getResumesPicture();
		this.resumesIntroduce = updateResumesDto.getResumesIntroduce();
		this.resumesPlace = updateResumesDto.getResumesPlace();
	}

	@Builder
	public Resumes(Integer resumesId, Integer personalId, String resumesTitle, String resumesPicture,
			String resumesIntroduce, Timestamp createdAt) {
		this.resumesId = resumesId;
		this.personalId = personalId;
		this.resumesTitle = resumesTitle;
		this.resumesPicture = resumesPicture;
		this.resumesIntroduce = resumesIntroduce;
		this.createdAt = createdAt;
	}

}
