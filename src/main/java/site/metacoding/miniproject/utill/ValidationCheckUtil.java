package site.metacoding.miniproject.utill;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.util.ObjectUtils;

import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardInsertReqDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalUpdatReqDto;
import site.metacoding.miniproject.exception.NormalException;
import site.metacoding.miniproject.exception.ValCheckException;

public class ValidationCheckUtil {

	public static void valCheckToJoinPersonal(PersonalJoinDto joinDto) {

		if (joinDto == null)
			throw new NormalException("잘못된 요청입니다.");

		// 검증 오류 결과 보관
		Map<String, String> errors = new HashMap<>();
		// 검증 로직
		if (ObjectUtils.isEmpty(joinDto.getLoginId())) {
			errors.put("id", "아이디를 입력하셔야합니다.");
		}

		Boolean passwordCheck = Pattern.matches(
				"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,25}",
				joinDto.getLoginPassword());
		if (ObjectUtils.isEmpty(joinDto.getLoginPassword())
				|| (!passwordCheck)) {
			errors.put("password", "패스워드는 대문자, 소문자, 특수문자가 적어도 하나씩은 있어야 하며 최소 8자리여야 하며 최대 25자리까지 가능합니다.");
		}

		Boolean phoneNumberCheck = Pattern.matches("\\d{3}-\\d{3,4}-\\d{4}$", joinDto.getPersonalPhoneNumber());
		if (ObjectUtils.isEmpty(joinDto.getPersonalPhoneNumber()) || (!phoneNumberCheck)) {
			errors.put("personalPhoneNumber", "전화번호 입력 규격에 맞지 않습니다. ex) 010-0000-0000");
		}

		Boolean emailCheck = Pattern.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]{4,}+.{1}?[a-zA-Z]{2,3}$",
				joinDto.getPersonalEmail());
		if (ObjectUtils.isEmpty(joinDto.getPersonalEmail()) || (!emailCheck)) {
			errors.put("PersonalEmail", "이메일 입력형식으로 해주세요 ex) example@exmple.com");
		}

		// if (!ObjectUtils.isEmpty()
		// && !ObjectUtils.isEmpty(memberService.getMemberById(member.getId()))) {
		// errors.put("id", "이미 존재하는 아이디입니다");
		// }

		// if (ObjectUtils.isEmpty(member.getYear())
		// || !( >= 1900 && <= 2021)) {
		// errors.put("year", "태어난 해는 1900 ~ 2021 사이 숫자여야합니다.");
		// }

		if (!errors.isEmpty()) {
			for (String key : errors.keySet()) {
				throw new ValCheckException(errors.get(key));
			}
		}
	}

	public static void valCheckToJoinCompany(CompanyJoinDto joinDto) {

		if (joinDto == null)
			throw new NormalException("잘못된 요청입니다.");

		// 검증 오류 결과 보관
		Map<String, String> errors = new HashMap<>();
		// 검증 로직
		if (ObjectUtils.isEmpty(joinDto.getLoginId())) {
			errors.put("id", "아이디를 입력하셔야합니다.");
		}

		Boolean passwordCheck = Pattern.matches(
				"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,25}",
				joinDto.getLoginPassword());
		if (ObjectUtils.isEmpty(joinDto.getLoginPassword())
				|| (!passwordCheck)) {
			errors.put("password", "패스워드는 대문자, 소문자, 특수문자가 적어도 하나씩은 있어야 하며 최소 8자리여야 하며 최대 25자리까지 가능합니다.");
		}

		Boolean phoneNumberCheck = Pattern.matches("\\d{3}-\\d{3,4}-\\d{4}$", joinDto.getCompanyPhoneNumber());
		if (ObjectUtils.isEmpty(joinDto.getCompanyPhoneNumber()) || (!phoneNumberCheck)) {
			errors.put("personalPhoneNumber", "전화번호 입력 규격에 맞지 않습니다. ex) 010-0000-0000");
		}

		Boolean emailCheck = Pattern.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]{4,}.{1}?[a-zA-Z]{2,3}$",
				joinDto.getCompanyEmail());
		if (ObjectUtils.isEmpty(joinDto.getCompanyEmail()) || (!emailCheck)) {
			errors.put("PersonalEmail", "이메일 입력형식으로 해주세요 ex) example@exmple.com");
		}

		// if (!ObjectUtils.isEmpty()
		// && !ObjectUtils.isEmpty(memberService.getMemberById(member.getId()))) {
		// errors.put("id", "이미 존재하는 아이디입니다");
		// }

		// if (ObjectUtils.isEmpty(member.getYear())
		// || !( >= 1900 && <= 2021)) {
		// errors.put("year", "태어난 해는 1900 ~ 2021 사이 숫자여야합니다.");
		// }

		if (!errors.isEmpty()) {
			for (String key : errors.keySet()) {
				throw new ValCheckException(errors.get(key));
			}
		}
	}

	public static void valCheckToUpdatePersonal(PersonalUpdatReqDto personalUpdateReqDto) {

		// 검증 오류 결과 보관
		Map<String, String> errors = new HashMap<>();

		if (personalUpdateReqDto == null)
			throw new NormalException("잘못된 요청입니다.");

		// 패스워드
		Boolean passwordCheck = Pattern.matches(
				"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,25}",
				personalUpdateReqDto.getLoginPassword());
		if (ObjectUtils.isEmpty(personalUpdateReqDto.getLoginPassword())
				|| (!passwordCheck)) {
			errors.put("loginPassword", "패스워드는 대문자, 소문자, 특수문자가 적어도 하나씩은 있어야 하며 최소 8자리여야 하며 최대 25자리까지 가능합니다.");
		}

		Boolean phoneNumberCheck = Pattern.matches("\\d{3}-\\d{3,4}-\\d{4}$",
				personalUpdateReqDto.getPersonalPhoneNumber());
		if (ObjectUtils.isEmpty(personalUpdateReqDto.getPersonalPhoneNumber()) || (!phoneNumberCheck)) {
			errors.put("PersonalPhoneNumber", "전화번호 입력 규격에 맞지 않습니다. ex) 010-0000-0000");
		}

		Boolean emailCheck = Pattern.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]{4,}.{1}?[a-zA-Z]{2,3}$",
				personalUpdateReqDto.getPersonalEmail());
		if (ObjectUtils.isEmpty(personalUpdateReqDto.getPersonalEmail()) || (!emailCheck)) {
			errors.put("PersonalEmail", "이메일 입력형식으로 해주세요 ex) example@exmple.com");
		}

		if (!errors.isEmpty()) {
			for (String key : errors.keySet()) {
				throw new ValCheckException(errors.get(key));
			}
		}

	}

	public static void valCheckToUpdateCompany(CompanyUpdateReqDto companyUpdateReqDto) {

		if (companyUpdateReqDto == null)
			throw new NormalException("잘못된 요청입니다.");

		// 검증 오류 결과 보관
		Map<String, String> errors = new HashMap<>();

		Boolean passwordCheck = Pattern.matches(
				"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,25}",
				companyUpdateReqDto.getLoginPassword());
		if (ObjectUtils.isEmpty(companyUpdateReqDto.getLoginPassword())
				|| (!passwordCheck)) {
			errors.put("password", "패스워드는 대문자, 소문자, 특수문자가 적어도 하나씩은 있어야 하며 최소 8자리여야 하며 최대 25자리까지 가능합니다.");
		}

		Boolean phoneNumberCheck = Pattern.matches("\\d{3}-\\d{3,4}-\\d{4}$",
				companyUpdateReqDto.getCompanyPhoneNumber());
		if (ObjectUtils.isEmpty(companyUpdateReqDto.getCompanyPhoneNumber()) || (!phoneNumberCheck)) {
			errors.put("personalPhoneNumber", "전화번호 입력 규격에 맞지 않습니다. ex) 010-0000-0000");
		}

		Boolean emailCheck = Pattern.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]{4,}.{1}?[a-zA-Z]{2,3}$",
				companyUpdateReqDto.getCompanyEmail());
		if (ObjectUtils.isEmpty(companyUpdateReqDto.getCompanyEmail()) || (!emailCheck)) {
			errors.put("PersonalEmail", "이메일 입력형식으로 해주세요 ex) example@exmple.com");
		}

		if (!errors.isEmpty()) {
			for (String key : errors.keySet()) {
				throw new ValCheckException(errors.get(key));
			}
		}
	}

}
