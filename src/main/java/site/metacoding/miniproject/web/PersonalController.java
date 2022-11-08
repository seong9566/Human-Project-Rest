package site.metacoding.miniproject.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.ResponseDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyDetailWithPerRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingDetailWithPersonalRespDto;
import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalUpdatReqDto;
import site.metacoding.miniproject.dto.personal.PersonalRespDto.PersonalUpdateRespDto;
import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesInsertReqDto;
import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesUpdateReqDto;
import site.metacoding.miniproject.dto.resumes.ResumesRespDto.ResumesAllByIdRespDto;
import site.metacoding.miniproject.dto.resumes.ResumesRespDto.ResumesAllRespDto;
import site.metacoding.miniproject.dto.resumes.ResumesRespDto.ResumesUpdateRespDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.exception.ApiException;
import site.metacoding.miniproject.service.personal.PersonalService;

@RequiredArgsConstructor
@RestController
public class PersonalController {

	private final HttpSession session;
	private final PersonalService personalService;

	// 이력서 작성 하기
	@PostMapping(value = "/s/resumes/insert")
	public ResponseDto<?> insertResumes(@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart("reqDto") ResumesInsertReqDto resumesInsertReqDto) throws Exception {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignPersonalDto signPersonalDto = (SignPersonalDto) principal.getUserInfo();
		resumesInsertReqDto.setPersonalId(signPersonalDto.getPersonalId());
		resumesInsertReqDto.setFile(file);
		return new ResponseDto<>(1, "이력서 등록 성공", personalService.insertResumes(resumesInsertReqDto));
	}

	// 내가 작성한 이력서 목록 보기
	@GetMapping("/s/resumes/myList")
	public ResponseDto<?> findAllMyResumes(ResumesAllByIdRespDto resumesAllByIdRespDto) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignPersonalDto signPersonalDto = (SignPersonalDto) principal.getUserInfo();
		resumesAllByIdRespDto.setPersonalId(signPersonalDto.getPersonalId());
		return new ResponseDto<>(1, "내 이력서 목록 보기 성공", personalService.findAllMyResumes(resumesAllByIdRespDto));
	}

	// 이력서 상세 보기
	@GetMapping("/resumes/{resumesId}")
	public ResponseDto<?> findByResumesId(@PathVariable Integer resumesId) {
		// SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		// PersonalLike personalLike = personalLikeService.좋아요확인(resumesId,
		// signedDto.getCompanyId());
		// model.addAttribute("personalLike", personalLike);
		return new ResponseDto<>(1, "내 이력서 상세 보기 성공", personalService.findByResumesId(resumesId));
	}

	// 이력서 수정
	@PutMapping(value = "/s/resumes/update/{resumesId}")
	public ResponseDto<?> updateResumes(@PathVariable Integer resumesId,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart("resumesUpdateReqDto") ResumesUpdateReqDto resumesUpdateReqDto)
			throws Exception {
		resumesUpdateReqDto.setFile(file);
		resumesUpdateReqDto.setResumesId(resumesId);
		ResumesUpdateRespDto resumesUpdateRespDto = personalService.updateResumes(resumesUpdateReqDto);
		return new ResponseDto<>(1, "이력서 수정 성공", resumesUpdateRespDto);
	}

	// 이력서 삭제 하기
	@DeleteMapping("/s/resumes/delete/{resumesId}")
	public ResponseDto<?> deleteResumes(@PathVariable Integer resumesId) {
		personalService.deleteResumes(resumesId);
		return new ResponseDto<>(1, "이력서 삭제 성공", null);
	}

	// 전체 이력서 목록 보기 (페이징+검색+카테고리)
	@GetMapping("/resumes/resumesList/{id}")
	public ResponseDto<?> findAllResumes(@PathVariable Integer id, Integer page, String keyword,
			ResumesAllRespDto resumesAllRespDto) {
		if (page == null)
			page = 0;
		int startNum = page * 5;
		resumesAllRespDto.setId(id);
		resumesAllRespDto.setPage(page);
		resumesAllRespDto.setStartNum(startNum);
		resumesAllRespDto.setKeyword(keyword);
		return new ResponseDto<>(1, "전체 이력서 목록 보기 성공", personalService.findAllResumes(resumesAllRespDto));
	}

	// 내정보 보기
	@GetMapping("/s/api/personal/detail")
	public ResponseDto<?> findByPersonal() {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignPersonalDto signPersonalDto = (SignPersonalDto) principal.getUserInfo();

		return new ResponseDto<>(1, "성공", personalService.findByPersonal(signPersonalDto.getPersonalId()));
		// principal.getUserinfo().getPersonalId()
	}

	// 내정보 수정
	@PutMapping("/s/api/personal/update")
	public @ResponseBody ResponseDto<?> personalUpdate(@RequestBody PersonalUpdatReqDto personalUpdatReqDto) {
		// ValidationCheckUtil.valCheckToUpdatePersonal(personalUpdatReqDto);
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignPersonalDto signPersonalDto = (SignPersonalDto) principal.getUserInfo();
		PersonalUpdateRespDto personalUpdateRespDto = personalService.updatePersonal(principal.getUsersId(),
				signPersonalDto.getPersonalId(),
				personalUpdatReqDto);
		return new ResponseDto<>(1, "수정 성공", personalUpdateRespDto);
	}

	// 채용공고 상세 보기 (개인)
	@GetMapping("/personal/jobPostingBoard/{jobPostingBoardId}")
	public ResponseDto<?> jobPostingDetailForm(@PathVariable Integer jobPostingBoardId) {
		JobPostingDetailWithPersonalRespDto jobPostingPS = personalService.jobPostingBoardDetail(jobPostingBoardId);
		return new ResponseDto<>(1, "채용공고 상세보기", jobPostingPS);
	}

	// 회사 정보보러 가기(개인)
	@GetMapping("/personal/company/{companyId}")
	public ResponseDto<?> companyDetailform(@PathVariable Integer companyId) {
		CompanyDetailWithPerRespDto companyPS = personalService.findByCompany(companyId);
		if (companyPS == null) {
			throw new ApiException("해당 회사를 찾을 수 없습니다.");
		}
		return new ResponseDto<>(1, "회사정보보기", companyPS);
	}

}
