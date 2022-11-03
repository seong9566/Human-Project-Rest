package site.metacoding.miniproject.dto.alarm;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.miniproject.domain.alarm.Alarm;

public class AlarmRespDto {

    @Getter
    @Setter
    public static class UserAlarmRespDto {
        private Integer alarmId;
        private Integer usersId;
        private Integer alarmApplyId;
        private Integer alarmIncruitId;
        private Integer alarmSubscribeId;
        private Integer alarmCompanyLikeId;
        private Integer alarmPersonalLikeId;
        private String alarmMessage;
        private Boolean alarmCheck;

        public UserAlarmRespDto(Alarm alarm) {
            this.alarmId = alarm.getAlarmId();
            this.usersId = alarm.getUsersId();
            this.alarmApplyId = alarm.getAlarmApplyId();
            this.alarmIncruitId = alarm.getAlarmIncruitId();
            this.alarmSubscribeId = alarm.getAlarmSubscribeId();
            this.alarmCompanyLikeId = alarm.getAlarmCompanyLikeId();
            this.alarmPersonalLikeId = alarm.getAlarmPersonalLikeId();
            this.alarmMessage = alarm.getAlarmMessage();
            this.alarmCheck = alarm.getAlarmCheck();
        }
    }
    @Getter
    @Setter
    public static class UserAlarmRespDtoToChecked {
        private Integer alarmId;
        private Integer usersId;
        private Integer alarmApplyId;
        private Integer alarmIncruitId;
        private Integer alarmSubscribeId;
        private Integer alarmCompanyLikeId;
        private Integer alarmPersonalLikeId;
        private String alarmMessage;
        private Boolean alarmCheck;

        public UserAlarmRespDtoToChecked(Alarm alarm) {
            this.alarmId = alarm.getAlarmId();
            this.usersId = alarm.getUsersId();
            this.alarmApplyId = alarm.getAlarmApplyId();
            this.alarmIncruitId = alarm.getAlarmIncruitId();
            this.alarmSubscribeId = alarm.getAlarmSubscribeId();
            this.alarmCompanyLikeId = alarm.getAlarmCompanyLikeId();
            this.alarmPersonalLikeId = alarm.getAlarmPersonalLikeId();
            this.alarmMessage = alarm.getAlarmMessage();
            this.alarmCheck = alarm.getAlarmCheck();
        }
    }
    
}
