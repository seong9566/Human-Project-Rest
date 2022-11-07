package site.metacoding.miniproject.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.like.LikeReqDto.CompanyLikeReqDto;
import site.metacoding.miniproject.dto.like.LikeReqDto.PersonalLikeReqDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.service.company.CompanyLikeService;
import site.metacoding.miniproject.service.personal.PersonalLikeService;

@RequiredArgsConstructor
@RestController
public class LikeController {
	private final HttpSession session;
	private final PersonalLikeService personalLikeService;
	private final CompanyLikeService companyLikeService;

	@PostMapping("/s/api/personalLike/{resumesId}")
	public ResponseDto<?> insertPersonalLike(@PathVariable Integer resumesId,
			PersonalLikeReqDto personalLikeReqDto) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
		personalLikeReqDto.setCompanyId(signCompanyDto.getCompanyId());
		return new ResponseDto<>(1, "좋아요성공", personalLikeService.좋아요(resumesId, personalLikeReqDto));

	}

	@DeleteMapping("/s/api/personalLike/{resumesId}")
	public ResponseDto<?> deletePersonalLike(@PathVariable Integer resumesId) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
		personalLikeService.좋아요취소(resumesId, signCompanyDto.getCompanyId());
		return new ResponseDto<>(1, "좋아요취소", null);
	}

	@GetMapping("/s/api/resumeList")
	public ResponseDto<?> findAllPersonalLike(Integer companyId) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
		return new ResponseDto<>(1, "좋아요 ", personalLikeService.좋아요이력서(signCompanyDto.getCompanyId()));
	}

	@PostMapping("/s/api/companyLike/{companyId}")
	public ResponseDto<?> insertCompanyLike(@PathVariable Integer companyId,
			CompanyLikeReqDto companyLikeReqDto) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignPersonalDto signPersonalDto = (SignPersonalDto) principal.getUserInfo();
		companyLikeReqDto.setPersonalId(signPersonalDto.getPersonalId());
		return new ResponseDto<>(1, "좋아요 성공", companyLikeService.좋아요(companyId, companyLikeReqDto));

	}

	@DeleteMapping("/s/api/companyLike/{companyId}")
	public ResponseDto<?> deleteCompanyLike(@PathVariable Integer companyId) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignPersonalDto signPersonalDto = (SignPersonalDto) principal.getUserInfo();
		companyLikeService.좋아요취소(signPersonalDto.getPersonalId(), companyId);
		return new ResponseDto<>(1, "좋아요 취소 성공", null);
	}

	@GetMapping("/api/bestcompany")
	public ResponseDto<?> bestCompany(Integer companyId) {

		return new ResponseDto<>(1, "좋아요 많이 받은 회사",
				companyLikeService.bestcompany(companyId));
	}
}