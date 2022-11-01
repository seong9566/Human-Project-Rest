package site.metacoding.miniproject.web.dto.response.personal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalAddressDto {
	private Integer personalId;
	private String zoneCode;
	private String roadJibunAddr;
	private String detailAddress;

}
