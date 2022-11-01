package site.metacoding.miniproject.service.company;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.alarm.Alarm;
import site.metacoding.miniproject.domain.alarm.AlarmDao;
import site.metacoding.miniproject.domain.like.companylike.CompanyLike;
import site.metacoding.miniproject.domain.like.companylike.CompanyLikesDao;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.AlarmEnum;

@RequiredArgsConstructor
@Service
public class CompanyLikeService {

	private final CompanyLikesDao companyLikesDao;
	private final UsersDao usersDao;
	private final AlarmDao alarmDao;

	@Transactional(rollbackFor = RuntimeException.class)
	public void 좋아요(SignedDto<?> signedDto, Integer companyId) {

		HashMap<String, Integer> companylikes = new HashMap<>();
		Personal personalinfo = (Personal) signedDto.getUserinfo();

		CompanyLike companyLike = new CompanyLike(companyId, personalinfo.getPersonalId());
		companyLikesDao.insert(companyLike);

		companylikes.put(AlarmEnum.ALARMCOMPANYLIKEID.key(), companyLike.getCompanyLikeId());

		Users users = usersDao.findByCompanyId(companyId);
		Alarm alarm = new Alarm(users.getUsersId(), companylikes, personalinfo.getPersonalName());

		alarmDao.insert(alarm);

		companyLike.setAlarmId(alarm.getAlarmId());
		companyLikesDao.update(companyLike);

	}

	public void 좋아요취소(Integer personalId, Integer companyId) {
		CompanyLike companyLike = new CompanyLike(personalId, companyId);
		companyLikesDao.deleteById(companyLike);
	}

	public CompanyLike 좋아요확인(Integer personalId, Integer companyId) {
		CompanyLike companyLike = new CompanyLike(personalId, companyId);
		CompanyLike companyLike3 = companyLikesDao.findById(companyLike);
		return companyLike3;
	}
}
