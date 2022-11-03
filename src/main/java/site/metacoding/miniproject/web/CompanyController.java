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
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardInsertReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardUpdateReqDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;
import site.metacoding.miniproject.service.company.CompanyService;
import site.metacoding.miniproject.service.personal.PersonalService;
import site.metacoding.miniproject.web.dto.response.ResponseDto;

@RestController
@RequiredArgsConstructor
public class CompanyController {

	private final HttpSession session;
	private final CompanyService companyService;

	// 회사 정보 보기
	@GetMapping("/s/api/company/detail")
	public ResponseDto<?> findByCompany() {
		SignedDto principal = (SignedDto<?>) session.getAttribute("principal");
		SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
		return new ResponseDto<>(1, "성공", companyService.findByCompany(signCompanyDto.getCompanyId()));
	}

	// // 내정보 수정 폼
	// @GetMapping("/s/api/company/update")
	// public ResponseDto<?> companyInformUpdate() {
	// SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
	// SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
	// return new ResponseDto<>(1, "성공",
	// companyService.companyUpdateById(signCompanyDto.getCompanyId()));

	// }

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

	// @PutMapping(value = "/company/companyInform/update")
	// public @ResponseBody ResponseDto<?> companyUpdate(@RequestPart("file")
	// MultipartFile file,
	// @RequestPart("companyUpdateDto") CompanyUpdateDto companyUpdateDto) throws
	// Exception {
	// int pos = file.getOriginalFilename().lastIndexOf('.');
	// String extension = file.getOriginalFilename().substring(pos + 1);
	// String filePath = "C:\\Temp\\img\\";
	// // String filePath = "/Users/ihyeonseong/Desktop/img";//Mac전용 경로
	// String imgSaveName = UUID.randomUUID().toString();
	// String imgName = imgSaveName + "." + extension;
	// File makeFileFolder = new File(filePath);
	// if (!makeFileFolder.exists()) {
	// if (!makeFileFolder.mkdir()) {
	// throw new Exception("File.mkdir():Fail.");
	// }
	// }
	// File dest = new File(filePath, imgName);
	// try {
	// Files.copy(file.getInputStream(), dest.toPath());
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// companyUpdateDto.setCompanyPicture(imgName);
	// SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
	// companyService.updateCompany(principal.getUsersId(),
	// principal.getCompanyId(), companyUpdateDto);
	// return new ResponseDto<>(1, "수정 성공", null);
	// }

	// // 채용 공고 작성 폼
	// @GetMapping("/s/api/jobpostingboard/insert")
	// public ResponseDto<?> insertjobPostingBoardForm() {
	// SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
	// SignCompanyDto signCompanyDto = (SignCompanyDto) principal.getUserInfo();
	// return new ResponseDto<>(1, "채용 공고 작성 폼 데이터 ",
	// companyService.findByCompany(signCompanyDto.getCompanyId()));
	// }

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

	// // 채용 공고 수정 폼
	// @GetMapping("/company/jobPostingBoardUpdate/{jobPostingBoardId}")
	// public String jobPostingBoardUpdate(Model model, @PathVariable Integer
	// jobPostingBoardId) {
	// JobPostingBoardDetailDto jobPostingPS =
	// companyService.jobPostingOne(jobPostingBoardId);
	// SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
	// CompanyAddressRespDto addressPS =
	// companyService.findByAddress(principal.getCompanyId());
	// model.addAttribute("address", addressPS);
	// model.addAttribute("jobPostingPS", jobPostingPS);
	// return "company/jobPostingBoardUpdate";
	// }

	@PutMapping("/api/jobposting/update/{jobPostingBoardId}")
	public ResponseDto<?> companyUpdate(@PathVariable Integer jobPostingBoardId,
			@RequestBody JobPostingBoardUpdateReqDto jobPostingBoardUpdateReqDto) {
		// @Param("categoryId") Integer categoryId, @Param("careerId")Integer careerId,

		System.out.println("kkkservice" + jobPostingBoardUpdateReqDto.getCareerId());

		return new ResponseDto<>(1, "수정 성공",
				companyService.updateJobPostingBoard(jobPostingBoardUpdateReqDto, jobPostingBoardId));
	}

	// 채용 공고 삭제
	@DeleteMapping("/s/company/jobPostingBoard/delete/{jobPostingBoardId}")
	public @ResponseBody ResponseDto<?> deleteResumes(@PathVariable Integer jobPostingBoardId) {
		companyService.deleteJobposting(jobPostingBoardId);
		return new ResponseDto<>(1, "채용공고 삭제 성공", null);
	}

}
