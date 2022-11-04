package site.metacoding.miniproject.domain.company;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;

@Getter
@Setter
@NoArgsConstructor
public class Company {
	private Integer companyId;
	private String companyName;
	private String companyPicture;
	private String companyEmail;
	private String companyPhoneNumber;
	private String companyAddress;
	private Timestamp createdAt;

	@Builder
	public Company(Integer companyId, String companyName, String companyPicture, String companyEmail,
			String companyPhoneNumber, String companyAddress, Timestamp createdAt) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyPicture = companyPicture;
		this.companyEmail = companyEmail;
		this.companyPhoneNumber = companyPhoneNumber;
		this.companyAddress = companyAddress;
		this.createdAt = createdAt;
	}

	public void updateCompany(CompanyUpdateReqDto companyUpdateReqDto) {
		this.companyName = companyUpdateReqDto.getCompanyName();
		this.companyPicture = companyUpdateReqDto.getCompanyPicture();
		this.companyEmail = companyUpdateReqDto.getCompanyEmail();
		this.companyPhoneNumber = companyUpdateReqDto.getCompanyPhoneNumber();
		this.companyAddress = companyUpdateReqDto.getCompanyAddress();
	}

}
