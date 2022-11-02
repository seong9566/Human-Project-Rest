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
	@Builder
	public Resumes(int resumesId, String resumesTitle, String resumesPicture, String resumesIntroduce,
			String resumesPlace) {
		this.resumesId = resumesId;
		this.resumesTitle = resumesTitle;
		this.resumesPicture = resumesPicture;
		this.resumesIntroduce = resumesIntroduce;
		this.resumesPlace = resumesPlace;
	}
}
