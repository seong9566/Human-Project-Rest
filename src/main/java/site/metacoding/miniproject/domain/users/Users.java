package site.metacoding.miniproject.domain.users;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalUpdatReqDto;

@Getter
@Setter
@NoArgsConstructor
public class Users {
	private Integer usersId;
	private String loginId;
	private String loginPassword;
	private Integer personalId;
	private Integer companyId;
	private Timestamp createdAt;

	@Builder
	public Users(Integer usersId, String loginId, String loginPassword, Integer personalId, Integer companyId,
			Timestamp createdAt) {
		this.usersId = usersId;
		this.loginId = loginId;
		this.loginPassword = loginPassword;
		this.personalId = personalId;
		this.companyId = companyId;
		this.createdAt = createdAt;
	}

	public void update(PersonalUpdatReqDto personalUpdatReqDto) {
		this.loginPassword = personalUpdatReqDto.getLoginPassword();
	}

	public void update(Users companyPassWordUpdateReqDto) {
		this.loginPassword = companyPassWordUpdateReqDto.getLoginPassword();
	}
}
