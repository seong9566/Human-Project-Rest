package site.metacoding.miniproject.service.personal;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.alarm.Alarm;
import site.metacoding.miniproject.domain.alarm.AlarmDao;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.like.personalike.PersonalLike;
import site.metacoding.miniproject.domain.like.personalike.PersonalLikesDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.utill.AlarmEnum;
import site.metacoding.miniproject.web.dto.request.personal.PersonalLikeDto;
import site.metacoding.miniproject.web.dto.response.etc.SignedDto;

@RequiredArgsConstructor
@Service
public class PersonalLikeService {
	private final UsersDao usersDao;
	private final PersonalLikesDao personalLikesDao;
	private final AlarmDao alarmDao;

	@Transactional(rollbackFor = RuntimeException.class)
	public void 좋아요(Integer resumesId, SignedDto<?> signedDto) {

		HashMap<String, Integer> personallikes = new HashMap<>();
		Company companyinfo = (Company) signedDto.getUserinfo();

		PersonalLike personalLike = new PersonalLike(resumesId, signedDto.getCompanyId());
		personalLikesDao.insert(personalLike);

		personallikes.put(AlarmEnum.ALARMPERSONALLIKEID.key(), personalLike.getPersonalLikeId());

		Users users = usersDao.findByResumesId(resumesId);
		Alarm alarm = new Alarm(users.getUsersId(), personallikes, companyinfo.getCompanyName());

		alarmDao.insert(alarm);

		personalLike.setAlarmId(alarm.getAlarmId());
		personalLikesDao.update(personalLike);

	}

	@Transactional(rollbackFor = RuntimeException.class)
	public void 좋아요취소(Integer resumesId, Integer companyId) {

		PersonalLike personalLike = new PersonalLike(resumesId, companyId);
		alarmDao.deleteById(companyId);
		personalLikesDao.deleteById(personalLike);

	}

	public List<PersonalLikeDto> 좋아요이력서(Integer companyId) {
		List<PersonalLikeDto> PersonalLikeDtoList = personalLikesDao.findAll(companyId);
		return PersonalLikeDtoList;
	}

	public PersonalLike 좋아요확인(Integer companyId, Integer resumesId) {
		PersonalLike personalLike = new PersonalLike(companyId, resumesId);
		PersonalLike companyLike2 = personalLikesDao.findById(personalLike);
		return companyLike2;
	}

}
