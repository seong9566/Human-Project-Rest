package site.metacoding.miniproject.dto.like;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.like.companylike.CompanyLike;
import site.metacoding.miniproject.domain.like.personalike.PersonalLike;

public class LikeReqDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CompanyLikeReqDto {
        private Integer companyId;
        private Integer personalId;
        private Integer alarmId;

        public CompanyLike companyLikeEntity() {
            return CompanyLike.builder()
                    .companyId(companyId)
                    .personalId(personalId)
                    .alarmId(alarmId).build();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PersonalLikeReqDto {

        private Integer resumesId;
        private Integer companyId;
        private Integer alarmId;

        public PersonalLike personalLikeEntity() {
            return PersonalLike.builder()
                    .resumesId(resumesId)
                    .companyId(companyId)
                    .alarmId(alarmId)
                    .build();
        }
    }

}
