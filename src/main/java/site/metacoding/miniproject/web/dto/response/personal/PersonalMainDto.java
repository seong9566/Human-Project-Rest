package site.metacoding.miniproject.web.dto.response.personal;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalMainDto {
	private Integer jobPostingBoardId;
	private String jobPostingBoardTitle;
	private Timestamp jobPostingBoardDeadline;
	// 모집중, 모집 마감
	private String state;
	// TimeStamp > String
	private String formatDeadLine;

	// 드롭다운 리스트
	private Boolean categoryFrontend;
	private Boolean categoryBackend;
	private Boolean categoryDevops;
	private String categoryAll;

	private Integer jobPostingBoardCategoryId;
	private Integer categoryId;
	private Integer id;
}
