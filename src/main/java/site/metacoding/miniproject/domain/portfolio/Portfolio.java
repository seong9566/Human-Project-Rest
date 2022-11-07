package site.metacoding.miniproject.domain.portfolio;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@Builder
	public Portfolio(Integer portfolioId, String portfolioSource, String portfolioFile) {
		this.portfolioId = portfolioId;
		this.portfolioSource = portfolioSource;
		this.portfolioFile = portfolioFile;
	}

}
