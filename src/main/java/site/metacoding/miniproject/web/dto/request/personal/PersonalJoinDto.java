package site.metacoding.miniproject.web.dto.request.personal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalJoinDto {
	private String loginId;
	private String loginPassword;
	private String personalName;
	private String personalEmail;
	private String personalPhoneNumber;
	private String personalEducation;
	private String personalAddress;

	// Users계정 생성용
	private Integer personalId;
}
