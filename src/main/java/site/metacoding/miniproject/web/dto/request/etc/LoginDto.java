package site.metacoding.miniproject.web.dto.request.etc;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {
	private String loginId;
	private String loginPassword;


	public LoginDto(String loginId, String loginPassword) {
		this.loginId = loginId;
		this.loginPassword = loginPassword;
	}


}
