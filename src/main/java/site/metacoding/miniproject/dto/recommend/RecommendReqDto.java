package site.metacoding.miniproject.dto.recommend;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class RecommendReqDto {

    @Getter
    @Setter
    public class RecommendInsertDto { // RecommendInsertDto -> RecommendInsertReqDto
        private Integer personalLikeId;
        private Integer resumesId;
        private Integer companyId;
        private Timestamp createdAt;

    }
}
