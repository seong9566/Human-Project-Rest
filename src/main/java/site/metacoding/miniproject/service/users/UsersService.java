package site.metacoding.miniproject.service.users;

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
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;
import site.metacoding.miniproject.dto.user.UserReqDto.LoginDto;
import site.metacoding.miniproject.dto.user.UserReqDto.SignedDto;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersDao usersDao;
    private final CompanyDao companyDao;
    private final PersonalDao personalDao;
    private final AlarmDao alarmDao;
    private final SubscribeDao subscribeDao;

    public SignedDto<?> login(LoginDto loginDto) {
        String loginId = loginDto.getLoginId();
        String loginPassword = loginDto.getLoginPassword();
        SignedDto<?> signedDto;
        Users userinfo = usersDao.findByIdAndPassword(loginId, loginPassword);
        if (userinfo == null) {
            return null;
        }
        if (userinfo.getPersonalId() != null) {
            Personal personal = personalDao.findById(userinfo.getPersonalId());
            signedDto = new SignedDto<>(userinfo.getUsersId(), userinfo.getLoginId(), userinfo.getLoginPassword(),
                    personal.getPersonalId(), null, personal);
        } else {
            Company company = companyDao.findById(userinfo.getCompanyId());
            signedDto = new SignedDto<>(userinfo.getUsersId(), userinfo.getLoginId(), userinfo.getLoginPassword(), null,
                    company.getCompanyId(), company);
        }

        return signedDto;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void joinPersonal(PersonalJoinDto joinDto) {

        Personal personal = new Personal(joinDto);
        personalDao.insert(personal);

        Integer personalId = personal.getPersonalId();
        joinDto.setPersonalId(personalId);

        Users users = new Users(joinDto);
        usersDao.insert(users);

    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void joinCompany(CompanyJoinDto joinDto) {

        Company company = new Company(joinDto);
        companyDao.insert(company);

        Integer companyId = company.getCompanyId();
        joinDto.setCompanyId(companyId);

        Users users = new Users(joinDto);
        usersDao.insert(users);

    }

    public Integer findUserIdByResumesId(Integer resumesId) {

        Users users = usersDao.findByResumesId(resumesId);
        return users.getUsersId();
    }

    public Integer findUserIdByCompanyId(Integer companyId) {
        Users users = usersDao.findByCompanyId(companyId);
        return users.getUsersId();
    }

    public Integer checkUserId(String loginId) {
        Integer checkUser = usersDao.findByLoginId(loginId);
        return checkUser;
    }

    public List<Alarm> userAlarm(Integer usersId) {
        List<Alarm> usersAlarm = alarmDao.findByusersId(usersId);
        return usersAlarm;
    }

    public Boolean checkUserAlarm(Integer usersId) {
        Boolean ischecked = alarmDao.findByUsersIdToAlarmChecked(usersId);
        return ischecked;
    }

    public void userAlarmToCheck(List<Integer> alarmsId) {
        alarmDao.updateAlarmByIdToCheck(alarmsId);
    }

    public void deleteAlarm(Integer alarmId) {
        alarmDao.deleteById(alarmId);
    }

    public List<Subscribe> findSubscribeinfoByPersonalId(Integer personalId) {
        return subscribeDao.findByPersonalId(personalId);
    }
}