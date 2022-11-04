package site.metacoding.miniproject.dto.like;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.like.companylike.CompanyLike;
import site.metacoding.miniproject.domain.like.personalike.PersonalLike;

public class LikeRespDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CompanyLikeRespDto {
        private Integer companyId;
        private Integer personalId;
        private Integer alarmId;

        public CompanyLikeRespDto(CompanyLike companyLike) {
            this.companyId = companyLike.getCompanyId();
            this.personalId = companyLike.getPersonalId();
            this.alarmId = companyLike.getAlarmId();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class bestCompany {
        private Integer companyId;
        private Integer ranks;
        private Integer Count;

        public bestCompany(Integer companyId, Integer ranks, Integer count) {
            this.companyId = companyId;
            this.ranks = ranks;
            Count = count;
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PersonalLikeRespDto {
        private Integer resumesId;
        private Integer companyId;
        private Integer alarmId;

        public PersonalLikeRespDto(PersonalLike personalLike) {
            this.companyId = personalLike.getCompanyId();
            this.resumesId = personalLike.getResumesId();
            this.alarmId = personalLike.getAlarmId();
        }

    }
}
