package site.metacoding.miniproject.web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.config.MyBatisConfig;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.personal.PersonalDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalUpdatReqDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.exception.ApiException;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Import(MyBatisConfig.class)
@Sql("classpath:testdatabase.sql")
public class PersonalApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PersonalDao personalDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UsersDao usersDao;

    private MockHttpSession session;

    private static HttpHeaders headers;

    @BeforeAll // 선언시 static으로 선언해야한다. - container에 띄우기 위해 사용한다.
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeEach
    public void sessionInit() {
        session = new MockHttpSession();
        SignPersonalDto signPersonalDto = new SignPersonalDto();
        signPersonalDto.setPersonalId(17);
        SignedDto<?> signedDto = new SignedDto<>(1, "personal01", signPersonalDto);
        session.setAttribute("principal", signedDto);
    }

    // @BeforeEach
    // public void loginInit() {
    // String loginId = loginDto.getLoginId();
    // String loginPassword = sha256.encrypt(loginDto.getLoginPassword());
    // SignedDto<?> signedDto;

    // Users userInfo = usersDao.findByIdAndPassword(loginId, loginPassword);

    // if (userInfo == null) {
    // throw new ApiException("아이디 또는 패스워드가 틀렸습니다.");
    // }

    // if (userInfo.getCompanyId() != null) {
    // Company companyPS = companyDao.findById(userInfo.getCompanyId());

    // SignCompanyDto signCompanyDto = new SignCompanyDto(companyPS);

    // signedDto = new SignedDto<>(userInfo.getUsersId(), userInfo.getLoginId(),
    // signCompanyDto);
    // } else {
    // Personal personalPS = personalDao.findById(userInfo.getPersonalId());

    // SignPersonalDto signPersonalDto = new SignPersonalDto(personalPS);

    // signedDto = new SignedDto<>(userInfo.getUsersId(), userInfo.getLoginId(),
    // signPersonalDto);

    // }
    // }

    @BeforeEach
    public void dataInit() {
        session = new MockHttpSession();
        Personal personal = Personal.builder().personalName("ssar")
                .personalPhoneNumber("010-9459-5116")
                .personalEmail("cndtjq1248@naver.com")
                .personalEducation("4년제")
                .personalAddress("대구광역시")
                .build();
    }

    @Test
    public void findByPersonal_test() throws Exception {

        // given
        PersonalJoinDto joinDto = new PersonalJoinDto();
        joinDto.setLoginId("user3");
        joinDto.setLoginPassword("Qwer1234!!!");
        joinDto.setPersonalPhoneNumber("000-1111-4444");
        joinDto.setPersonalEmail("example@example.com");
        joinDto.setPersonalName("testUsername");
        joinDto.setPersonalAddress("testAddress");
        joinDto.setPersonalEducation("test");

        String body = om.writeValueAsString(joinDto);

        // when
        ResultActions resultActions = mvc
                .perform(
                        MockMvcRequestBuilders.get("/s/api/personal/detail").accept(APPLICATION_JSON).session(session));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg: " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void updatePersonalDetail_test() throws Exception {

        // given
        PersonalUpdatReqDto personalUpdateReqDto = new PersonalUpdatReqDto();
        personalUpdateReqDto.setPersonalName("ssar");
        personalUpdateReqDto.setPersonalPhoneNumber("010-9459-5116");
        personalUpdateReqDto.setPersonalEmail("cndtjq1248@naver.com");
        personalUpdateReqDto.setPersonalAddress("대구,달서구,장기동");
        personalUpdateReqDto.setPersonalEducation("4년제");
        personalUpdateReqDto.setLoginPassword("@@ps990104");
        String body = om.writeValueAsString(personalUpdateReqDto);

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.put("/s/api/personal/update").content(body)
                .contentType(APPLICATION_JSON).accept(APPLICATION_JSON).session(session));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg:" + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("ssar"));
    }

}
