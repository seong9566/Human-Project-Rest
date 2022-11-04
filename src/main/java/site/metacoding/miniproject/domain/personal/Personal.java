package site.metacoding.miniproject.domain.personal;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalUpdatReqDto;

@Getter
@Setter
@NoArgsConstructor
public class Personal{
	private Integer personalId;
	private String personalName;
	private String personalEmail;
	private String personalEducation;
	private String personalPhoneNumber;
	private String personalAddress;
	private Timestamp createdAt;

	@Builder
	public Personal(Integer personalId, String personalName, String personalEmail, String personalEducation,
			String personalPhoneNumber, String personalAddress, Timestamp createdAt) {
		this.personalId = personalId;
		this.personalName = personalName;
		this.personalEmail = personalEmail;
		this.personalEducation = personalEducation;
		this.personalPhoneNumber = personalPhoneNumber;
		this.personalAddress = personalAddress;
		this.createdAt = createdAt;
	}

	public void updatePersonal(PersonalUpdatReqDto personalUpdatReqDto) {
		this.personalName = personalUpdatReqDto.getPersonalName();
		this.personalPhoneNumber = personalUpdatReqDto.getPersonalPhoneNumber();
		this.personalEmail = personalUpdatReqDto.getPersonalEmail();
		this.personalEducation = personalUpdatReqDto.getPersonalEducation();
		this.personalAddress = personalUpdatReqDto.getPersonalAddress();
	}

}
