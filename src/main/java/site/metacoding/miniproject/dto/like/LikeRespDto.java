package site.metacoding.miniproject.dto.like;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.like.companylike.CompanyLike;

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
}
