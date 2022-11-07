package site.metacoding.miniproject.service.users;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.alarm.Alarm;
import site.metacoding.miniproject.domain.alarm.AlarmDao;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.personal.PersonalDao;
import site.metacoding.miniproject.domain.subscribe.Subscribe;
import site.metacoding.miniproject.domain.subscribe.SubscribeDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.exception.ApiException;
import site.metacoding.miniproject.utill.AlarmEnum;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeDao subscribeDao;
    private final AlarmDao alarmDao;
    private final UsersDao usersDao;
    private final CompanyDao companyDao;
    private final PersonalDao personalDao;

    @Transactional(rollbackFor = RuntimeException.class)
    public Subscribe subscribeToCompany(SignedDto<?> signedDto, Integer companyId) {

        Company companyPS = companyDao.findById(companyId);
        if (companyPS == null) {
            throw new ApiException("해당 기업이 존재하지 않습니다.");
        }



        HashMap<String, Integer> subscribes = new HashMap<>();
        SignPersonalDto personalInfo = (SignPersonalDto) signedDto.getUserInfo();
        Personal personalPS = personalDao.findById(companyId);

        if (personalPS == null) {
            throw new ApiException("유저가 존재하지 않습니다.");
        }

        Subscribe subscribe = new Subscribe(personalInfo.getPersonalId(), companyId);
        subscribeDao.insert(subscribe);

        Users users = usersDao.findByCompanyId(companyId);
        subscribes.put(AlarmEnum.ALARMSUBSCRIBEID.key(), subscribe.getSubscribeId());

        Alarm alarm = new Alarm(users.getUsersId(), subscribes, personalPS.getPersonalName());
        alarmDao.insert(alarm);

        subscribe.setAlarmId(alarm.getAlarmId());
        subscribeDao.update(subscribe);

        return subscribe;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public List<Subscribe> subscribeCancelToCompany(Integer subscribeId, SignedDto<?> signedDto) {
        
        SignPersonalDto personalInfo = (SignPersonalDto) signedDto.getUserInfo();

        Subscribe subscribe = subscribeDao.findById(subscribeId);

        if (subscribe == null) {
            throw new ApiException("구독내역이 없습니다");
        }
        
        subscribeDao.deleteById(subscribeId);
        List<Subscribe> subscribes = subscribeDao.findByPersonalId(personalInfo.getPersonalId());
        return subscribes;

    }
}