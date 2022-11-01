package site.metacoding.miniproject.web;

import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.service.users.UsersService;
import site.metacoding.miniproject.web.dto.response.ResponseDto;

@Controller
@RequiredArgsConstructor
public class AlarmController {
	// sendTo사용시 destinationValue 적용 받을라면 Spring 4.2부터 가능 - simpleMessagingTemplate을
	// 사용해서 DestinationValue를 대체한다.
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final UsersService usersService;

	@MessageMapping("/Personal/{userId}")
	public void messageToTopicPersonal(@DestinationVariable Integer userId, Integer resumesId) {
		simpMessagingTemplate.convertAndSend("/topic/Personal", new ResponseDto<>(1, "success", resumesId));
	}

	@MessageMapping("/Personal/subscribe/{companyId}")
	public void messageToSubscribePersonal(@DestinationVariable Integer companyId, String companyName) {
		System.out.println(companyId);
		System.out.println(companyName);
		simpMessagingTemplate.convertAndSend("/topic/Company/" + companyId,
				new ResponseDto<>(1, "success", companyName + "님이 새로운 채용공고를 등록 했습니다."));
	}

	@MessageMapping("/Company/Likeresume/{resumesId}")
	public void messageToResume(@DestinationVariable Integer resumesId, Integer FromUsersId) {
		Integer usersId = usersService.findUserIdByResumesId(resumesId);
		simpMessagingTemplate.convertAndSend("/queue/Personal/" + usersId,
				new ResponseDto<>(1, "success", resumesId));
	}

	@MessageMapping("/Personal/LikeCompany/{companyId}")
	public void messageToCompany(@DestinationVariable Integer companyId, Integer FromUsersId) {
		Integer usersId = usersService.findUserIdByCompanyId(companyId);
		simpMessagingTemplate.convertAndSend("/queue/Company/" + usersId,
				new ResponseDto<>(1, "success", FromUsersId));
	}

	@PutMapping("/user/alarm/readed")
	public @ResponseBody ResponseDto<?> readedAlarm(@RequestParam(value = "alarmsId[]") List<Integer> alarmsId) {
		usersService.userAlarmToCheck(alarmsId);
		return new ResponseDto<>(1, "success", alarmsId);
	}

	@GetMapping("/user/alarm/notreaded/{userId}")
	public @ResponseBody ResponseDto<?> notReadedAlarm(@PathVariable Integer userId) {
		Boolean isReaded = usersService.checkUserAlarm(userId);
		return new ResponseDto<>(1, "success", isReaded);
	}
}