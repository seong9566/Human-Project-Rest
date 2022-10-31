package site.metacoding.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseDto<T> {

	private Integer code;
	private String message;
	private T data;

}
