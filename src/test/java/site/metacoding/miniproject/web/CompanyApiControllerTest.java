package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockCookie;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;

@Slf4j
@ActiveProfiles("test")
@Sql("classpath:truncate.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CompanyApiControllerTest {
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

    @BeforeEach // test메서드 진입전에 트랜잭션 발동
    public void sessionInit() {

        session = new MockHttpSession();
        SignCompanyDto signCompanyDto = new SignCompanyDto();

        signCompanyDto.setCompanyId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "testuser1", signCompanyDto);

        session.setAttribute("principal", signedDto);

        String JwtToken = CreateJWTToken.createToken(signedDto); // Authorization
        mockCookie = new MockCookie("Authorization", JwtToken);

    }

    @AfterEach
    public void sessionClear() {
        session.clearAttributes();
    }

    @Test
    @Sql(scripts = "classpath:testsql/insertuserforpersonal.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    public void findByCompany_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc

                .perform(get("/api/company/detail").session(session).cookie(mockCookie)
                        .accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());

    }

    @Test
    @Sql(scripts = "classpath:testsql/insertuserforpersonal.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    public void companyUpdate_test() throws Exception {
        // given
        CompanyUpdateReqDto companyUpdateReqDto = new CompanyUpdateReqDto();
        companyUpdateReqDto.setCompanyName("박동훈");
        String body = om.writeValueAsString(companyUpdateReqDto);
        // when
        ResultActions resultActions = mvc
                .perform(put("/s/api/company/update").session(session).cookie(mockCookie).content(body)
                        .contentType(APPLICATION_JSON).accept(APPLICATION_JSON));
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
    }

}
