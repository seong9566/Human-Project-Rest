package site.metacoding.miniproject.domain.like.personalike;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PersonalLike {
	private Integer personalLikeId;
	private Integer resumesId;
	private Integer companyId;
	private Integer alarmId;
	private Timestamp createdAt;

	@Builder
	public PersonalLike(Integer resumesId, Integer companyId, Integer alarmId) {
		this.resumesId = resumesId;
		this.companyId = companyId;
		this.alarmId = alarmId;
	}

}
