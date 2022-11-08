package site.metacoding.miniproject.domain.portfolio;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesUpdateReqDto;

@Getter
@Setter
@NoArgsConstructor
public class Portfolio {
	private Integer portfolioId;
	private String portfolioSource;
	private String portfolioFile;
	private Timestamp createdAt;

	// 이력서 작성
	@Builder
	public Portfolio(String portfolioSource, String portfolioFile) {
		this.portfolioSource = portfolioSource;
		this.portfolioFile = portfolioFile;
	}

	// 이력서 수정
	public void updatePortfolio(ResumesUpdateReqDto resumesUpdateReqDto) {
		this.portfolioId = resumesUpdateReqDto.getPortfolioId();
		this.portfolioSource = resumesUpdateReqDto.getPortfolioSource();
		this.portfolioFile = resumesUpdateReqDto.getPortfolioFile();
	}

}
