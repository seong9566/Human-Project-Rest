package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockCookie;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;

@ActiveProfiles("test")
@Sql("classpath:truncate.sql")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class LikeApiControllerTest {
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

    // @BeforeEach // test메서드 진입전에 트랜잭션 발동
    // public void sessionInit() {

    // session = new MockHttpSession();
    // SignCompanyDto signCompanyDto = new SignCompanyDto();

    // signCompanyDto.setCompanyId(1);
    // SignedDto<?> signedDto = new SignedDto<>(1, "testuser1", signCompanyDto);

    // session.setAttribute("principal", signedDto);

    // String JwtToken = CreateJWTToken.createToken(signedDto); // Authorization
    // mockCookie = new MockCookie("Authorization", JwtToken);

    // }
    @BeforeEach
    public void sessionInit() {

        session = new MockHttpSession();
        SignPersonalDto signPersonalDto = new SignPersonalDto();

        signPersonalDto.setPersonalId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "testuser1", signPersonalDto);

        session.setAttribute("principal", signedDto);

        String JwtToken = CreateJWTToken.createToken(signedDto); // Authorization
        mockCookie = new MockCookie("Authorization", JwtToken);

    }

    @AfterEach
    public void sessionClear() {
        session.clearAttributes();
    }

    @Sql(scripts = "classpath:testsql/insertuserforlike.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void insertPersonalLike_test() throws Exception {

        // given
        Integer resumesId = 1;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/personalLike/" + resumesId).session(session)
                        .cookie(mockCookie)
                        .contentType(APPLICATION_JSON).accept(APPLICATION_JSON));
        System.out.println("디버그 : " + resultActions.andReturn().getResponse().getContentAsString());
        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
    }

    @Sql(scripts = "classpath:testsql/insertuserforlike.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void deletePersonalLike_test() throws Exception {
        Integer resumesId = 1;

        ResultActions resultActions = mvc
                .perform(delete("/s/api/personalLike/" + resumesId)
                        .accept(APPLICATION_JSON)
                        .session(session));

        // then/ charset=utf-8안넣으면바로한글이깨진다

        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
    }

    @Sql(scripts = "classpath:testdatabase.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void insertCompanyLike_test() throws Exception {

        // given
        Integer companyId = 1;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/companyLike/" + companyId).session(session)
                        .cookie(mockCookie)
                        .contentType(APPLICATION_JSON).accept(APPLICATION_JSON));
        System.out.println("디버그 : " + resultActions.andReturn().getResponse().getContentAsString());
        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
    }

    @Sql(scripts = "classpath:testsql/insertuserforlike.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void deleteCompanyLike_test() throws Exception {
        Integer companyId = 2;

        ResultActions resultActions = mvc
                .perform(delete("/s/api/companyLike/{companyId}" + companyId)
                        .accept(APPLICATION_JSON)
                        .session(session));

        // then/ charset=utf-8안넣으면바로한글이깨진다

        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
    }
}
