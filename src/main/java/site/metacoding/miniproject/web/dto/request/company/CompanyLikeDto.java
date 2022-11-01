package site.metacoding.miniproject.web.dto.request.company;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyLikeDto {
	private Integer companyId;
	private String companyName;
	private String companyPicture;
	private String companyEmail;
	private String companyPhoneNumber;
	private String companyAddress;
	private Timestamp createdAt;
	

	private Integer personalId;
	private Integer alarmId;
}