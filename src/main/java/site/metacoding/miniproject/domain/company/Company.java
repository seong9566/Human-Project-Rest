package site.metacoding.miniproject.domain.company;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.web.dto.request.company.CompanyUpdateDto;

@Getter
@Setter
@NoArgsConstructor
public class Company implements Serializable {
	private Integer companyId;
	private String companyName;
	private String companyPicture;
	private String companyEmail;
	private String companyPhoneNumber;
	private String companyAddress;
	private Timestamp createdAt;

	private static final long serialVersionUID = 7364337982660485087L;

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
