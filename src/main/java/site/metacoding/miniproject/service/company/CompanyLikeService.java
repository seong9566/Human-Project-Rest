package site.metacoding.miniproject.service.company;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.alarm.Alarm;
import site.metacoding.miniproject.domain.alarm.AlarmDao;
import site.metacoding.miniproject.domain.like.companylike.CompanyLike;
import site.metacoding.miniproject.domain.like.companylike.CompanyLikesDao;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.personal.PersonalDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.like.LikeReqDto.CompanyLikeReqDto;
import site.metacoding.miniproject.dto.like.LikeRespDto.CompanyLikeRespDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.AlarmEnum;

@RequiredArgsConstructor
@Service
public class CompanyLikeService {
	private final HttpSession session;
	private final CompanyLikesDao companyLikesDao;
	private final UsersDao usersDao;
	private final AlarmDao alarmDao;
	private final PersonalDao personalDao;

	@Transactional(rollbackFor = RuntimeException.class)
	public CompanyLikeRespDto 좋아요(SignedDto<?> signedDto, Integer companyId) {
		HashMap<String, Integer> companylikes = new HashMap<>();
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignPersonalDto signPersonalDto = (SignPersonalDto) principal.getUserInfo();
		CompanyLike companyLike = new CompanyLike(companyId, signPersonalDto.getPersonalId());

		companylikes.put(AlarmEnum.ALARMCOMPANYLIKEID.key(),
				companyLike.getCompanyLikeId());

		Users users = usersDao.findByCompanyId(companyId);
		Personal personalPS = personalDao.findById(signPersonalDto.getPersonalId());
		Alarm alarm = new Alarm(users.getUsersId(), companylikes, personalPS.getPersonalName());

		alarmDao.insert(alarm);

		companyLike.setAlarmId(alarm.getAlarmId());
		companyLikesDao.update(companyLike);
		CompanyLikeRespDto companyLikeRespDto = companyLikesDao.insert(companyLike);
		return companyLikeRespDto;

	}

	public void 좋아요취소(Integer personalId, Integer companyId, CompanyLikeReqDto companyLikeReqDto) {
		CompanyLike companyLike = companyLikeReqDto.CompanyLikeEntity();
		companyLikesDao.deleteById(companyLike);
	}

	public CompanyLike 좋아요확인(Integer personalId, Integer companyId, CompanyLikeReqDto companyLikeReqDto) {
		CompanyLike companyLike = companyLikesDao.findById(companyLikeReqDto);
		return companyLike;
	}
}
