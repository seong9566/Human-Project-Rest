package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockCookie;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesInsertReqDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.service.personal.PersonalService;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;
import site.metacoding.miniproject.utill.SHA256;

@ActiveProfiles("test")
@Sql("classpath:truncate.sql")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class PersonalApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SHA256 sha256;

    @Autowired
    private ResourceLoader loader;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private MockMvc mvc;

    private MockCookie mockCookie;

    private MockHttpSession session;

    @BeforeEach
    public void sessionInit() {
        session = new MockHttpSession();
        SignPersonalDto signPersonalDto = new SignPersonalDto();
        signPersonalDto.setPersonalId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "testusers1", signPersonalDto);

        session.setAttribute("principal", signedDto);

        String JwtToken = CreateJWTToken.createToken(signedDto);
        mockCookie = new MockCookie("Authorization", JwtToken);
    }

    @AfterEach
    public void sessionClear() {
        session.clearAttributes();
    }

    @Test
    @Sql("classpath:testsql/insertresumes.sql")
    public void insertResumes_test() throws Exception {
        // given
        ResumesInsertReqDto resumesInsertReqDto = new ResumesInsertReqDto();

        resumesInsertReqDto.setCategoryFrontend(true);
        resumesInsertReqDto.setCategoryBackend(true);
        resumesInsertReqDto.setCategoryDevops(true);
        resumesInsertReqDto.setPortfolioFile("포트폴리오파일");
        resumesInsertReqDto.setPortfolioSource("http://github.com/asdfqwer");
        resumesInsertReqDto.setOneYearLess(true);
        resumesInsertReqDto.setTwoYearOver(false);
        resumesInsertReqDto.setThreeYearOver(false);
        resumesInsertReqDto.setFiveYearOver(false);
        resumesInsertReqDto.setResumesTitle("이력서제목1");
        resumesInsertReqDto.setResumesPicture("사진자리");
        resumesInsertReqDto.setResumesIntroduce("자기소개1");
        resumesInsertReqDto.setResumesPlace("부산경남");

        String filename = "p4.jpg";
        Resource resource = loader.getResource("classpath:/static/images/" + filename);
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpg", resource.getInputStream());

        String body = om.writeValueAsString(resumesInsertReqDto);
        MockMultipartFile multipartBody = new MockMultipartFile("reqDto", "formData", APPLICATION_JSON,
                body.getBytes());

        // when
        ResultActions resultActions = mvc
                .perform(multipart(HttpMethod.POST, "/s/resumes/insert")
                        .file(file)
                        .file(multipartBody)
                        .accept(APPLICATION_JSON)
                        .cookie(mockCookie)
                        .session(session));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        // System.out.println("디버그 : " + mvcResult.getResponse().getStatus());
        // System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.message").value("이력서 등록 성공"));
        resultActions.andExpect(jsonPath("$.data.resumesTitle").value("이력서제목1"));
    }

    @Test
    @Sql("classpath:testsql/findbyresumesid.sql")
    public void findByResumesId_test() throws Exception {
        // given
        Integer resumesId = 1;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/resumes/" + resumesId).accept(APPLICATION_JSON)
                        .cookie(mockCookie)
                        .session(session));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.message").value("내 이력서 상세 보기 성공"));
        resultActions.andExpect(jsonPath("$.data.resumesTitle").value("resumes_title_example1"));
    }

    @Test
    @Sql("classpath:testsql/findallmyresumes.sql")
    public void findAllMyResumes_test() throws Exception {
        // given
        Integer id = 1;

        // when
        ResultActions resultActions = mvc
                .perform(get("/s/resumes/myList").accept(APPLICATION_JSON)
                        .cookie(mockCookie)
                        .session(session));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.message").value("내 이력서 목록 보기 성공"));
        resultActions.andExpect(jsonPath("$.data.[0].resumesTitle").value("resumes_title_example1"));
    }

    @Test
    @Sql("classpath:testsql/deleteresumes.sql")
    public void deleteResumes_test() throws Exception {
        // given
        Integer id = 1;

        // when
        ResultActions resultActions = mvc
                .perform(delete("/s/resumes/delete/" + id)
                        .accept(APPLICATION_JSON)
                        .cookie(mockCookie)
                        .session(session));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        resultActions.andExpect(jsonPath("$.code").value(1));
        resultActions.andExpect(jsonPath("$.message").value("이력서 삭제 성공"));
    }
}
