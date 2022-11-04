package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.FileInputStream;

import org.junit.jupiter.api.Assertions;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.config.MyBatisConfig;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.web.dto.response.ResponseDto;

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
        PersonalJoinDto personalJoinDto = new PersonalJoinDto();
        personalJoinDto.setLoginId("user1");
        personalJoinDto.setLoginPassword("Qwer1234!!!");
        personalJoinDto.setPersonalPhoneNumber("000-1111-4444");
        personalJoinDto.setPersonalEmail("example@example.com");
        personalJoinDto.setPersonalName("testUsername");
        personalJoinDto.setPersonalAddress("testAddress");
        personalJoinDto.setPersonalEducation("test");

        String body = om.writeValueAsString(personalJoinDto);

        //when
        ResultActions resultActions = mvc
                .perform(post("/join/personal").content(body)
                        .contentType("application/json; charset=utf-8").accept(APPLICATION_JSON));
        ResponseDto<?> responseDto = new ResponseDto<>(1, "success",
                resultActions.andReturn().getResponse().getContentAsString());
        //then
        //MvcResult mvcResult = resultActions.andReturn();
        Assertions.assertEquals(1, responseDto.getCode());
        Assertions.assertEquals("success", responseDto.getMessage());
        Assertions.assertNotNull(responseDto.getData());
        //assertEquals(null, joinDto);

    }

    @Test
    public void joinCompany_test() throws Exception {
        
        //given
        CompanyJoinDto joinDto = new CompanyJoinDto();
        joinDto.setCompanyName("testcompany");
        joinDto.setCompanyPhoneNumber("010-4444-6666");
        joinDto.setLoginId("testId");
        joinDto.setLoginPassword("Qwer1234!");
        MockMultipartFile file =new MockMultipartFile("image", "test.png", "image/png",
                new FileInputStream("C:\\Users\\GGG\\Desktop"));
        String body = om.writeValueAsString(joinDto);
        System.out.println(body);
        // //when
        // ResultActions resultActions = mvc
        //         .perform(post("/join/company").content(body)
        //                 .contentType("application/json; charset=utf-8").accept(APPLICATION_JSON));
        // ResponseDto<?> responseDto = new ResponseDto<>(1, "success",
        //         resultActions.andReturn().getResponse().getContentAsString());
                
        // //then
        // log.debug("디버그 : " );
        // Assertions.assertEquals(1, responseDto.getCode());
        // Assertions.assertEquals("success", responseDto.getMessage());
        // Assertions.assertNotNull(responseDto.getData());
    }

}
