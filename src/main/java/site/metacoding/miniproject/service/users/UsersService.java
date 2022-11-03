package site.metacoding.miniproject.service.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
import site.metacoding.miniproject.dto.alarm.AlarmReqDto.AlarmReqListDtoToCheck;
import site.metacoding.miniproject.dto.alarm.AlarmRespDto.UserAlarmRespDto;
import site.metacoding.miniproject.dto.alarm.AlarmRespDto.UserAlarmRespDtoToChecked;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.SHA256;
import site.metacoding.miniproject.web.dto.request.etc.LoginDto;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersDao usersDao;
    private final CompanyDao companyDao;
    private final PersonalDao personalDao;
    private final AlarmDao alarmDao;
    private final SubscribeDao subscribeDao;
    private final SHA256 sha256;

    // 로그인
    public SignedDto<?> login(LoginDto loginDto) {

        String loginId = loginDto.getLoginId();
        String loginPassword = sha256.encrypt(loginDto.getLoginPassword());
        SignedDto<?> signedDto;

        Users userInfo = usersDao.findByIdAndPassword(loginId, loginPassword);

        if (userInfo == null) {
            throw new ApiException("아이디 또는 패스워드가 틀렸습니다.");
        }

        // if (signedDto == null)
        // return new ResponseDto<>(-1, "비밀번호 또는 아이디를 확인하여 주세요", null);

        // if (SessionConfig.getSessionidCheck(signedDto.getUsersId()) != null) {
        // return new ResponseDto<>(-2, "중복 로그인 확인됨", null);
        // }

        // session.setAttribute("principal", signedDto);
        // SessionConfig.login(session.getId(), signedDto.getUsersId());

        // if (signedDto.getCompanyId() != null) {
        // session.setAttribute("companyId", signedDto.getCompanyId());
        // } else {
        // subscribes =
        // userService.findSubscribeinfoByPersonalId(signedDto.getPersonalId());
        // session.setAttribute("personalId", signedDto.getPersonalId());
        // session.setAttribute("subscribe", subscribes);
        // }

        // 회사 또는 개인일 경우 Dto생성
        if (userInfo.getCompanyId() != null) {
            Company companyPS = companyDao.findById(userInfo.getCompanyId());

            SignCompanyDto signCompanyDto = new SignCompanyDto(companyPS);

            signedDto = new SignedDto<>(userInfo.getUsersId(), userInfo.getLoginId(), signCompanyDto);
        } else {
            Personal personalPS = personalDao.findById(userInfo.getPersonalId());

            SignPersonalDto signPersonalDto = new SignPersonalDto(personalPS);

            signedDto = new SignedDto<>(userInfo.getUsersId(), userInfo.getLoginId(), signPersonalDto);

        }

        return signedDto;
    }

    // 개인 회원가입
    @Transactional(rollbackFor = RuntimeException.class)
    public SignedDto<?> joinPersonal(PersonalJoinDto joinDto) {

        Personal personalBeforePS = joinDto.personalJoinDtoToPersonalEntity();

        personalDao.insert(personalBeforePS);

        joinDto.setPersonalId(personalBeforePS.getPersonalId());

        Users usersBeforePS = joinDto.personalJoinDtoToUserEntity();

        usersDao.insert(usersBeforePS);

        SignPersonalDto signPersonalDto = new SignPersonalDto(personalBeforePS);

        SignedDto<?> signedDto = new SignedDto<>(usersBeforePS.getUsersId(), usersBeforePS.getLoginId(),
                signPersonalDto);

        return signedDto;
    }

    // 기업 회원가입
    @Transactional(rollbackFor = RuntimeException.class)
    public SignedDto<?> joinCompany(CompanyJoinDto joinDto) {

        try {
            joinDto.companyJoinDtoPictureSet();
        } catch (Exception e) {
            throw new ApiException("멀티파트 폼 에러");
        }

        Company companyBeforePS = joinDto.companyJoinDtoToCompanyEntity();
        companyDao.insert(companyBeforePS);

        joinDto.setCompanyId(companyBeforePS.getCompanyId());

        Users usersBeforePS = joinDto.companyJoinDtoToUserEntity();
        usersDao.insert(usersBeforePS);

        SignCompanyDto signCompanyDto = new SignCompanyDto(companyBeforePS);

        SignedDto<?> signedDto = new SignedDto<>(usersBeforePS.getUsersId(), usersBeforePS.getLoginId(),
                signCompanyDto);

        return signedDto;

    }

    //이력서번호로 유저 아이디찾기
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

    //유저아이디로 해당 유저에게 온 알람 체크 하기
    public List<UserAlarmRespDto> finduserAlarmByUserId(Integer usersId) {

        List<Alarm> usersAlarmsPS = alarmDao.findByUsersId(usersId);
        List<UserAlarmRespDto> userAlarmRespDtos = new ArrayList<>();

        userAlarmRespDtos = usersAlarmsPS.stream().map(alarm-> new UserAlarmRespDto(alarm)).collect(Collectors.toList());
        return userAlarmRespDtos;
    }

    public Boolean checkUserAlarm(Integer usersId) {
        Boolean ischecked = alarmDao.findByUsersIdToAlarmChecked(usersId);
        return ischecked;
    }

     //알람확인시 읽음표시 하기
    public List<UserAlarmRespDtoToChecked> userAlarmToCheck(AlarmReqListDtoToCheck alarmReqListDtoToCheck) {


        List<Alarm> alarmsPS = alarmDao.findByUsersIdForUnCheckedAlarm(alarmReqListDtoToCheck.getUsersId());
        HashMap<Integer, Integer> alarmPSId = new HashMap<>();

        for (Integer userAlarmId : alarmReqListDtoToCheck.getAlarmsId()) {
            alarmPSId.put(userAlarmId, userAlarmId);
        }

        Integer confirmAlarmCount = alarmsPS.stream()
        .filter(alarm -> alarm.getAlarmId().equals(alarmPSId.get(alarm.getAlarmId())))
                .collect(Collectors.toList()).size();
        
        if (confirmAlarmCount != alarmReqListDtoToCheck.getAlarmsId().size()) {
            throw new ApiException("해당유저의 알람이 아닙니다.");
        }

        alarmDao.updateAlarmByIdToCheck(alarmReqListDtoToCheck.getAlarmsId());

        List<UserAlarmRespDtoToChecked> checkeds = new ArrayList<>();

        //Dto반환을 위해 PS부에서 check를 true 변환 한 뒤 Dto로 넘김
        alarmsPS.iterator().forEachRemaining(alarm -> {
            alarm.setAlarmCheck(true);
            checkeds.add(new UserAlarmRespDtoToChecked(alarm));
        });
            
        return checkeds;


    }
        
    //알람 지우기
    public void deleteAlarm(Integer alarmId) {

        try {
            Alarm alarmPS = alarmDao.findById(alarmId);
            alarmDao.deleteById(alarmPS.getAlarmId());
        } catch (Exception e) {
            throw new ApiException("해당 알람이 존재하지 않습니다.");
        }
    }

    //구독정보 개인회원아이디로 호출하기
    public List<Subscribe> findSubscribeInfoByPersonalId(Integer personalId) {
        return subscribeDao.findByPersonalId(personalId);
    }
}