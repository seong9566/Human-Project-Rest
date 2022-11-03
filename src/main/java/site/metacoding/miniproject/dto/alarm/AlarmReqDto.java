package site.metacoding.miniproject.dto.alarm;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AlarmReqDto {


    @Setter
    @Getter
    public static class AlarmReqListDtoToCheck {
        List<Integer> alarmsId;
        Integer usersId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class AlarmReqDtoToDelete {
        Integer alarmId;
        Integer usersId;

        public AlarmReqDtoToDelete(Integer alarmId, Integer usersId) {
            this.alarmId = alarmId;
            this.usersId = usersId;
        }

    }
    
}
