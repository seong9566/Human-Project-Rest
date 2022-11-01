package site.metacoding.miniproject.web.dto.response.personal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalInfoDto {
	// 이력서 작성 시 보여 줄 개인정보
	private String personalName;
	private String personalEmail;
	private String personalEducation;
	private String personalPhoneNumber;
	private String personalAddress;
}
