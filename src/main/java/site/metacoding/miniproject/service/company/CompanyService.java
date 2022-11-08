package site.metacoding.miniproject.service.company;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.career.Career;
import site.metacoding.miniproject.domain.career.CareerDao;
import site.metacoding.miniproject.domain.category.Category;
import site.metacoding.miniproject.domain.category.CategoryDao;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.domain.jobpostingboard.JobPostingBoard;
import site.metacoding.miniproject.domain.jobpostingboard.JobPostingBoardDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyUpdateReqDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyAddressRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyDetailRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateFormRespDto;
import site.metacoding.miniproject.dto.company.CompanyRespDto.CompanyUpdateRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardInsertReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardReqDto.JobPostingBoardUpdateReqDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingAllRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingBoardAllRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingBoardDetailRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingBoardInsertRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingBoardUpdateRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.PagingDto;
import site.metacoding.miniproject.exception.ApiException;

@Service
@RequiredArgsConstructor
public class CompanyService {

	private final JobPostingBoardDao jobPostingBoardDao;
	private final CategoryDao categoryDao;
	private final CareerDao careerDao;
	private final CompanyDao companyDao;
	private final UsersDao usersDao;

	@Transactional(readOnly = true)
	public CompanyDetailRespDto findByCompany(Integer companyId) {
		CompanyDetailRespDto companyPS = companyDao.findByCompany(companyId);
		if (companyPS == null) {
			throw new ApiException("회사 정보를 찾을 수 없습니다.");
		}

		String address = companyPS.getCompanyAddress();
		String[] arry = address.split(",");
		for (int i = 0; i < arry.length; i++) {
			companyPS.setZoneCode(arry[0]);
			companyPS.setRoadJibunAddr(arry[1]);
			companyPS.setDetailAddress(arry[2]);
		}
		CompanyDetailRespDto companyDetailRespDto = new CompanyDetailRespDto(companyPS);
		return companyDetailRespDto;
	}

	// 내 정보 수정에서 데이터 보여주기
	@Transactional(readOnly = true)
	public CompanyUpdateFormRespDto companyUpdateById(Integer companyId) {
		CompanyUpdateFormRespDto companyUpdateFormRespDto = companyDao.companyUpdateById(companyId);
		if (companyUpdateFormRespDto == null) {
			throw new ApiException("회사 정보를 찾을 수 없습니다.");
		}
		return companyUpdateFormRespDto;
	}

	// 회사 정보 수정
	// 회사 정보 수정에 대한 validation 필요함.
	@Transactional(rollbackFor = Exception.class)
	public CompanyUpdateRespDto updateCompany(Integer userId, Integer companyId,
			CompanyUpdateReqDto companyUpdateReqDto) {

		try {
			companyUpdateReqDto.companyUpdateDtoPictureSet();
		} catch (Exception e) {
			throw new ApiException("멀티파트 폼 에러");
		}
		// user패스워드 수정
		Users companyUserPS = usersDao.findById(userId);
		if (companyUserPS == null) {
			throw new ApiException("해당 " + userId + " 유저 아이디로 찾을 수 없습니다.");
		}
		companyUserPS.update(companyUserPS);
		usersDao.update(companyUserPS);

		// personal 개인정보 수정
		Company companyPS = companyDao.findById(companyId);
		if (companyPS == null) {
			throw new ApiException("해당 " + companyId + " 유저 아이디로 찾을 수 없습니다.");
		}
		companyPS.updateCompany(companyUpdateReqDto);
		companyDao.update(companyPS);
		CompanyUpdateRespDto companyUpdateRespDto = new CompanyUpdateRespDto(companyPS, companyUserPS);

		return companyUpdateRespDto;
	}

