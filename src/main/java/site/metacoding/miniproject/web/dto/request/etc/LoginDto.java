package site.metacoding.miniproject.web.dto.request.etc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;

@Getter
@NoArgsConstructor
public class LoginDto {
	private String loginId;
	private String loginPassword;

	public LoginDto(PersonalJoinDto joindto) {
		this.loginId = joindto.getLoginId();
		this.loginPassword = joindto.getLoginPassword();
	}

	public LoginDto(CompanyJoinDto joindto) {
		this.loginId = joindto.getLoginId();
		this.loginPassword = joindto.getLoginPassword();
	}

}
