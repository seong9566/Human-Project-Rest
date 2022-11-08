package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardInsertReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardUpdateReqDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CompanyApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockCookie mockCookie;

    @BeforeAll // 선언시 static으로 선언해야한다. - container에 띄우기 위해 사용한다.
    public static void init() {

    }

    @BeforeEach
    public void sessionInit() {

        SignCompanyDto signCompanyDto = new SignCompanyDto();

        signCompanyDto.setCompanyId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "company", signCompanyDto);

        String JwtToken = CreateJWTToken.createToken(signedDto); // Authorization
        mockCookie = new MockCookie("Authorization", JwtToken);

    }

    // 채용공고업데이트
    @Test

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertjobpostingboard.sql" })
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
                        .cookie(mockCookie)
                        .accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg:" + mvcResult.getResponse().getStatus());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));

    }

    // 채용공고 목록보기
    @Test

    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertjobpostingboard.sql" })
    public void jobPostingBoardList_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/s/company/jobPostingBoardList")

                .cookie(mockCookie).content(APPLICATION_JSON).accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    // 채용공고 추가하기
    @Test
    @Sql({ "classpath:truncate.sql", "classpath:testsql/companytest.sql" })
    public void insertJobPostingBoard_test() throws Exception {

        // given
        JobPostingBoardInsertReqDto jobPostingBoardInsertReqDto = new JobPostingBoardInsertReqDto();
        jobPostingBoardInsertReqDto.setJobPostingBoardTitle("title");
        jobPostingBoardInsertReqDto.setJobPostingBoardContent("contentcontet");
        jobPostingBoardInsertReqDto.setJobPostingBoardPlace("placeplace");
        jobPostingBoardInsertReqDto.setJobPostingSalary(10000);
        jobPostingBoardInsertReqDto.setJobPostingBoardDeadline(null);
        jobPostingBoardInsertReqDto.setCategoryBackend(true);
        jobPostingBoardInsertReqDto.setCategoryDevops(false);
        jobPostingBoardInsertReqDto.setCategoryFrontend(false);
        jobPostingBoardInsertReqDto.setOneYearLess(false);
        jobPostingBoardInsertReqDto.setTwoYearOver(false);
        jobPostingBoardInsertReqDto.setThreeYearOver(true);
        jobPostingBoardInsertReqDto.setFiveYearOver(false);

        String body = om.writeValueAsString(jobPostingBoardInsertReqDto);
        log.debug("mytest : " + body);
        // when
        ResultActions resultActions = mvc.perform(post("/s/api/jobpostingboard/insert")
                .content(body)
                .cookie(mockCookie)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();

        //
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    // 채용공고 삭제하기
    @Test
    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertjobpostingBoard.sql" })
    public void deleteJobPostingBoard_test() throws Exception {
        // given

        Integer id = 1;
        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.delete("/s/company/jobPostingBoard/delete/1")
                        .cookie(mockCookie)
                        .accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("debugggg:" + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
    }

    @Test
    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertjobpostingBoard.sql" })
    public void findByjobPostingBoard_test() throws Exception { // 채용공고 상세 보기 완료
        // given
        Integer jobPostingBoardId = 1;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/jobPostingBoard/detail/" + jobPostingBoardId)
                        .accept(APPLICATION_JSON)
                        .cookie(mockCookie));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.message").value("채용공고 상세보기"));
        resultActions.andExpect(jsonPath("$.data.jobPostingBoardTitle").value("title"));
    }

    @Test
    @Sql("classpath:testsql/companytest.sql")
    public void findByCompany_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc

                .perform(get("/api/company/detail").cookie(mockCookie)
                        .accept(APPLICATION_JSON));
        // then
        MvcResult mvcResult = resultActions.andReturn();

    }

    @Test
    @Sql({ "classpath:truncate.sql", "classpath:testsql/companytest.sql" })
    public void companyUpdate_test() throws Exception {
        // given
        CompanyUpdateReqDto companyUpdateReqDto = new CompanyUpdateReqDto();
        companyUpdateReqDto.setCompanyName("박동훈");
        companyUpdateReqDto.setCompanyEmail("sopu555555@naver.com");
        companyUpdateReqDto.setCompanyPhoneNumber("01024102957");
        // MockMultipartFile file = new MockMultipartFile("image", "test.png",
        // "image/png",
        // new FileInputStream("C:\\Users\\GGG\\4.jpg"));
        companyUpdateReqDto.setCompanyPicture("file");
        String body = om.writeValueAsString(companyUpdateReqDto);
        // when
        ResultActions resultActions = mvc
                .perform(put("/s/api/company/update").cookie(mockCookie).content(body)
                        .contentType(APPLICATION_JSON).accept(APPLICATION_JSON));
    }

}