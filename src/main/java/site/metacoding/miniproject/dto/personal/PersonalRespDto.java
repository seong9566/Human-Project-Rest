package site.metacoding.miniproject.dto.personal;

import lombok.Getter;
<<<<<<< HEAD
=======
import lombok.NoArgsConstructor;
>>>>>>> 5f7763fa1c2385f6e45660f3cfa5f858e5ffa13c
import lombok.Setter;
import site.metacoding.miniproject.domain.personal.Personal;

public class PersonalRespDto {

    @Getter
    @Setter
    @NoArgsConstructor

    public static class PersonalDetailRespDto {
        private Integer personalId;
        private String personalName;
        private String personalEmail;
        private String personalPhoneNumber;
        private String personalEducation;
        private String personalAddress;

        public PersonalDetailRespDto(Personal personal) {
            this.personalId = personal.getPersonalId();
            this.personalName = personal.getPersonalName();
            this.personalEmail = personal.getPersonalEmail();
            this.personalPhoneNumber = personal.getPersonalPhoneNumber();
            this.personalEducation = personal.getPersonalEducation();
            this.personalAddress = personal.getPersonalAddress();
        }

    }

}
