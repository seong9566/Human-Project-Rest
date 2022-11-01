package site.metacoding.miniproject.web.dto.response.etc;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignedDto<T> implements Serializable {
	private Integer usersId;
	private String loginId;
	private String loginPassword;
	//AlarmDto 넣기
	private T Userinfo;

	private static final long serialVersionUID = 7364337982660485087L;
}
