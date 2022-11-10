package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;
import site.metacoding.miniproject.dto.user.UserReqDto.LoginDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UsersApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ResourceLoader loader;

    private static MockHttpSession session = new MockHttpSession();

    @BeforeAll // 선언시 static으로 선언해야한다. - container에 띄우기 위해 사용한다.
    public static void init() {
        SignPersonalDto signPersonalDto = new SignPersonalDto();

        signPersonalDto.setPersonalId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "testuser1", signPersonalDto);

        session.setAttribute("principal", signedDto);

    }

    @BeforeEach
    public void sessionInit() {

    }

    @Order(1)
    @Test
    @Sql("classpath:truncate.sql")
    public void joinPersonal_test() throws Exception {

        // given
        PersonalJoinDto personalJoinDto = new PersonalJoinDto();
        personalJoinDto.setLoginId("user1");
        personalJoinDto.setLoginPassword("Qwer1234!!!");
        personalJoinDto.setPersonalPhoneNumber("000-1111-4444");
        personalJoinDto.setPersonalEmail("example@example.com");
        personalJoinDto.setPersonalName("testUsername");
        personalJoinDto.setPersonalAddress("testAddress");
        personalJoinDto.setPersonalEducation("test");

        String body = om.writeValueAsString(personalJoinDto);

        // when
        ResultActions resultActions = mvc
                .perform(post("/api/join/personal").content(body)
                        .contentType(APPLICATION_JSON).accept(APPLICATION_JSON));

        // then
        resultActions.andExpect(jsonPath("$.code").value("1"))
                .andExpect(jsonPath("$.message").value("계정생성완료"))
                .andExpect(jsonPath("$.data.usersId").value("1"));

    }

    @Order(2)
    @Test
    @Sql("classpath:truncate.sql")
    public void joinCompany_test() throws Exception {

        // given
        CompanyJoinDto joinDto = new CompanyJoinDto();
        joinDto.setCompanyName("testcompany");
        joinDto.setCompanyPhoneNumber("010-4444-6666");
        joinDto.setLoginId("testId");
        joinDto.setLoginPassword("Qwer1234!");
        joinDto.setCompanyEmail("example@example.com");

        String filename = "p4.jpg";
        Resource resource = loader.getResource("classpath:/static/images/" + filename);
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpg", resource.getInputStream());

        String body = om.writeValueAsString(joinDto);
        MockMultipartFile multipartBody = new MockMultipartFile("joinDto", "formData", APPLICATION_JSON,
                body.getBytes());

        ResultActions resultActions = mvc.perform(
                multipart(HttpMethod.POST, "/api/join/company")
                        .file(file)
                        .file(multipartBody)
                        .accept(APPLICATION_JSON));

        // then
        resultActions.andExpect(jsonPath("$.code").value("1"))
                .andExpect(jsonPath("$.message").value("계정생성완료"))
                .andExpect(jsonPath("$.data.usersId").value("1"));
    }

    @Order(3)
    @Test
    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforpersonal.sql" })
    public void login_test() throws Exception {
        // given
        String loginId = "testuser1";
        String loginPassword = "Qwer1234!!!";
        LoginDto loginDto = new LoginDto(loginId, loginPassword);

        String body = om.writeValueAsString(loginDto);

        // when
        ResultActions resultActions = mvc.perform(post("/api/login")
                .content(body)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));

        // String whatIsThat =
        // resultActions.andReturn().getResponse().getContentAsString();
        // log.debug("디버그 : " + whatIsThat);

        // then
        resultActions.andExpect(jsonPath("$.code").value("1"))
                .andExpect(jsonPath("$.message").value("로그인완료"))
                .andExpect(jsonPath("$.data.userInfo.personalId").value("1"));
    }

    @Order(4)
    @Test
    @Sql("classpath:truncate.sql")
    public void loginForm_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/api/loginForm")
                .session(session)
                .accept(APPLICATION_JSON));
        // then
        resultActions.andExpect(jsonPath("$.code").value("-1"))
                .andExpect(jsonPath("$.message").value("이미 로그인 되어 있음"));

    }

    @Order(2)
    @Test
    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforpersonal.sql" })
    public void userIdSameCheck_test() throws Exception {

        // given

        String loginId = "testuser1";

        // when

        ResultActions resultActions = mvc.perform(get("/api/checkId/" + loginId)
                .accept(APPLICATION_JSON))

                // then
                .andExpect(jsonPath("$.code").value("-1"))
                .andDo(result -> {
                    mvc.perform(get("/api/checkId/" + "testuser2").accept(APPLICATION_JSON))
                            .andExpect(jsonPath("$.code").value("1"))
                            .andExpect(jsonPath("$.data").value("true"));

                });
    }

}
