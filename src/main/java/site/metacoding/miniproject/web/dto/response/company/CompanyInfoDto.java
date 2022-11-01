package site.metacoding.miniproject.web.dto.response.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyInfoDto {
	private Integer usersId;
	private Integer companyId;
	private String loginId;
	private String loginPassword;
	private String companyName;
	private String companyPhoneNumber;
	private String companyEmail;
	private String companyPicture;
	private String companyAddress;

	private Integer count;
}
