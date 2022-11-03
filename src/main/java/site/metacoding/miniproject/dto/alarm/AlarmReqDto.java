package site.metacoding.miniproject.dto.alarm;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class AlarmReqDto {


    @Setter
    @Getter
    public static class AlarmReqListDtoToCheck {
        List<Integer> alarmsId;
        Integer usersId;
    }
    
}
