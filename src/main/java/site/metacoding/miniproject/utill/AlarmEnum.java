package site.metacoding.miniproject.utill;

public enum AlarmEnum {
    ALARMAPPLYID("alarmApplyId"),
    ALARMINCRUITID("alarmIncruitId"),
    ALARMSUBSCRIBEID("alarmSubscribeId"),
    ALARMCOMPANYLIKEID("alarmCompanyLikeId"),
    ALARMPERSONALLIKEID("alarmPersonalLikeId");

    private final String key;

    // call constructor
    AlarmEnum(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }

    public static AlarmEnum findBy(String key) {
        for (AlarmEnum alarmEnum : values()) {
            if (alarmEnum.name().equals(key))
                return alarmEnum;
        }
        return null;
    }

    public String AlarmMessage(String key, String name) {
        String message = "";
        if (ALARMAPPLYID.key.equals(key)) {
            message = name + " 님이 회원님의 공고에 지원 하였습니다.";
        }
        if (ALARMINCRUITID.key.equals(key)) {
            message = name + " 님이 회원님의 이력서를 인크루트 하였습니다.";
        }
        if (ALARMSUBSCRIBEID.key.equals(key)) {
            message = name + " 님이 회원님을 구독 하였습니다.";
        }
        if (ALARMCOMPANYLIKEID.key.equals(key)) {
            message = name + " 님이 회원님을 좋아요 하였습니다.";
        }
        if (ALARMPERSONALLIKEID.key.equals(key)) {

            message = name + " 님이 회원님의 이력서를 좋아요 하였습니다.";

        }

        return message;
    }
}
