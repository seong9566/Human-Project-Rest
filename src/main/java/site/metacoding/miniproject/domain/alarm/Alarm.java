package site.metacoding.miniproject.domain.alarm;

import java.security.Timestamp;
import java.util.HashMap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.utill.AlarmEnum;

@Getter
@Setter
@NoArgsConstructor
public class Alarm {
	private Integer alarmId;
	private Integer usersId;
	private Integer alarmApplyId;
	private Integer alarmIncruitId;
	private Integer alarmSubscribeId;
	private Integer alarmCompanyLikeId;
	private Integer alarmPersonalLikeId;
	private String alarmMessage;
	private Boolean alarmCheck;
	private Timestamp createdAt;

	@Builder
	public Alarm(Integer alarmId, Integer usersId, Integer alarmApplyId, Integer alarmIncruitId,
			Integer alarmSubscribeId, Integer alarmCompanyLikeId, Integer alarmPersonalLikeId, String alarmMessage,
			Boolean alarmCheck, Timestamp createdAt) {
		this.alarmId = alarmId;
		this.usersId = usersId;
		this.alarmApplyId = alarmApplyId;
		this.alarmIncruitId = alarmIncruitId;
		this.alarmSubscribeId = alarmSubscribeId;
		this.alarmCompanyLikeId = alarmCompanyLikeId;
		this.alarmPersonalLikeId = alarmPersonalLikeId;
		this.alarmMessage = alarmMessage;
		this.alarmCheck = alarmCheck;
		this.createdAt = createdAt;
	}

	// null값 방지용 알람삽입 생성자
	public Alarm(Integer usersId, HashMap<String, Integer> map, String username) {
		this.usersId = usersId;
		this.alarmApplyId = map.get("alarmApplyId") == null ? null : map.get("alarmApplyId");
		this.alarmIncruitId = map.get("alarmIncruitId") == null ? null : map.get("alarmIncruitId");
		this.alarmSubscribeId = map.get("alarmSubscribeId") == null ? null : map.get("alarmSubscribeId");
		this.alarmCompanyLikeId = map.get("alarmCompanyLikeId") == null ? null : map.get("alarmCompanyLikeId");
		this.alarmPersonalLikeId = map.get("alarmPersonalLikeId") == null ? null : map.get("alarmPersonalLikeId");
		map.forEach((key, value) -> {
			if (AlarmEnum.findBy(key.toUpperCase()).AlarmMessage(key, username) != null)
				this.alarmMessage = AlarmEnum.findBy(key.toUpperCase()).AlarmMessage(key, username);
		});
		this.alarmCheck = false;
	}
}
