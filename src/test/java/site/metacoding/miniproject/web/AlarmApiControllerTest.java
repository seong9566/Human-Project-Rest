package site.metacoding.miniproject.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.dto.alarm.AlarmReqDto.AlarmReqListDtoToCheck;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class AlarmApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession session;

    private MockCookie mockCookie;

    @BeforeAll
    public static void init() {

    }

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

    @Order(1)
    @Test
    @Sql(value = { "classpath:truncate.sql", "classpath:testsql/insertalarmfortest.sql" })
    public void refreshUserAlarm_test() throws Exception {

        // given

        // when
        ResultActions resultActions = mvc.perform(get("/s/api/users/alarm")
                .session(session)
                .cookie(mockCookie)
                .accept(APPLICATION_JSON))

                // then

                .andExpect(jsonPath("$.code").value("1"))
                .andExpect(jsonPath("$.message").value("통신 성공"));

    }

    @Order(2)
    @Test
    @Sql(value = { "classpath:truncate.sql", "classpath:testsql/insertalarmfortest.sql" })
    public void readedAlarm_test() throws Exception {

        // given
        AlarmReqListDtoToCheck alarmReqListDtoToCheck = new AlarmReqListDtoToCheck();
        List<Integer> alarmsId = new ArrayList<>();

        alarmsId.add(4);
        alarmsId.add(5);
        alarmsId.add(6);

        alarmReqListDtoToCheck.setAlarmsId(alarmsId);

        String body = om.writeValueAsString(alarmReqListDtoToCheck);

        // when
        ResultActions resultActions = mvc.perform(put("/s/api/users/alarm/readed")
                .content(body)
                .cookie(mockCookie)
                .session(session)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("-1"))
                .andDo(result -> {

                    final List<Integer> fixedAlarmsId = alarmsId;

                    fixedAlarmsId.clear();

                    fixedAlarmsId.add(1);
                    fixedAlarmsId.add(2);
                    fixedAlarmsId.add(3);

                    final AlarmReqListDtoToCheck fixedbody = alarmReqListDtoToCheck;

                    fixedbody.setAlarmsId(fixedAlarmsId);

                    mvc.perform(
                            put("/s/api/users/alarm/readed")
                                    .content(om.writeValueAsString(fixedbody))
                                    .cookie(mockCookie)
                                    .session(session)
                                    .contentType(APPLICATION_JSON)
                                    .accept(APPLICATION_JSON))

                            // then
                            .andExpect(jsonPath("$.code").value("1"));
                });
    }

    @Test
    @Sql(value = { "classpath:truncate.sql", "classpath:testsql/insertalarmfortest.sql" })
    public void deleteUserAlarm_test() throws Exception {

        // given

        Integer alarmId = 5;

        // when

        ResultActions resultActions = mvc
                .perform(delete("/s/api/users/alarm/delete/" + alarmId)
                        .session(session)
                        .cookie(mockCookie)
                        .accept(APPLICATION_JSON))

                // then

                .andExpect(jsonPath("$.code").value("-1"))
                .andDo(result -> {
                    mvc.perform(delete("/s/api/users/alarm/delete/" + 1)
                            .session(session)
                            .cookie(mockCookie)
                            .accept(APPLICATION_JSON))
                            .andExpect(jsonPath("$.code").value("1"));
                });
    }

}
