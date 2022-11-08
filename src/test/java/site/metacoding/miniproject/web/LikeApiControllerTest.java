package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockCookie;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
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
    private static MockCookie mockCookie;

    public static void sessionCompanyInit() {

        SignCompanyDto signCompanyDto = new SignCompanyDto();

        signCompanyDto.setCompanyId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "testuser1", signCompanyDto);

        String JwtToken = CreateJWTToken.createToken(signedDto); // Authorization
        mockCookie = new MockCookie("Authorization", JwtToken);

    }

    public void sessionPersonalInit() {

        SignPersonalDto signPersonalDto = new SignPersonalDto();

        signPersonalDto.setPersonalId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "testuser1", signPersonalDto);

        String JwtToken = CreateJWTToken.createToken(signedDto); // Authorization
        mockCookie = new MockCookie("Authorization", JwtToken);

    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void insertPersonalLike_test() throws Exception {

        // given
        sessionCompanyInit();
        Integer resumesId = 1;
        PersonalLikeReqDto personalLikeReqDto = new PersonalLikeReqDto();
        personalLikeReqDto.setAlarmId(2);
        personalLikeReqDto.setCompanyId(1);
        personalLikeReqDto.setResumesId(1);
        String body = om.writeValueAsString(personalLikeReqDto);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/personalLike/" + resumesId)
                        .content(body).cookie(mockCookie)
                        .accept(APPLICATION_JSON));
        // then

        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void deletePersonalLike_test() throws Exception {
        sessionCompanyInit();
        Integer resumesId = 1;
        ResultActions resultActions = mvc
                .perform(delete("/s/api/personalLike/" + resumesId)
                        .cookie(mockCookie)
                        .accept(APPLICATION_JSON));

        // then/ charset=utf-8안넣으면바로한글이깨진다

        MvcResult mvcResult = resultActions.andReturn();
    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void insertCompanyLike_test() throws Exception {

        // given
        sessionPersonalInit();
        Integer companyId = 1;
        CompanyLikeReqDto companyLikeReqDto = new CompanyLikeReqDto();
        companyLikeReqDto.setCompanyId(1);
        companyLikeReqDto.setAlarmId(1);
        companyLikeReqDto.setPersonalId(1);
        String body = om.writeValueAsString(companyLikeReqDto);
        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/companyLike/" + companyId)
                        .content(body)
                        .cookie(mockCookie)
                        .accept(APPLICATION_JSON));
        // then
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void deleteCompanyLike_test() throws Exception {
        sessionPersonalInit();
        Integer companyId = 1;

        ResultActions resultActions = mvc
                .perform(delete("/s/api/companyLike/" + companyId)
                        .accept(APPLICATION_JSON)
                        .cookie(mockCookie));

        // then/ charset=utf-8안넣으면바로한글이깨진다

        MvcResult mvcResult = resultActions.andReturn();
    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void findAllPersonalLike_test() throws Exception {
        // given
        sessionCompanyInit();
        // when
        ResultActions resultActions = mvc

                .perform(get("/s/api/resumeList").cookie(mockCookie)
                        .accept(APPLICATION_JSON));
        // then
        MvcResult mvcResult = resultActions.andReturn();

    }

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertuserforlike.sql" })
    @Test
    public void bestCompanye_test() throws Exception {
        // given
        // when
        ResultActions resultActions = mvc

                .perform(get("/api/bestcompany").cookie(mockCookie)
                        .accept(APPLICATION_JSON));
        // then
        MvcResult mvcResult = resultActions.andReturn();

    }
}