	// 채용공고 작성 (category,career,jobPostingboard)
	// 채용공고 작성에 대한 validation 필요함.
	@Transactional(rollbackFor = Exception.class)
	public JobPostingBoardInsertRespDto insertJobPostingBoard(JobPostingBoardInsertReqDto jobPostingBoardInsertReqDto) {
		if (jobPostingBoardInsertReqDto.getCompanyId() == null) {
			throw new ApiException("companyId가 null입니다.");
		}
		Category categoryPS = jobPostingBoardInsertReqDto.JobPostingBoardInsertRespDtoToCategoryEntity();
		categoryDao.insert(categoryPS);

		Career careerPS = jobPostingBoardInsertReqDto.JobPostingBoardInsertRespDtoToCareerEntity();
		careerDao.insert(careerPS);
		// if 체크
		if (!(categoryPS.getCategoryId() != null && careerPS.getCareerId() != null)) {
			throw new ApiException("채용공고 작성 에러");
		}
		JobPostingBoard jobPostingBoardPS = jobPostingBoardInsertReqDto
				.JobPostingBoardInsertReqDtoJobPostingBoardToEntity();
		jobPostingBoardPS.setJobPostingBoardCategoryId(categoryPS.getCategoryId());
		jobPostingBoardPS.setJobPostingBoardCareerId(careerPS.getCareerId());
		jobPostingBoardDao.insert(jobPostingBoardPS);

		JobPostingBoardInsertRespDto jobPostingBoardInsertRespDto = new JobPostingBoardInsertRespDto(jobPostingBoardPS,
				categoryPS, careerPS);
		return jobPostingBoardInsertRespDto;
	}

	// 채용공고 리스트
	public List<JobPostingBoardAllRespDto> jobPostingBoardList(Integer companyId) {
		List<JobPostingBoardAllRespDto> jobPostingBoardList = jobPostingBoardDao
				.jobPostingBoardList(companyId);
		if (jobPostingBoardList == null) {
			throw new ApiException("채용공고가 없습니다.");
		}
		// TimeStamp to String
		for (JobPostingBoardAllRespDto deadLine : jobPostingBoardList) {
			Timestamp ts = deadLine.getJobPostingBoardDeadline();
			Date date = new Date();
			date.setTime(ts.getTime());
			String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
			deadLine.setFormatDeadLine(formattedDate);
		}

		return jobPostingBoardList;
	}

	// 채용공고 상세 보기
	@Transactional(readOnly = true)
	public JobPostingBoardDetailRespDto jobPostingBoardDetail(Integer jobPostingBoardId, Integer companyId) {
		// .. 로직이 너무 더러운데..
		Company companyPS = companyDao.findById(companyId);
		JobPostingBoard jobPostingBoardPS = jobPostingBoardDao.findById(jobPostingBoardId);
		if (jobPostingBoardPS == null) {
			throw new ApiException("해당 " + jobPostingBoardId + " 로 채용공고를 찾을 수 없습니다.");
		}

		if (companyPS == null || jobPostingBoardPS.getCompanyId() != companyPS.getCompanyId()) {
			throw new ApiException("해당 채용공고 작성 회사가 아닙니다.");
		}
		JobPostingBoardDetailRespDto jobPostingBoardDetailRespDto = jobPostingBoardDao
				.findByJobPostingBoard(jobPostingBoardId);
		Timestamp ts = jobPostingBoardDetailRespDto.getJobPostingBoardDeadline();
		Date date = new Date();
		date.setTime(ts.getTime());
		String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
		jobPostingBoardDetailRespDto.setFormatDeadLine(formattedDate);
		return jobPostingBoardDetailRespDto;
	}

