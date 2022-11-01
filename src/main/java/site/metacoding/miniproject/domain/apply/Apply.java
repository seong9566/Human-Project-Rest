package site.metacoding.miniproject.domain.apply;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apply {
	private Integer applyId;
	private Integer jobPostiongBoardId;
	private Integer resumesId;
	private Integer alarmId;
	private Timestamp createdAt;
}
