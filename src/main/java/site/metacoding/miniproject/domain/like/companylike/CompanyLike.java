package site.metacoding.miniproject.domain.like.companylike;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CompanyLike {
	private Integer companyLikeId;
	private Integer personalId;
	private Integer companyId;
	private Integer alarmId;
	private Timestamp createdAt;

	@Builder
	public CompanyLike(Integer companyId, Integer personalId, Integer alarmId) {
		this.companyId = companyId;
		this.personalId = personalId;
		this.alarmId = alarmId;
	}

}