	// 채용공고 수정 (jobpostingboard,career,Category)
	@Transactional(rollbackFor = Exception.class)
	public JobPostingBoardUpdateRespDto updateJobPostingBoard(
			JobPostingBoardUpdateReqDto jobPostingBoardUpdateReqDto, Integer jobPostingBoardId) {
		// Integer categoryId,Integer careerId,
		jobPostingBoardUpdateReqDto.setJobPostingBoardId(jobPostingBoardId);
		JobPostingBoard jobPostingBoardPS2Board = jobPostingBoardUpdateReqDto.jobPostingBoardUpdate();

		jobPostingBoardDao.update(jobPostingBoardPS2Board);
		JobPostingBoard jobPostingBoardPS = jobPostingBoardDao.findById(jobPostingBoardId);

		Category categoryPS = categoryDao.findById(jobPostingBoardPS.getJobPostingBoardCategoryId());
		categoryPS = jobPostingBoardUpdateReqDto.jobPostingUpdateReqDtoToCategoryEntity();
		categoryDao.update(categoryPS);

		Career careerPS = careerDao.findById(jobPostingBoardPS.getJobPostingBoardCareerId());
		careerPS.updateCareer(jobPostingBoardUpdateReqDto);
		careerDao.update(careerPS);
		JobPostingBoardUpdateRespDto jobPostingBoardUpdateRespDto = new JobPostingBoardUpdateRespDto(jobPostingBoardPS,
				categoryPS, careerPS);

		return jobPostingBoardUpdateRespDto;

	}

	// 채용 공고 삭제
	@Transactional(rollbackFor = Exception.class)
	public void deleteJobposting(Integer jobPostingBoardId) {
		JobPostingBoard jobPostingBoard = jobPostingBoardDao.findById(jobPostingBoardId);
		if (jobPostingBoard == null) {
			throw new RuntimeException("해당" + jobPostingBoardId + "로 삭제할수 없습니다.");
		}

		jobPostingBoardDao.deleteById(jobPostingBoardId);
	}

	// 전체 채용공고 목록 보기 (페이징+검색+카테고리id별)
	@Transactional(readOnly = true)
	public List<JobPostingAllRespDto> findAllJobPostingBoard(JobPostingAllRespDto jobPostingAllRespDto) {
		if (jobPostingAllRespDto.getKeyword() == null || jobPostingAllRespDto.getKeyword().isEmpty()) {
			List<JobPostingBoard> jobPostingBoardList = jobPostingBoardDao.findCategory(
					jobPostingAllRespDto.getStartNum(),
					jobPostingAllRespDto.getId());
			List<JobPostingAllRespDto> jobPostingAllRespDtoList = new ArrayList<>();
			for (JobPostingBoard jobPostingBoard : jobPostingBoardList) {
				Timestamp ts = jobPostingBoard.getJobPostingBoardDeadline();
				Date date = new Date();
				date.setTime(ts.getTime());
				String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
				jobPostingBoard.setFormatDeadLine(formattedDate);
				jobPostingAllRespDtoList.add(new JobPostingAllRespDto(jobPostingBoard));
			}
			PagingDto paging = jobPostingBoardDao.jobPostingBoardPaging(jobPostingAllRespDto.getPage(),
					jobPostingAllRespDto.getKeyword());
			paging.makeBlockInfo(jobPostingAllRespDto.getKeyword());
			return jobPostingAllRespDtoList;
		} else {
			List<JobPostingBoard> jobPostingBoardList = jobPostingBoardDao.findCategorySearch(
					jobPostingAllRespDto.getStartNum(),
					jobPostingAllRespDto.getKeyword(), jobPostingAllRespDto.getId());
			// TimeStamp to String
			for (JobPostingBoard deadLine : jobPostingBoardList) {
				Timestamp ts = deadLine.getJobPostingBoardDeadline();
				Date date = new Date();
				date.setTime(ts.getTime());
				String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
				deadLine.setFormatDeadLine(formattedDate);
			}
			List<JobPostingAllRespDto> jobPostingAllRespDtoList = new ArrayList<>();
			for (JobPostingBoard jobPostingBoard : jobPostingBoardList) {
				jobPostingAllRespDtoList.add(new JobPostingAllRespDto(jobPostingBoard));
			}
			PagingDto paging = jobPostingBoardDao.jobPostingBoardPaging(jobPostingAllRespDto.getPage(),
					jobPostingAllRespDto.getKeyword());
			paging.makeBlockInfo(jobPostingAllRespDto.getKeyword());
			return jobPostingAllRespDtoList;
		}
	}

}
