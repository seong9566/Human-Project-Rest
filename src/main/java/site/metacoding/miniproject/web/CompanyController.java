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
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardInsertReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardUpdateReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingAllRespDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.service.company.CompanyService;

@RestController
@RequiredArgsConstructor
public class CompanyController {

	private final HttpSession session;
	private final CompanyService companyService;

	// 회사 정보 보기
	@GetMapping("/s/api/company/detail")
	public ResponseDto<?> findByCompany() {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
		return new ResponseDto<>(1, "성공", companyService.findByCompany(signCompanyDto.getCompanyId()));
	}

	// 내정보 수정
	@PutMapping(value = "/s/api/company/update")
	public @ResponseBody ResponseDto<?> companyUpdate(@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestPart("companyUpdateReqDto") CompanyUpdateReqDto companyUpdateReqDto) {
		// ValidationCheckUtil.valCheckToUpdatePersonal(personalUpdatReqDto);
		companyUpdateReqDto.setFile(file);
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
		CompanyUpdateRespDto companyUpdateRespDto = companyService.updateCompany(principal.getUsersId(),
				signCompanyDto.getCompanyId(),
				companyUpdateReqDto);
		return new ResponseDto<>(1, "수정 성공",
				companyUpdateRespDto);
	}

	@PostMapping("/s/api/jobpostingboard/insert")
	public @ResponseBody ResponseDto<?> insertJobPostingBoard(
			@RequestBody JobPostingBoardInsertReqDto jobPostingBoardInsertReqDto) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
		jobPostingBoardInsertReqDto.setCompanyId(signCompanyDto.getCompanyId());
		return new ResponseDto<>(1, "등록 성공", companyService.insertJobPostingBoard(jobPostingBoardInsertReqDto));
	}

	// 회사가 작성한 구인 공고 리스트 보기
	@GetMapping("/s/company/jobPostingBoardList")
	public ResponseDto<?> jobPostingBoardList() {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");

		SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
		Integer companyId = signCompanyDto.getCompanyId();

		// List<JobPostingBoardAllRespDto> jobPostingBoardList =
		// companyService.jobPostingBoardList(principal.getCompanyId());

		return new ResponseDto<>(1, "성공", companyService.jobPostingBoardList(companyId));
	}

	// 내가 쓴 채용 공고 상세보기 - 인증 필요
	@GetMapping("/s/api/jobPostingBoard/detail/{jobPostingBoardId}")
	public ResponseDto<?> findByjobPostingBoard(@PathVariable Integer jobPostingBoardId) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
		Integer companyId = signCompanyDto.getCompanyId();
		return new ResponseDto<>(1, "채용공고 상세보기", companyService.jobPostingBoardDetail(jobPostingBoardId, companyId));
	}

	@PutMapping("/s/api/jobPostingBoard/update/{jobPostingBoardId}")
	public ResponseDto<?> updatejobPostingBoard(@PathVariable Integer jobPostingBoardId,
			@RequestBody JobPostingBoardUpdateReqDto jobPostingBoardUpdateReqDto) {
		// @Param("categoryId") Integer categoryId, @Param("careerId")Integer careerId,

		return new ResponseDto<>(1, "수정 성공",
				companyService.updateJobPostingBoard(jobPostingBoardUpdateReqDto, jobPostingBoardId));
	}

	// 채용 공고 삭제
	@DeleteMapping("/s/company/jobPostingBoard/delete/{jobPostingBoardId}")
	public @ResponseBody ResponseDto<?> deleteJobPostingBoard(@PathVariable Integer jobPostingBoardId) {
		companyService.deleteJobposting(jobPostingBoardId);
		return new ResponseDto<>(1, "채용공고 삭제 성공", null);
	}

	// 전체 채용공고 목록 보기 (페이징+검색+카테고리)
	@GetMapping("/jobposting/jobpostingList/{id}")
	public ResponseDto<?> findAllJobPosting(@PathVariable Integer id, Integer page, String keyword,
			JobPostingAllRespDto jobPostingAllRespDto) {
		if (page == null)
			page = 0;
		int startNum = page * 5;
		jobPostingAllRespDto.setId(id);
		jobPostingAllRespDto.setPage(page);
		jobPostingAllRespDto.setStartNum(startNum);
		jobPostingAllRespDto.setKeyword(keyword);
		return new ResponseDto<>(1, "전체 채용공고 목록 보기 성공", companyService.findAllJobPostingBoard(jobPostingAllRespDto));
	}

}
