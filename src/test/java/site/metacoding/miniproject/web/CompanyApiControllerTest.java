package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.domain.personal.PersonalDao;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.SHA256;
import site.metacoding.miniproject.web.dto.request.etc.LoginDto;

@ActiveProfiles("test")
@Sql("classpath:truncate.sql,classpath:test.sql")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CompanyApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private LoginDto loginDto;
    @Autowired
    private SHA256 sha256;
    @Autowired
    private PersonalDao personalDao;
    private MockHttpSession session;

    @BeforeEach
    public void sessionInit() {
        session = new MockHttpSession();
        SignCompanyDto signCompanyDto = new SignCompanyDto();
        signCompanyDto.setCompanyId(4);
        SignedDto<?> signedDto = new SignedDto<>(1, "company01", signCompanyDto);
        session.setAttribute("principal", signedDto);
    }

    @BeforeEach
    public void sessionconpany() {
        Company company = Company.builder().companyName("박동훈")
                .companyEmail("sopu55555@naver.com").companyPhoneNumber("01024102957")
                .companyPicture("ddd").build();
        session.setAttribute("sessioncompany", company);
    }

    @Test
    public void findByCompany_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/s/api/company/detail").accept(APPLICATION_JSON));
        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(status().isOk());
    }

}
