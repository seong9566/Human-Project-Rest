package site.metacoding.miniproject.dto.like;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.like.companylike.CompanyLike;
import site.metacoding.miniproject.domain.like.personalike.PersonalLike;
import site.metacoding.miniproject.domain.resumes.Resumes;

public class LikeReqDto {

    @Getter
    @Setter
    public static class CompanyLikeReqDto {
        private Integer companyId;
        private String companyName;
        private String companyPicture;
        private String companyEmail;
        private String companyPhoneNumber;
        private String companyAddress;
        private Timestamp createdAt;

        private Integer personalId;
        private Integer alarmId;

        public CompanyLike CompanyLikeEntity() {
            return CompanyLike.builder().companyId(companyId).personalId(personalId).alarmId(alarmId).build();
        }
    }

    @Getter
    @Setter
    public class PersonalLikeReqDto {
        private Integer resumesId;
        private Integer personalId;
        private String resumesTitle;
        private String resumesPicture;
        private String resumesIntroduce;
        private Timestamp createdAt;

        private Integer personalLikeId;
        private Integer companyId;
        private Integer alarmId;

        public Resumes PersonalLikePersonalEntity() {
            return Resumes.builder()
                    .resumesId(resumesId)
                    .personalId(personalId)
                    .resumesIntroduce(resumesIntroduce)
                    .resumesTitle(resumesTitle)
                    .resumesPicture(resumesPicture)
                    .resumesIntroduce(resumesIntroduce)
                    .createdAt(createdAt)
                    .build();
        }

        public PersonalLike PersonalLikeEntity() {
            return PersonalLike.builder()
                    .alarmId(alarmId)
                    .companyId(companyId)
                    .personalLikeId(personalLikeId)
                    .build();
        }
    }

}
