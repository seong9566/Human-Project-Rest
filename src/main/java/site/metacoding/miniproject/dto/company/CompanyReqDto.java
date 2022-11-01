package site.metacoding.miniproject.dto.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.config.handler.exception.ApiException;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.users.Users;

public class CompanyReqDto {
    
@Getter
@Setter
public static class CompanyJoinDto {
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
    private MultipartFile file;

    public Users companyJoinDtoToUserEntity() {
        return Users.builder().loginId(loginId)
            .loginPassword(loginPassword)
            .companyId(companyId)
            .build();
    }

    public Company companyJoinDtoToCompanyEntity() {
        return Company.builder()
                .companyName(companyName)
                .companyPhoneNumber(companyPhoneNumber)
                .companyAddress(companyAddress)
                .companyPicture(companyPicture)
                .companyEmail(companyEmail)
                .build();
    }
    
    public void companyJoinDtoPictureSet() throws Exception {
        int pos = file.getOriginalFilename().lastIndexOf('.');
		String extension = file.getOriginalFilename().substring(pos + 1);
		String filePath = "C:\\Temp\\img\\";
		// String filePath = "/Users/ihyeonseong/Desktop/img";//Mac전용 경로
		String imgSaveName = UUID.randomUUID().toString();
		String imgName = imgSaveName + "." + extension;
		File makeFileFolder = new File(filePath);
		if (!makeFileFolder.exists()) {
			if (!makeFileFolder.mkdir()) {
				throw new ApiException("File.mkdir():Fail.");
			}
		}
		File dest = new File(filePath, imgName);
		Files.copy(file.getInputStream(), dest.toPath());
        this.companyPicture = imgName;
    }

}



}
