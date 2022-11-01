package site.metacoding.miniproject.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyInfoRespDto;
import site.metacoding.miniproject.service.company.CompanyService;
import site.metacoding.miniproject.web.dto.request.company.CompanyUpdateDto;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardInsertDto;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardUpdateDto;
import site.metacoding.miniproject.web.dto.response.ResponseDto;
import site.metacoding.miniproject.web.dto.response.company.CompanyAddressDto;
import site.metacoding.miniproject.web.dto.response.company.CompanyInfoDto;
import site.metacoding.miniproject.web.dto.response.etc.SignedDto;
import site.metacoding.miniproject.web.dto.response.jobpostingboard.JobPostingBoardDetailDto;
import site.metacoding.miniproject.web.dto.response.jobpostingboard.JobPostingBoardListDto;

@Controller
@RequiredArgsConstructor
public class CompanyController {

	private final HttpSession session;
	private final CompanyService companyService;

	// 회사 정보 보기
	@GetMapping("/api/company/inform")
	public ResponseDto<?> inform() {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		return new ResponseDto<>(1, "성공", companyService.findByCompany(principal.getCompanyId()));
	}

	// 회사 정보 업데이트
	@GetMapping("/company/companyInform/companyUpdate")
	public String companyUpdateForm(Model model) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		CompanyInfoDto companyPS = companyService.findCompanyInfo(principal.getCompanyId());
		CompanyAddressDto addressPS = companyService.findByAddress(principal.getCompanyId());
		model.addAttribute("address", addressPS);
		model.addAttribute("companyInfo", companyPS);
		return "company/companyUpdate";
	}

	@PutMapping(value = "/company/companyInform/update")
	public @ResponseBody ResponseDto<?> companyUpdate(@RequestPart("file") MultipartFile file,
			@RequestPart("companyUpdateDto") CompanyUpdateDto companyUpdateDto) throws Exception {
		int pos = file.getOriginalFilename().lastIndexOf('.');
		String extension = file.getOriginalFilename().substring(pos + 1);
		String filePath = "C:\\Temp\\img\\";
		// String filePath = "/Users/ihyeonseong/Desktop/img";//Mac전용 경로
		String imgSaveName = UUID.randomUUID().toString();
		String imgName = imgSaveName + "." + extension;
		File makeFileFolder = new File(filePath);
		if (!makeFileFolder.exists()) {
			if (!makeFileFolder.mkdir()) {
				throw new Exception("File.mkdir():Fail.");
			}
		}
		File dest = new File(filePath, imgName);
		try {
			Files.copy(file.getInputStream(), dest.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		companyUpdateDto.setCompanyPicture(imgName);
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		companyService.updateCompany(principal.getUsersId(), principal.getCompanyId(), companyUpdateDto);
		return new ResponseDto<>(1, "수정 성공", null);
	}

	// 채용 공고 작성
	@GetMapping("/company/insertForm")
	public String insertjobPostingBoard(Model model) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		CompanyInfoDto companyPS = companyService.findCompanyInfo(principal.getCompanyId());
		CompanyAddressDto addressPS = companyService.findByAddress(principal.getCompanyId());
		model.addAttribute("address", addressPS);
		model.addAttribute("companyInfo", companyPS);
		model.addAttribute("principal", principal);
		return "company/jobPostingBoardInsert";
	}

	@PostMapping("/company/jobPostingBoard/insert")
	public @ResponseBody ResponseDto<?> insertJobPostingBoard(@RequestBody JobPostingBoardInsertDto insertDto) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		companyService.insertJobPostingBoard(principal.getCompanyId(), insertDto);
		return new ResponseDto<>(1, "등록 성공", null);
	}

	// 회사가 작성한 구인 공고 리스트 보기
	@GetMapping("/company/jobPostingBoardList")
	public String jobPostingBoardList(Model model, Integer companyId) {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		List<JobPostingBoardListDto> jobPostingBoardList = companyService.jobPostingBoardList(principal.getCompanyId());
		model.addAttribute("jobPostingBoardList", jobPostingBoardList);
		return "company/jobPostingBoardList";
	}

	// 채용 공고 상세보기
	// @GetMapping("/api/company/jobPostingBoardDetail/{jobPostingBoardId}")
	// public String jobPostingBoardDetail(Model model, @PathVariable Integer
	// jobPostingBoardId) {
	// return new ResponseDto<>(1, "성공",
	// companyService.findByCompany(principal.getCompanyId()));
	// JobPostingBoardDetailDto jobPostingPS =
	// companyService.jobPostingOne(jobPostingBoardId);
	// SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
	// CompanyAddressDto addressPS =
	// companyService.findByAddress(principal.getCompanyId());
	// model.addAttribute("address", addressPS);
	// model.addAttribute("jobPostingPS", jobPostingPS);
	// return "company/jobPostingBoardDetail";
	// }

	@GetMapping("/api/company/jobPostingBoardDetail/{jobPostingBoardId}")
	public ResponseDto<?> jobPostingBoardDetail() {
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		return new ResponseDto<>(1, "성공", companyService.jobPostingOne(jobPostingBoardId));
	}

	// 채용 공고 수정 폼
	@GetMapping("/company/jobPostingBoardUpdate/{jobPostingBoardId}")
	public String jobPostingBoardUpdate(Model model, @PathVariable Integer jobPostingBoardId) {
		JobPostingBoardDetailDto jobPostingPS = companyService.jobPostingOne(jobPostingBoardId);
		SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
		CompanyAddressDto addressPS = companyService.findByAddress(principal.getCompanyId());
		model.addAttribute("address", addressPS);
		model.addAttribute("jobPostingPS", jobPostingPS);
		return "company/jobPostingBoardUpdate";
	}

	@PutMapping("/company/jobPostingBoardUpdate/{jobPostingBoardId}")
	public @ResponseBody ResponseDto<?> companyUpdate(@PathVariable Integer jobPostingBoardId,
			@RequestBody JobPostingBoardUpdateDto jobPostingBoardUdateDto) {
		// @Param("categoryId") Integer categoryId, @Param("careerId")Integer careerId,
		companyService.updateJobPostingBoard(jobPostingBoardId, jobPostingBoardUdateDto);
		return new ResponseDto<>(1, "수정 성공", null);
	}

	// 채용 공고 삭제
	@DeleteMapping("/company/jobPostingBoard/delete/{jobPostingBoardId}")
	public @ResponseBody ResponseDto<?> deleteResumes(@PathVariable Integer jobPostingBoardId) {
		companyService.deleteJobposting(jobPostingBoardId);
		return new ResponseDto<>(1, "채용공고 삭제 성공", null);
	}

}
