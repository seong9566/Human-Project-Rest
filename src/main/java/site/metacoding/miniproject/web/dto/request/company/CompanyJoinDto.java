package site.metacoding.miniproject.web.dto.request.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyJoinDto {
    private String loginId;
    private String loginPassword;
    private String companyName;
    private String companyEmail;
    private String companyPicture;
    private String companyAddress;
    private String companyPhoneNumber;
    // 계정생성용
    private Integer companyId;
    private Integer categoryId;

}
