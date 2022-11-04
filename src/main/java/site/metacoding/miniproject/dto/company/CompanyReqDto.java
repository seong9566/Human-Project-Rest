package site.metacoding.miniproject.dto.company;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.exception.ApiException;
import site.metacoding.miniproject.utill.SHA256;

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

        public Company companyJoinDtoToCompanyEntity() {
            return Company.builder()
                    .companyName(companyName)
                    .companyPhoneNumber(companyPhoneNumber)
                    .companyAddress(companyAddress)
                    .companyPicture(companyPicture)
                    .companyEmail(companyEmail)
                    .build();
        }

        public Users companyJoinDtoToUserEntity() {

            SHA256 sha256 = new SHA256();
            this.loginPassword = sha256.encrypt(this.loginPassword);

            return Users.builder().loginId(loginId)
                    .loginPassword(loginPassword)
                    .companyId(companyId)
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

    @Getter
    @Setter
    public static class CompanyUpdateReqDto {

        private Integer companyId;
        private String companyName;
        private String companyEmail;
        private String companyPicture;
        private String companyAddress;
        private String companyPhoneNumber;
        private MultipartFile file;
        private String loginPassword;

        public Company companyUpdateDtoToCompanyEntity() {
            return Company.builder()
                    .companyName(companyName)
                    .companyPhoneNumber(companyPhoneNumber)
                    .companyAddress(companyAddress)
                    .companyPicture(companyPicture)
                    .companyEmail(companyEmail)
                    .build();
        }

        public Users companyPassWordUpdateReqDto() {

            SHA256 sha256 = new SHA256();
            this.loginPassword = sha256.encrypt(this.loginPassword);

            return Users.builder()
                    .companyId(companyId)
                    .loginPassword(loginPassword)
                    .build();
        }

        public void companyUpdateDtoPictureSet() throws Exception {
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
