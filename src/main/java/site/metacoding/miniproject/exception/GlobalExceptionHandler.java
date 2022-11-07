package site.metacoding.miniproject.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.utill.Script;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// @ExceptionHandler(RuntimeException.class)
	// public @ResponseBody String RuntimeError(Exception e) {
	// return Script.back("잘못된 요청입니다.");
	// }

	@ExceptionHandler(ApiException.class)
	public @ResponseBody ResponseDto<?> apiError(Exception e) {
		return new ResponseDto<>(-1, e.getMessage(), e.getStackTrace());
	}

	@ExceptionHandler(NormalException.class)
	public @ResponseBody String Error(Exception e) {
		return Script.back(e.getMessage());
	}

	@ExceptionHandler(ValCheckException.class)
	public @ResponseBody ResponseDto<?> valCheckError(Exception e) {
		return new ResponseDto<>(-1, e.getMessage(), null);
	}

}
