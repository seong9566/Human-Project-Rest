package site.metacoding.miniproject.dto.like;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.domain.like.companylike.CompanyLike;

@Getter
@Setter
@NoArgsConstructor
public class LikeRespDto {
    public static class CompanyLikeRespDto {
        private Integer companyId;
        private Integer personalId;
        private Integer alarmId;

        public CompanyLike CompanyLikeEntity() {
            return CompanyLike.builder().companyId(companyId).personalId(personalId).alarmId(alarmId).build();
        }
    }
}
