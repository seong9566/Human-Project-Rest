package site.metacoding.miniproject.web.dto.request.etc;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendInsertDto {
	private Integer personalLikeId;
	private Integer resumesId;
	private Integer companyId;
	private Timestamp createdAt;

}