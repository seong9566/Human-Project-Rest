package site.metacoding.miniproject.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.alarm.AlarmRespDto.UserAlarmRespDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.service.users.UsersService;
import site.metacoding.miniproject.web.dto.response.ResponseDto;

@RestController
@RequiredArgsConstructor
public class AlarmController {
	// sendTo사용시 destinationValue 적용 받을라면 Spring 4.2부터 가능 - simpleMessagingTemplate을
	// 사용해서 DestinationValue를 대체한다.
	//private final SimpMessagingTemplate simpMessagingTemplate;
    private final UsersService userService;
    private final HttpSession session;

    // 유저알람 갱신 해주기
	@GetMapping("/s/user/alarm")
	public ResponseDto<?> refreshUserAlarm() {
        ResponseDto<?> responseDto = new ResponseDto<>(1, "알람없음", null);
        SignedDto<?> signedDto = (SignedDto<?>) session.getAttribute("principal");

        Integer usersId = signedDto.getUsersId();
		List<UserAlarmRespDto> usersAlarm = userService.finduserAlarmByUserId(usersId);

		if (!usersAlarm.isEmpty())
			responseDto = new ResponseDto<>(1, "통신 성공", usersAlarm);

		return responseDto;
	}

	// 알람지우기
	@DeleteMapping("/s/user/alarm/{alarmId}")
	public ResponseDto<?> deleteUserAlarm(@PathVariable Integer alarmId) {
		userService.deleteAlarm(alarmId);
		return new ResponseDto<>(1, "삭제 성공", null);
	}

// 	@MessageMapping("/Personal/{userId}")
// 	public void messageToTopicPersonal(@DestinationVariable Integer userId, Integer resumesId) {
// 		simpMessagingTemplate.convertAndSend("/topic/Personal", new ResponseDto<>(1, "success", resumesId));
// 	}

// 	@MessageMapping("/Personal/subscribe/{companyId}")
// 	public void messageToSubscribePersonal(@DestinationVariable Integer companyId, String companyName) {
// 		simpMessagingTemplate.convertAndSend("/topic/Company/" + companyId,
// 				new ResponseDto<>(1, "success", companyName + "님이 새로운 채용공고를 등록 했습니다."));
// 	}

// 	@MessageMapping("/Company/Likeresume/{resumesId}")
// 	public void messageToResume(@DestinationVariable Integer resumesId, Integer FromUsersId) {
// 		Integer usersId = userService.findUserIdByResumesId(resumesId);
// 		simpMessagingTemplate.convertAndSend("/queue/Personal/" + usersId,
// 				new ResponseDto<>(1, "success", resumesId));
// 	}

// 	@MessageMapping("/Personal/LikeCompany/{companyId}")
// 	public void messageToCompany(@DestinationVariable Integer companyId, Integer FromUsersId) {
// 		Integer usersId = userService.findUserIdByCompanyId(companyId);
// 		simpMessagingTemplate.convertAndSend("/queue/Company/" + usersId,
// 				new ResponseDto<>(1, "success", FromUsersId));
// 	}

// 	@PutMapping("/user/alarm/readed")
// 	public @ResponseBody ResponseDto<?> readedAlarm(@RequestParam(value = "alarmsId[]") List<Integer> alarmsId) {
// 		usersService.userAlarmToCheck(alarmsId);
// 		return new ResponseDto<>(1, "success", alarmsId);
// 	}

// 	@GetMapping("/user/alarm/notreaded/{userId}")
// 	public @ResponseBody ResponseDto<?> notReadedAlarm(@PathVariable Integer userId) {
// 		Boolean isReaded = userService.checkUserAlarm(userId);
// 		return new ResponseDto<>(1, "success", isReaded);
// 	}
}