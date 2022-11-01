package site.metacoding.miniproject.web.dto.request.personal;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalLikeDto {
	// resumes
	private Integer resumesId;
	private Integer personalId;
	private String resumesTitle;
	private String resumesPicture;
	private String resumesIntroduce;
	private Timestamp createdAt;

	private Integer personalLikeId;
	private Integer companyId;
	private Integer alarmId;
}