package site.metacoding.miniproject.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.alarm.AlarmReqDto.AlarmReqDtoToDelete;
import site.metacoding.miniproject.dto.alarm.AlarmReqDto.AlarmReqListDtoToCheck;
import site.metacoding.miniproject.dto.alarm.AlarmRespDto.UserAlarmRespDto;
import site.metacoding.miniproject.dto.alarm.AlarmRespDto.UserAlarmRespDtoToChecked;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.service.users.UsersService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AlarmController {
	// sendTo사용시 destinationValue 적용 받을라면 Spring 4.2부터 가능 - simpleMessagingTemplate을
	// 사용해서 DestinationValue를 대체한다.
	// private final SimpMessagingTemplate simpMessagingTemplate;
	private final UsersService usersService;
	private final HttpSession session;

	// 유저알람 갱신 해주기
	@GetMapping("/s/api/users/alarm")
	public ResponseDto<?> refreshUserAlarm() {

		ResponseDto<?> responseDto = new ResponseDto<>(1, "알람없음", null);
		SignedDto<?> signedDto = (SignedDto<?>) session.getAttribute("principal");

		Integer usersId = signedDto.getUsersId();
		List<UserAlarmRespDto> usersAlarm = usersService.finduserAlarmByUserId(usersId);

		if (!usersAlarm.isEmpty())
			responseDto = new ResponseDto<>(1, "통신 성공", usersAlarm);

		return responseDto;
	}

	// 알람 확인시 ischeck -> true 변경
	@PutMapping("/s/api/users/alarm/readed")
	public ResponseDto<?> readedAlarm(@RequestBody AlarmReqListDtoToCheck alarmReqListDtoToCheck) {
		SignedDto<?> signedDto = (SignedDto<?>) session.getAttribute("principal");
		alarmReqListDtoToCheck.setUsersId(signedDto.getUsersId());
		List<UserAlarmRespDtoToChecked> checkeds = usersService.userAlarmToCheck(alarmReqListDtoToCheck);
		return new ResponseDto<>(1, "success", checkeds);
	}

	// 알람지우기
	@DeleteMapping("/s/api/users/alarm/delete/{alarmId}")
	public ResponseDto<?> deleteUserAlarm(@PathVariable Integer alarmId) {

		SignedDto<?> signedDto = (SignedDto<?>) session.getAttribute("principal");
		AlarmReqDtoToDelete alarmReqDtoToDelete = new AlarmReqDtoToDelete(alarmId, signedDto.getUsersId());

		usersService.deleteAlarm(alarmReqDtoToDelete);
		return new ResponseDto<>(1, "삭제 성공", null);
	}
	// 웹소켓 의존 제외로 인해서 사용 안됨
	// @MessageMapping("/Personal/{userId}")
	// public void messageToTopicPersonal(@DestinationVariable Integer userId,
	// Integer resumesId) {
	// simpMessagingTemplate.convertAndSend("/topic/Personal", new ResponseDto<>(1,
	// "success", resumesId));
	// }

	// @MessageMapping("/Personal/subscribe/{companyId}")
	// public void messageToSubscribePersonal(@DestinationVariable Integer
	// companyId, String companyName) {
	// simpMessagingTemplate.convertAndSend("/topic/Company/" + companyId,
	// new ResponseDto<>(1, "success", companyName + "님이 새로운 채용공고를 등록 했습니다."));
	// }

	// @MessageMapping("/Company/Likeresume/{resumesId}")
	// public void messageToResume(@DestinationVariable Integer resumesId, Integer
	// FromUsersId) {
	// Integer usersId = userService.findUserIdByResumesId(resumesId);
	// simpMessagingTemplate.convertAndSend("/queue/Personal/" + usersId,
	// new ResponseDto<>(1, "success", resumesId));
	// }

	// @MessageMapping("/Personal/LikeCompany/{companyId}")
	// public void messageToCompany(@DestinationVariable Integer companyId, Integer
	// FromUsersId) {
	// Integer usersId = userService.findUserIdByCompanyId(companyId);
	// simpMessagingTemplate.convertAndSend("/queue/Company/" + usersId,
	// new ResponseDto<>(1, "success", FromUsersId));
	// }

	// @GetMapping("/user/alarm/notreaded/{userId}")
	// public @ResponseBody ResponseDto<?> notReadedAlarm(@PathVariable Integer
	// userId) {
	// Boolean isReaded = userService.checkUserAlarm(userId);
	// return new ResponseDto<>(1, "success", isReaded);
	// }
}