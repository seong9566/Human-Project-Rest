package site.metacoding.miniproject.service.users;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.config.handler.exception.ApiException;
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
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.web.dto.request.etc.LoginDto;

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
            throw new ApiException("아이디 또는 패스워드가 틀렸습니다.");
        }

        //회사 또는 개인일 경우 Dto생성
        if (userinfo.getCompanyId() != null) {
            Company companyPS = companyDao.findById(userinfo.getCompanyId());

            SignCompanyDto signCompanyDto = new SignCompanyDto(companyPS);

            signedDto = new SignedDto<>(userinfo.getUsersId(), userinfo.getLoginId(), signCompanyDto);
        } else {
            Personal personalPS = personalDao.findById(userinfo.getPersonalId());

            SignPersonalDto signPersonalDto = new SignPersonalDto(personalPS);

            signedDto = new SignedDto<>(userinfo.getUsersId(), userinfo.getLoginId(), signPersonalDto);

        }

        return signedDto;
    }

    //개인 회원가입
    @Transactional(rollbackFor = RuntimeException.class)
    public void joinPersonal(PersonalJoinDto joinDto) {

        Personal personalBeforePS = joinDto.personalJoinDtoToPersonalEntity();

        personalDao.insert(personalBeforePS);

        joinDto.setPersonalId(personalBeforePS.getPersonalId());

        Users usersBeforePS = joinDto.personalJoinDtoToUserEntity();

        usersDao.insert(usersBeforePS);

    }
    
    //기업 회원가입
    @Transactional(rollbackFor = RuntimeException.class)
    public void joinCompany(CompanyJoinDto joinDto) {

        try {
            joinDto.companyJoinDtoPictureSet();
        } catch (Exception e) {
            throw new ApiException("멀티파트 폼 에러");
        }

        Company company = joinDto.companyJoinDtoToCompanyEntity();
        companyDao.insert(company);

        joinDto.setCompanyId(company.getCompanyId());

        Users users = joinDto.companyJoinDtoToUserEntity();
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