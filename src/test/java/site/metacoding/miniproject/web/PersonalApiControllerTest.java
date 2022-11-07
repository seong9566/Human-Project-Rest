package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.annotation.Order;
import org.springframework.mock.web.MockCookie;
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
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalUpdatReqDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Sql("classpath:truncate.sql")
public class PersonalApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession session;

    private MockCookie mockCookie;

    @BeforeAll // 선언시 static으로 선언해야한다. - container에 띄우기 위해 사용한다.
    public static void init() {

    }

    @BeforeEach
    public void sessionInit() {

        session = new MockHttpSession();
        SignPersonalDto signPersonalDto = new SignPersonalDto();

        signPersonalDto.setPersonalId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "testuser1", signPersonalDto);

        String JwtToken = CreateJWTToken.createToken(signedDto); // Authorization
        mockCookie = new MockCookie("Authorization", JwtToken);

        session.setAttribute("principal", signedDto);

    }

    @AfterEach
    public void sessionClear() {
        session.clearAttributes();
    }

    // 내정보보기
    @Order(1)
    @Sql(scripts = "classpath:testsql/selectdetailforpersonal.sql")
    @Test
    public void findByPersonal_test() throws Exception {

        // given

        // when
        ResultActions resultActions = mvc.perform(get("/api/personal/detail")
                .session(session)
                .cookie(mockCookie)
                .accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg: " + mvcResult.getResponse().getContentAsString());
    }

    // 내정보수정
    @Order(2)
    @Sql(scripts = "classpath:testsql/selectdetailforpersonal.sql")
    @Test
    public void updatePersonalDetail_test() throws Exception {

        // given
        PersonalUpdatReqDto personalUpdateReqDto = new PersonalUpdatReqDto();
        personalUpdateReqDto.setPersonalId(1);
        personalUpdateReqDto.setPersonalName("ssar");
        personalUpdateReqDto.setPersonalPhoneNumber("010-9459-5116");
        personalUpdateReqDto.setPersonalEmail("cndtjq1248@naver.com");
        personalUpdateReqDto.setPersonalAddress("대구,달서구,장기동");
        personalUpdateReqDto.setPersonalEducation("4년제");
        personalUpdateReqDto.setLoginPassword("@@ps990104");
        String body = om.writeValueAsString(personalUpdateReqDto);

        // when
        ResultActions resultActions = mvc.perform(put("/api/personal/update").content(body)
                .contentType(APPLICATION_JSON).accept(APPLICATION_JSON).session(session).cookie(mockCookie));
        System.out.println("debugggg:" + resultActions.andReturn().getResponse().getContentAsString());

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg:" + mvcResult.getResponse().getContentAsString());

    }

}
