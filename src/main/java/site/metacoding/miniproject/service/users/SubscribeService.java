package site.metacoding.miniproject.service.users;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.alarm.Alarm;
import site.metacoding.miniproject.domain.alarm.AlarmDao;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.subscribe.Subscribe;
import site.metacoding.miniproject.domain.subscribe.SubscribeDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.user.UserReqDto.SignedDto;
import site.metacoding.miniproject.utill.AlarmEnum;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeDao subscribeDao;
    private final AlarmDao alarmDao;
    private final UsersDao usersDao;

    @Transactional(rollbackFor = RuntimeException.class)
    public Subscribe subscribeToCompany(SignedDto<?> signedDto, Integer companyId) {

        HashMap<String, Integer> subscribes = new HashMap<>();
        Personal personalinfo = (Personal) signedDto.getUserinfo();

        Subscribe subscribe = new Subscribe(signedDto.getPersonalId(), companyId);
        subscribeDao.insert(subscribe);

        Users users = usersDao.findByCompanyId(companyId);
        subscribes.put(AlarmEnum.ALARMSUBSCRIBEID.key(), subscribe.getSubscribeId());

        Alarm alarm = new Alarm(users.getUsersId(), subscribes, personalinfo.getPersonalName());
        alarmDao.insert(alarm);

        subscribe.setAlarmId(alarm.getAlarmId());
        subscribeDao.update(subscribe);

        return subscribe;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public List<Subscribe> subscribeCancelToCompany(Integer subscribeId, SignedDto<?> signedDto) {
        subscribeDao.deleteById(subscribeId);
        List<Subscribe> subscribes = subscribeDao.findByPersonalId(signedDto.getPersonalId());
        return subscribes;
    }
}