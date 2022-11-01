package site.metacoding.miniproject.config.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import site.metacoding.miniproject.config.handler.exception.ApiException;
import site.metacoding.miniproject.config.handler.exception.NormalException;
import site.metacoding.miniproject.config.handler.exception.ValCheckException;
import site.metacoding.miniproject.utill.Script;
import site.metacoding.miniproject.web.dto.response.ResponseDto;

@ControllerAdvice
public class PageExceptionHandler {

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
