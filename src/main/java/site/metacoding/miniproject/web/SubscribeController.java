package site.metacoding.miniproject.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.config.handler.exception.ApiException;
import site.metacoding.miniproject.domain.subscribe.Subscribe;
import site.metacoding.miniproject.service.users.SubscribeService;
import site.metacoding.miniproject.web.dto.response.ResponseDto;
import site.metacoding.miniproject.web.dto.response.etc.SignedDto;

@Controller
@RequiredArgsConstructor
public class SubscribeController {

	private final SubscribeService subscribeService;
	private final HttpSession session;

	@GetMapping("/subscribe/{companyId}")
	public @ResponseBody ResponseDto<?> subscribeToCompany(@PathVariable Integer companyId) {
		SignedDto<?> signedDto = (SignedDto<?>) session.getAttribute("principal");

		if (signedDto == null) {
			throw new ApiException("로그인 후 구독해주세요!");
		}

		List<Subscribe> subscribes = new ArrayList<>();
		Subscribe subscribe = subscribeService.subscribeToCompany(signedDto, companyId);
		subscribes.add(subscribe);

		session.setAttribute("subscribe", subscribes);
		return new ResponseDto<>(1, "구독 성공!", subscribe.getSubscribeId());
	}

	@DeleteMapping("/subscribe/{subscribeId}")
	public @ResponseBody ResponseDto<?> subscribeCancelToCompany(@PathVariable Integer subscribeId) {
		SignedDto<?> signedDto = (SignedDto<?>) session.getAttribute("principal");
		if (signedDto == null) {
			throw new ApiException("잘못된 접근입니다.");
		}
		List<Subscribe> subscribes = subscribeService.subscribeCancelToCompany(subscribeId, signedDto);
		session.setAttribute("subscribe", subscribes);
		return new ResponseDto<>(1, "구독 취소 성공!", null);
	}
}