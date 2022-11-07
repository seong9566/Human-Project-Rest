package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.miniproject.dto.like.LikeReqDto.CompanyLikeReqDto;
import site.metacoding.miniproject.dto.like.LikeReqDto.PersonalLikeReqDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;

@ActiveProfiles("test")
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

    // PersonalLike 테스트
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

    // CompanyLike 테스트
    // @BeforeEach
    // public void sessionInit() {

    // session = new MockHttpSession();
    // SignPersonalDto signPersonalDto = new SignPersonalDto();

    // signPersonalDto.setPersonalId(1);
    // SignedDto<?> signedDto = new SignedDto<>(1, "testuser1", signPersonalDto);

    // session.setAttribute("principal", signedDto);

    // String JwtToken = CreateJWTToken.createToken(signedDto); // Authorization
    // mockCookie = new MockCookie("Authorization", JwtToken);

    // }

    @AfterEach
    public void sessionClear() {
        session.clearAttributes();
    }

    @Sql(scripts = "classpath:testsql/insertuserforlike.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void insertPersonalLike_test() throws Exception {

        // given
        Integer resumesId = 1;
        PersonalLikeReqDto personalLikeReqDto = new PersonalLikeReqDto();
        personalLikeReqDto.setAlarmId(2);
        personalLikeReqDto.setCompanyId(1);
        personalLikeReqDto.setResumesId(1);
        String body = om.writeValueAsString(personalLikeReqDto);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/personalLike/" + resumesId).session(session)
                        .cookie(mockCookie).content(body)
                        .contentType(APPLICATION_JSON).accept(APPLICATION_JSON));
        System.out.println("디버그 : " + resultActions.andReturn().getResponse().getContentAsString());
        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void deletePersonalLike_test() throws Exception {
        Integer resumesId = 1;
        ResultActions resultActions = mvc
                .perform(delete("/s/api/personalLike/" + resumesId).session(session)
                        .cookie(mockCookie)
                        .accept(APPLICATION_JSON)
                        .session(session));

        // then/ charset=utf-8안넣으면바로한글이깨진다

        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void insertCompanyLike_test() throws Exception {

        // given
        Integer companyId = 1;
        CompanyLikeReqDto companyLikeReqDto = new CompanyLikeReqDto();
        companyLikeReqDto.setCompanyId(1);
        companyLikeReqDto.setAlarmId(1);
        companyLikeReqDto.setPersonalId(1);
        String body = om.writeValueAsString(companyLikeReqDto);
        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/companyLike/" + companyId)
                        .content(body).session(session)
                        .cookie(mockCookie)
                        .accept(APPLICATION_JSON));
        System.out.println("디버그 : " + resultActions.andReturn().getResponse().getContentAsString());
        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void deleteCompanyLike_test() throws Exception {
        Integer companyId = 1;

        ResultActions resultActions = mvc
                .perform(delete("/s/api/companyLike/" + companyId)
                        .accept(APPLICATION_JSON).session(session)
                        .cookie(mockCookie)
                        .session(session));

        // then/ charset=utf-8안넣으면바로한글이깨진다

        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void findAllPersonalLike_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc

                .perform(get("/s/api/resumeList").session(session).cookie(mockCookie)
                        .accept(APPLICATION_JSON));
        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());

    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void bestCompanye_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc

                .perform(get("/api/bestcompany").session(session).cookie(mockCookie)
                        .accept(APPLICATION_JSON));
        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());

    }
}
