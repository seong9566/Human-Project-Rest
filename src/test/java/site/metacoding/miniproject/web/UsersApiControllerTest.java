package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.config.MyBatisConfig;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Import(MyBatisConfig.class)
@Sql("classpath:testdatabase.sql")
public class UsersApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";
    
    @Autowired
    private MockMvc mvc;

    @Autowired
	private ObjectMapper om;

    private MockHttpSession session;

    private static HttpHeaders headers;

    @BeforeAll // 선언시 static으로 선언해야한다. - container에 띄우기 위해 사용한다.
	public static void init() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}
	
	@BeforeEach// test메서드 진입전에 트랜잭션 발동
    public void sessionInit() {
        session = new MockHttpSession();
		SignPersonalDto signPersonalDto = new SignPersonalDto();
		signPersonalDto.setPersonalId(1);
		SignedDto<?> signedDto = new SignedDto<>(1, "testuser", signPersonalDto);
		session.setAttribute("principal", signedDto);
    }


    @Test
    public void joinPersonal_test() throws Exception {
        //given
        PersonalJoinDto joinDto = new PersonalJoinDto();
        joinDto.setLoginId("user3");
        joinDto.setLoginPassword("Qwer1234!!!");
        joinDto.setPersonalPhoneNumber("000-1111-4444");
        joinDto.setPersonalEmail("example@example.com");
        joinDto.setPersonalName("testUsername");
        joinDto.setPersonalAddress("testAddress");
        joinDto.setPersonalEducation("test");

        String body = om.writeValueAsString(joinDto);

        //when
        ResultActions resultActions = mvc
                .perform(post("/join/personal").content(body)
                        .contentType("application/json; charset=utf-8").accept(APPLICATION_JSON));
        //then
        MvcResult mvcResult = resultActions.andReturn();
        log.debug("디버그 :  개인 회원가입 - " + mvcResult.getResponse().getContentAsString());
        //assertEquals(null, joinDto);

    }

    @Test
    public void joinPersonal_test2() throws Exception {
        //given
        PersonalJoinDto joinDto = new PersonalJoinDto();
        joinDto.setLoginId("user3");
        joinDto.setLoginPassword("Qwer1234!!!");
        joinDto.setPersonalPhoneNumber("000-1111-4444");
        joinDto.setPersonalEmail("example@example.com");
        joinDto.setPersonalName("testUsername");
        joinDto.setPersonalAddress("testAddress");
        joinDto.setPersonalEducation("test");

        String body = om.writeValueAsString(joinDto);

        //when
        ResultActions resultActions = mvc
                .perform(post("/join/personal").content(body)
                        .contentType("application/json; charset=utf-8").accept(APPLICATION_JSON));
        //then
        MvcResult mvcResult = resultActions.andReturn();
        log.debug("디버그 :  개인 회원가입 - " + mvcResult.getResponse().getContentAsString());
        //assertEquals(null, joinDto);

    }


}
