package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.annotation.Order;
import org.springframework.mock.web.MockCookie;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class SubscribeApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @Autowired

    private MockMvc mvc;

    private static MockCookie mockCookie;

    @BeforeAll
    public static void init() {

        SignPersonalDto signPersonalDto = new SignPersonalDto();

        signPersonalDto.setPersonalId(1);
        SignedDto<?> signedDto = new SignedDto<>(1, "testuser1", signPersonalDto);

        String JwtToken = CreateJWTToken.createToken(signedDto);
        mockCookie = new MockCookie("Authorization", JwtToken);

    }

    @BeforeEach
    public void sessionInit() {

    }

    @Order(1)
    @Test
    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertcompanyfortest.sql",
            "classpath:testsql/insertuserforpersonal.sql" })
    public void subscribeToCompany_test() throws Exception {
        // given
        Integer companyId = 1;
        // when
        ResultActions resultActions = mvc.perform(
                get("/s/api/subscribe/" + companyId)
                        .cookie(mockCookie).accept(APPLICATION_JSON))
                // then
                .andExpect(jsonPath("$.code").value("1"));
    }

    @Order(2)
    @Test
    @Sql({ "classpath:truncate.sql", "classpath:testsql/insertcompanyfortest.sql",
            "classpath:testsql/insertuserforpersonal.sql", "classpath:testsql/insertsubscribefortest.sql" })
    public void subscribeCancelToCompany_test() throws Exception {
        // given
        Integer subscribeId = 1;
        // when

        ResultActions resultActions = mvc.perform(delete("/s/api/subscribe/" + subscribeId)
                .cookie(mockCookie)
                .accept(APPLICATION_JSON))

                // then
                .andExpect(jsonPath("$.code").value("1"));
    }

}
