package site.metacoding.miniproject.dto.etc;

import lombok.Getter;
import lombok.Setter;

public class PagingRespDto {

    @Setter
    @Getter
    public static class PagingDto { // PagingDto -> PagingRespDto
        private String keyword;

        private Integer totalCount;
        private Integer totalPage;
        private Integer currentPage;
        private boolean isFirst;
        private boolean isLast;

        private Integer blockCount;
        private Integer currentBlock;
        private Integer startPageNum;
        private Integer lastPageNum;

        public void makeBlockInfo(String keyword) {
            this.keyword = keyword;
            this.blockCount = 5;
            this.currentBlock = currentPage / blockCount;
            this.startPageNum = 1 + blockCount * currentBlock;
            this.lastPageNum = 5 + blockCount * currentBlock;

            if (totalPage < lastPageNum) {
                this.lastPageNum = totalPage;
            }
        }

    }

}
