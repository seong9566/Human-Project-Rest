package site.metacoding.miniproject.domain.portfolio;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesInsertReqDto;
import site.metacoding.miniproject.web.dto.request.resume.ResumesUpdateDto;

@Getter
@Setter
public class Portfolio {
	private Integer portfolioId;
	private String portfolioSource;
	private String portfolioFile;
	private Timestamp createdAt;

	// 이력서 작성
	public Portfolio(ResumesInsertReqDto resumesInsertReqDto) {
		this.portfolioSource = resumesInsertReqDto.getPortfolioSource();
		this.portfolioFile = resumesInsertReqDto.getPortfolioFile();
	}

	// 이력서 수정
	public Portfolio(Integer portfolioId, ResumesUpdateDto updateResumesDto) {
		this.portfolioId = portfolioId;
		this.portfolioSource = updateResumesDto.getPortfolioSource();
		this.portfolioFile = updateResumesDto.getPortfolioFile();
	}

}
