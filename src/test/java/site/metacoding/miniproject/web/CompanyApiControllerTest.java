package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.sql.Timestamp;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardUpdateReqDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Sql("classpath:truncate.sql")
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

    @BeforeEach
    public void sessionInit() {

        session = new MockHttpSession();
        SignCompanyDto signCompanyDto = new SignCompanyDto();

        signCompanyDto.setCompanyId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "company", signCompanyDto);

        String JwtToken = CreateJWTToken.createToken(signedDto); // Authorization
        mockCookie = new MockCookie("Authorization", JwtToken);

        session.setAttribute("principal", signedDto);

    }

    @AfterEach
    public void sessionClear() {
        session.clearAttributes();
    }

    // 채용공고업데이트
    @Test
    @Sql(scripts = "classpath:testsql/insertjobpostingboard.sql")
    public void updatejobPostingBoard_test() throws Exception {

        // given
        JobPostingBoardUpdateReqDto jobPostingBoardUpdateReqDto = new JobPostingBoardUpdateReqDto();
        jobPostingBoardUpdateReqDto.setJobPostingBoardId(1);
        jobPostingBoardUpdateReqDto.setJobPostingBoardTitle("test");
        jobPostingBoardUpdateReqDto.setJobPostingBoardContent("content");
        jobPostingBoardUpdateReqDto.setJobPostingSalary(1000);
        jobPostingBoardUpdateReqDto.setJobPostingBoardPlace("place");
        jobPostingBoardUpdateReqDto.setJobPostingBoardDeadline(new Timestamp(100L));

        jobPostingBoardUpdateReqDto.setCategoryBackend(true);
        jobPostingBoardUpdateReqDto.setCategoryDevops(false);
        jobPostingBoardUpdateReqDto.setCategoryFrontend(false);

        jobPostingBoardUpdateReqDto.setOneYearLess(true);
        jobPostingBoardUpdateReqDto.setTwoYearOver(false);
        jobPostingBoardUpdateReqDto.setThreeYearOver(false);
        jobPostingBoardUpdateReqDto.setFiveYearOver(false);

        String body = om.writeValueAsString(jobPostingBoardUpdateReqDto);

        // when
        ResultActions resultActions = mvc
                .perform(put("/s/api/jobPostingBoard/update/" + 1)
                        .content(body)
                        .contentType(APPLICATION_JSON)
                        .session(session)
                        .cookie(mockCookie)
                        .accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg:" + mvcResult.getResponse().getStatus());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));

    }

    // 채용공고 목록보기
    @Test
    @Sql(scripts = "classpath:testsql/insertjobpostingBoard.sql")
    public void jobPostingBoardList_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/s/company/jobPostingBoardList")
                .session(session).cookie(mockCookie).content(APPLICATION_JSON).accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    // // 채용공소 추가하기
    // @Test
    // @Sql(scripts = "classpath:testsql/insertjobpostingBoard.sql", executionPhase
    // = ExecutionPhase.BEFORE_TEST_METHOD)
    // public void insertJobPostingBoard_test() throws Exception {
    // // given
    // JobPostingBoardInsertReqDto jobPostingBoardInsertReqDto = new
    // JobPostingBoardInsertReqDto();
    // jobPostingBoardInsertReqDto.setCompanyId(1);
    // jobPostingBoardInsertReqDto.setJobPostingBoardTitle("title");
    // jobPostingBoardInsertReqDto.setJobPostingBoardContent("contentcontet");
    // jobPostingBoardInsertReqDto.setJobPostingBoardPlace("placeplace");
    // jobPostingBoardInsertReqDto.setJobPostingSalary(10000);
    // jobPostingBoardInsertReqDto.setJobPostingBoardDeadline(null);
    // jobPostingBoardInsertReqDto.setCategoryBackend(true);
    // jobPostingBoardInsertReqDto.setCategoryDevops(false);
    // jobPostingBoardInsertReqDto.setCategoryFrontend(false);
    // jobPostingBoardInsertReqDto.setOneYearLess(false);
    // jobPostingBoardInsertReqDto.setTwoYearOver(false);
    // jobPostingBoardInsertReqDto.setThreeYearOver(true);
    // jobPostingBoardInsertReqDto.setFiveYearOver(false);

    // String body = om.writeValueAsString(jobPostingBoardInsertReqDto);

    // // when
    // ResultActions resultActions =
    // mvc.perform(MockMvcRequestBuilders.post("/s/api/jobpostingboard/insert")
    // .content(body).contentType(APPLICATION_JSON).accept(APPLICATION_JSON).session(session));

    // // then
    // MvcResult mvcResult = resultActions.andReturn();
    // System.out.println("debugggg:" +
    // mvcResult.getResponse().getContentAsString());
    // resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));

    // }

    // 채용공고 삭제하기
    @Test
    @Sql(scripts = "classpath:testsql/insertjobpostingBoard.sql")
    public void deleteJobPostingBoard_test() throws Exception {
        // given

        Integer id = 1;
        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.delete("/s/company/jobPostingBoard/delete/1")
                        .session(session)
                        .cookie(mockCookie)
                        .accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg:" + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
    }

}
