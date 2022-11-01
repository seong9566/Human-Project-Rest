package site.metacoding.miniproject.domain.personal;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.personal.PersonalJoinDto;
import site.metacoding.miniproject.web.dto.request.personal.PersonalUpdateDto;

@Getter
@Setter
@NoArgsConstructor
public class Personal implements Serializable {
	private Integer personalId;
	private String personalName;
	private String personalEmail;
	private String personalEducation;
	private String personalPhoneNumber;
	private String personalAddress;
	private Timestamp createdAt;

	private static final long serialVersionUID = 7364337982660485087L; // 시리얼 아이디 - 레디스 서버와 통신을 위함

	public Personal(PersonalJoinDto joinDto) {
		this.personalName = joinDto.getPersonalName();
		this.personalEmail = joinDto.getPersonalEmail();
		this.personalEducation = joinDto.getPersonalEducation();
		this.personalPhoneNumber = joinDto.getPersonalPhoneNumber();
		this.personalAddress = joinDto.getPersonalAddress();
	}

	public void updatePersonal(PersonalUpdateDto personalUpdateDto) {
		this.personalName = personalUpdateDto.getPersonalName();
		this.personalPhoneNumber = personalUpdateDto.getPersonalPhoneNumber();
		this.personalEmail = personalUpdateDto.getPersonalEmail();
		this.personalEducation = personalUpdateDto.getPersonalEducation();
		this.personalAddress = personalUpdateDto.getPersonalAddress();
	}

}
