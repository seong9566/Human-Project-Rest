package site.metacoding.miniproject.domain.alarm;

import java.security.Timestamp;
import java.util.HashMap;

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

	public Alarm(Integer usersId, HashMap<String, Integer> map, String username) {
		this.usersId = usersId;
		this.alarmApplyId = map.get("alarmApplyId") == null ? 0 : map.get("alarmApplyId");
		this.alarmIncruitId = map.get("alarmIncruitId") == null ? 0 : map.get("alarmIncruitId");
		this.alarmSubscribeId = map.get("alarmSubscribeId") == null ? 0 : map.get("alarmSubscribeId");
		this.alarmCompanyLikeId = map.get("alarmCompanyLikeId") == null ? 0 : map.get("alarmCompanyLikeId");
		this.alarmPersonalLikeId = map.get("alarmPersonalLikeId") == null ? 0 : map.get("alarmPersonalLikeId");
		map.forEach((key, value) -> {
			if (AlarmEnum.findBy(key.toUpperCase()).AlarmMessage(key, username) != null)
				this.alarmMessage = AlarmEnum.findBy(key.toUpperCase()).AlarmMessage(key, username);
		});
		this.alarmCheck = false;
	}
}
