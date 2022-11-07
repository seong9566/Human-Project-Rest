package site.metacoding.miniproject.dto.resumes;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.career.Career;
import site.metacoding.miniproject.domain.category.Category;
import site.metacoding.miniproject.domain.portfolio.Portfolio;
import site.metacoding.miniproject.domain.resumes.Resumes;

public class ResumesReqDto {

        @NoArgsConstructor
        @Setter
        @Getter
        public static class ResumesInsertReqDto {
                private Boolean oneYearLess;
                private Boolean twoYearOver;
                private Boolean threeYearOver;
                private Boolean fiveYearOver;
                private String portfolioSource;
                private String portfolioFile;
                private String resumesTitle;
                private String resumesPicture;
                private String resumesIntroduce;
                private String resumesPlace;
                private Boolean categoryFrontend;
                private Boolean categoryBackend;
                private Boolean categoryDevops;

                private Integer personalId;
                private Integer careerId;
                private Integer portfolioId;
                private Integer resumesCategoryId;
                private MultipartFile file;

                // 사진
                public void ResumesInsertDtoPictureSet() throws Exception {
                        int pos = file.getOriginalFilename().lastIndexOf('.');
                        String extension = file.getOriginalFilename().substring(pos + 1);
                        String filePath = "C:\\Temp\\img\\";
                        // String filePath = "/Users/ihyeonseong/Desktop/img";//Mac전용 경로
                        String imgSaveName = UUID.randomUUID().toString();
                        String imgName = imgSaveName + "." + extension;
                        File makeFileFolder = new File(filePath);
                        if (!makeFileFolder.exists()) {
                                if (!makeFileFolder.mkdir()) {
                                        throw new RuntimeException("File.mkdir():Fail.");
                                }
                        }
                        File dest = new File(filePath, imgName);
                        Files.copy(file.getInputStream(), dest.toPath());
                        this.resumesPicture = imgName;
                }

                public Resumes ResumesInsertReqDtoToResumesEntity() {
                        return Resumes.builder()
                                        .resumesTitle(resumesTitle)
                                        .resumesPicture(resumesPicture)
                                        .resumesIntroduce(resumesIntroduce)
                                        .resumesPlace(resumesPlace)
                                        .build();
                }

                public Category ResumesInsertReqDtoToCategoryEntity() {
                        return Category.builder()
                                        .categoryFrontend(categoryFrontend)
                                        .categoryBackend(categoryBackend)
                                        .categoryDevops(categoryDevops)
                                        .build();
                }

                public Career ResumesInsertReqDtoToCareerEntity() {
                        return Career.builder()
                                        .oneYearLess(oneYearLess)
                                        .twoYearOver(twoYearOver)
                                        .threeYearOver(threeYearOver)
                                        .fiveYearOver(fiveYearOver)
                                        .build();
                }

                public Portfolio ResumesInsertReqDtoToPortfolioEntity() {
                        return Portfolio.builder()
                                        .portfolioFile(portfolioFile)
                                        .portfolioSource(portfolioSource)
                                        .build();
                }

        }

        @NoArgsConstructor
        @Setter
        @Getter
        public static class ResumesUpdateReqDto {
                private Boolean oneYearLess;
                private Boolean twoYearOver;
                private Boolean threeYearOver;
                private Boolean fiveYearOver;
                private String portfolioSource;
                private String portfolioFile;
                private String resumesTitle;
                private String resumesPicture;
                private String resumesIntroduce;
                private String resumesPlace;
                private Boolean categoryFrontend;
                private Boolean categoryBackend;
                private Boolean categoryDevops;

                private Integer resumesId;
                private Integer careerId;
                private Integer portfolioId;
                private Integer categoryId;
                private Integer resumesCategoryId;
                private MultipartFile file;

                // 사진
                public void ResumesUpdateDtoPictureSet() throws Exception {
                        int pos = file.getOriginalFilename().lastIndexOf('.');
                        String extension = file.getOriginalFilename().substring(pos + 1);
                        String filePath = "C:\\Temp\\img\\";
                        // String filePath = "/Users/ihyeonseong/Desktop/img";//Mac전용 경로
                        String imgSaveName = UUID.randomUUID().toString();
                        String imgName = imgSaveName + "." + extension;
                        File makeFileFolder = new File(filePath);
                        if (!makeFileFolder.exists()) {
                                if (!makeFileFolder.mkdir()) {
                                        throw new RuntimeException("File.mkdir():Fail.");
                                }
                        }
                        File dest = new File(filePath, imgName);
                        Files.copy(file.getInputStream(), dest.toPath());
                        this.resumesPicture = imgName;
                }

        }
}
