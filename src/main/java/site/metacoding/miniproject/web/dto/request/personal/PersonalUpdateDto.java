package site.metacoding.miniproject.web.dto.request.personal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalUpdateDto {
	private Integer personalId;
	private String personalName;
	private String personalEmail;
	private String personalEducation;

	private String personalPhoneNumber;
	private String personalAddress;

	private String loginId;
	private String loginPassword;

}
