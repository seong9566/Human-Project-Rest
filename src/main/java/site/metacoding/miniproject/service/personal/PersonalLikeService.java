package site.metacoding.miniproject.service.personal;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.alarm.Alarm;
import site.metacoding.miniproject.domain.alarm.AlarmDao;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.domain.like.personalike.PersonalLike;
import site.metacoding.miniproject.domain.like.personalike.PersonalLikesDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.like.LikeReqDto.PersonalLikeReqDto;
import site.metacoding.miniproject.dto.like.LikeRespDto.PersonalLikeRespDto;
import site.metacoding.miniproject.utill.AlarmEnum;

@RequiredArgsConstructor
@Service
public class PersonalLikeService {
	private final UsersDao usersDao;
	private final PersonalLikesDao personalLikesDao;
	private final AlarmDao alarmDao;
	private final CompanyDao companyDao;

	@Transactional(rollbackFor = RuntimeException.class)
	public PersonalLikeRespDto 좋아요(Integer resumesId, PersonalLikeReqDto personalLikeReqDto) {
		HashMap<String, Integer> personallikes = new HashMap<>();

		PersonalLike personalLikePS = personalLikeReqDto.personalLikeEntity();
		personalLikesDao.insert(personalLikePS);

		personallikes.put(AlarmEnum.ALARMPERSONALLIKEID.key(), personalLikePS.getPersonalLikeId());

		Users users = usersDao.findByResumesId(resumesId);
		Company companyPS = companyDao.findById(personalLikePS.getCompanyId());
		// Alarm alarm = new Alarm(users.getUsersId(), personallikes,
		// companyPS.getCompanyName());

		// alarmDao.insert(alarm);
		// personalLikePS.setAlarmId(alarm.getAlarmId());
		personalLikesDao.update(personalLikePS);
		PersonalLikeRespDto personalLikeRespDto = new PersonalLikeRespDto(personalLikePS);
		return personalLikeRespDto;
	}

	public PersonalLikeRespDto 좋아요취소(Integer resumesId, PersonalLikeReqDto personalLikeReqDto) {

		PersonalLike personalLikePS = personalLikeReqDto.personalLikeEntity();
		if (personalLikePS == null) {
			throw new RuntimeException("해당" + resumesId + "의 좋아요를 삭제할수 없습니다.");
		}
		personalLikesDao.deleteById(personalLikePS);
		PersonalLikeRespDto personalLikeRespDto = new PersonalLikeRespDto(personalLikePS);
		return personalLikeRespDto;
	}

	@Transactional(readOnly = true)
	public List<PersonalLikeReqDto> 좋아요이력서(Integer companyId) {
		List<PersonalLikeReqDto> PersonalLikeDtoList = personalLikesDao.findAll(companyId);
		return PersonalLikeDtoList;
	}

}
