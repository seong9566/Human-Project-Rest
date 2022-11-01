package site.metacoding.miniproject.web.dto.request.resume;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResumesInsertDto {
	private Boolean oneYearLess;
	private Boolean twoYearOver;
	private Boolean threeYearOver;
	private Boolean fiveYearOver;
	private String portfolioSource;
	private String portfolioFile;
	private String resumesTitle;
	private String resumesPicture;
	private String resumesIntroduce;
	private String resumesPlace;
	private Boolean categoryFrontend;
	private Boolean categoryBackend;
	private Boolean categoryDevops;

	private Integer personalId;
	private Integer CareerId;
	private Integer PortfolioId;
	private Integer resumesCategoryId;
}
