package site.metacoding.miniproject.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyJoinDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalJoinDto;
import site.metacoding.miniproject.dto.user.UserReqDto.LoginDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.service.users.UsersService;
import site.metacoding.miniproject.utill.JWTToken.CookieForToken;
import site.metacoding.miniproject.utill.JWTToken.CreateJWTToken;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UsersController {
	private final UsersService userService;
	private final HttpSession session;

	@GetMapping("/")
	public ResponseEntity<?> healthCheck() {
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@GetMapping("/api/loginForm")
	public ResponseDto<?> loginForm() {

		ResponseDto<?> responseDto;
		SignedDto<?> signedDto = (SignedDto<?>) session.getAttribute("principal");
		if (signedDto != null) {
			responseDto = new ResponseDto<>(-1, "이미 로그인 되어 있음", signedDto);
		} else {
			responseDto = new ResponseDto<>(1, "성공", signedDto);
		}
		return responseDto;
	}

	@GetMapping("/api/logout")
	public ResponseDto<?> logout(HttpServletResponse resp) {

		Cookie cookie = new Cookie("Authorization", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		resp.addCookie(cookie);

		session.removeAttribute("principal");

		return new ResponseDto<>(1, "성공", null);
	}

	@GetMapping("/api/company/joinForm")
	public ResponseDto<?> CompanyJoinForm() {
		return new ResponseDto<>(-1, "성공", null);
	}

	@GetMapping("/api/personal/joinForm")
	public ResponseDto<?> PersonalJoinForm() {
		return new ResponseDto<>(-1, "성공", null);
	}

	@GetMapping("/api/company/companyinform")
	public ResponseDto<?> companyInform() {
		return new ResponseDto<>(-1, "성공", null);
	}

	@GetMapping("/api/checkId/{loginId}")
	public ResponseDto<?> userIdSameCheck(@PathVariable String loginId) {

		ResponseDto<?> responseDto;

		if (loginId == null || loginId == "") {
			responseDto = new ResponseDto<>(-1, "아이디를 입력하여 주세요", false);
			return responseDto;
		}

		Boolean userCheck = userService.checkUserId(loginId);

		if (userCheck) {
			responseDto = new ResponseDto<>(1, "아이디 중복 없음 사용하셔도 좋습니다.", true);
		} else {
			responseDto = new ResponseDto<>(-1, "아이디 중복이 확인됨", false);
		}

		return responseDto;
	}

	@PostMapping("/api/login")
	public ResponseDto<?> login(@RequestBody LoginDto loginDto, HttpServletResponse resp) {

		SignedDto<?> signUserDto = userService.login(loginDto);

		String token = CreateJWTToken.createToken(signUserDto);

		resp.addHeader("Authorization", "Bearer " + token);
		resp.addCookie(CookieForToken.setCookie(token));

		return new ResponseDto<>(1, "로그인완료", signUserDto);
	}

	// 개인 회원가입
	@PostMapping("/api/join/personal")
	public ResponseDto<?> joinPersonal(@RequestBody PersonalJoinDto joinDto, HttpServletResponse resp) {

		SignedDto<?> signedDto = userService.joinPersonal(joinDto);

		String token = CreateJWTToken.createToken(signedDto);

		resp.addHeader("Authorization", "Bearer " + token);
		resp.addCookie(CookieForToken.setCookie(token));

		session.setAttribute("principal", signedDto);

		return new ResponseDto<>(1, "계정생성완료", signedDto);
	}

	// 기업 회원가입
	@PostMapping(value = "/api/join/company")
	public ResponseDto<?> joinCompany(@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart("joinDto") CompanyJoinDto joinDto, HttpServletResponse resp) {
		joinDto.setFile(file);
		SignedDto<?> signedDto = userService.joinCompany(joinDto);

		String token = CreateJWTToken.createToken(signedDto);

		resp.addHeader("Authorization", "Bearer " + token);
		resp.addCookie(CookieForToken.setCookie(token));

		session.setAttribute("principal", signedDto);

		return new ResponseDto<>(1, "계정생성완료", signedDto);
	}

}