package site.metacoding.miniproject.service.company;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingBoardDetailRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingBoardInsertRespDto;
import site.metacoding.miniproject.web.dto.request.jobpostingboard.JobPostingBoardUpdateDto;
import site.metacoding.miniproject.web.dto.response.etc.PagingDto;
import site.metacoding.miniproject.web.dto.response.jobpostingboard.JobPostingBoardListDto;
import site.metacoding.miniproject.web.dto.response.personal.PersonalMainDto;

@Service
@RequiredArgsConstructor
public class CompanyService {

	private final JobPostingBoardDao jobPostingBoardDao;
	private final CategoryDao categoryDao;
	private final CareerDao careerDao;
	private final CompanyDao companyDao;
	private final UsersDao usersDao;

	public CompanyAddressRespDto findByAddress(Integer companyId) {
		return companyDao.findByAddress(companyId);
	}

	@Transactional(readOnly = true)
	public CompanyDetailRespDto findByCompany(Integer companyId) {
		CompanyAddressRespDto companyAddressRespDto = companyDao.findByAddress(companyId);
		CompanyDetailRespDto companyPS = companyDao.findByCompany(companyId);
		CompanyDetailRespDto companyDetailRespDto = new CompanyDetailRespDto(companyPS, companyAddressRespDto);
		System.out.println("디버그 : companyname은?" + companyPS.getCompanyName());
		// companyDetailRespDto.setZoneCode(companyAddressRespDto.getZoneCode());
		// companyDetailRespDto.setCompanyForAddressId(companyId);
		// companyDetailRespDto.setRoadJibunAddr(companyAddressRespDto.getRoadJibunAddr());
		// companyDetailRespDto.setDetailAddress(companyAddressRespDto.getDetailAddress());
		System.out.println("디버그: companyID는?" + companyDetailRespDto.getCompanyId());
		return companyDetailRespDto;
	}

	// 내 정보 수정에서 데이터 보여주기
	@Transactional(readOnly = true)
	public CompanyUpdateFormRespDto companyUpdateById(Integer companyId) {
		CompanyUpdateFormRespDto companyUpdateFormRespDto = companyDao.companyUpdateById(companyId);
		return companyUpdateFormRespDto;
	}

	// 내 정보 수정
	@Transactional(rollbackFor = Exception.class)
	public CompanyUpdateRespDto updateCompany(Integer userId, Integer companyId,
			CompanyUpdateReqDto companyUpdateReqDto) {

		// user패스워드 수정
		Users companyUserPS = usersDao.findById(userId);
		companyUserPS.update(companyUpdateReqDto);
		usersDao.update(companyUserPS);

		// personal 개인정보 수정
		Company companyPS = companyDao.findById(companyId);
		companyPS.updateCompany(companyUpdateReqDto);
		companyDao.update(companyPS);
		CompanyUpdateRespDto companyUpdateRespDto = new CompanyUpdateRespDto(companyPS, companyUserPS);

		return companyUpdateRespDto;
	}

	// 채용공고 작성 (category,career,jobPostingboard)
	@Transactional(rollbackFor = Exception.class)
	public JobPostingBoardInsertRespDto insertJobPostingBoard(JobPostingBoardInsertReqDto jobPostingBoardInsertReqDto) {

		Category categoryPS = jobPostingBoardInsertReqDto.JobPostingBoardInsertRespDtoToCategoryEntity();
		categoryDao.insert(categoryPS);

		Career careerPS = jobPostingBoardInsertReqDto.JobPostingBoardInsertRespDtoToCareerEntity();
		careerDao.insert(careerPS);

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
	public List<JobPostingBoardListDto> jobPostingBoardList(Integer companyId) {
		List<JobPostingBoardListDto> postingList = jobPostingBoardDao.jobPostingBoardList(companyId);
		// TimeStamp to String
		for (JobPostingBoardListDto deadLine : postingList) {
			Timestamp ts = deadLine.getJobPostingBoardDeadline();
			Date date = new Date();
			date.setTime(ts.getTime());
			String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
			deadLine.setFormatDeadLine(formattedDate);
		}
		return postingList;
	}

	// 채용공고 상세 보기
	public JobPostingBoardDetailRespDto jobPostingBoardDetail(Integer jobPostingBoardId) {
		JobPostingBoardDetailRespDto jobPostingBoardDetailRespDto = jobPostingBoardDao.findByDetail(jobPostingBoardId);
		Timestamp ts = jobPostingBoardDetailRespDto.getJobPostingBoardDeadline();
		Date date = new Date();
		date.setTime(ts.getTime());
		String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
		jobPostingBoardDetailRespDto.setFormatDeadLine(formattedDate);
		return jobPostingBoardDetailRespDto;
	}

	// 채용공고 수정 (jobpostingboard,career,Category)
	@Transactional(rollbackFor = Exception.class)
	public void updateJobPostingBoard(Integer jobPostingBoardId, JobPostingBoardUpdateDto updateDto) {
		// Integer categoryId,Integer careerId,
		Category category = new Category(updateDto);
		categoryDao.jobPostingUpdate(category);

		Career career = new Career(updateDto);
		careerDao.jobPostingUpdate(career);

		JobPostingBoard jobPostingBoard = new JobPostingBoard(jobPostingBoardId, updateDto);
		jobPostingBoardDao.update(jobPostingBoard);
	}

	// 채용 공고 삭제
	@Transactional(rollbackFor = Exception.class)
	public void deleteJobposting(Integer jobPostingBoardId) {
		jobPostingBoardDao.deleteById(jobPostingBoardId);
	}

	// 전체 채용공고 리스트
	public List<PersonalMainDto> findAll(int startNum) {
		List<PersonalMainDto> personalMainPS = jobPostingBoardDao.findAll(startNum);
		// TimeStamp to String
		for (PersonalMainDto deadLine : personalMainPS) {
			Timestamp ts = deadLine.getJobPostingBoardDeadline();
			Date date = new Date();
			date.setTime(ts.getTime());
			String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
			deadLine.setFormatDeadLine(formattedDate);
		}
		return personalMainPS;
	}

	// 페이징
	public PagingDto jobPostingBoardPaging(Integer page, String keyword) {
		return jobPostingBoardDao.jobPostingBoardPaging(page, keyword);
	}

	// 검색 결과 리스트
	public List<PersonalMainDto> findSearch(int startNum, String keyword) {
		List<PersonalMainDto> personalMainPS = jobPostingBoardDao.findSearch(startNum, keyword);
		// TimeStamp to String
		for (PersonalMainDto deadLine : personalMainPS) {
			Timestamp ts = deadLine.getJobPostingBoardDeadline();
			Date date = new Date();
			date.setTime(ts.getTime());
			String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
			deadLine.setFormatDeadLine(formattedDate);
		}
		return personalMainPS;
	}

	// 카테고리 별 리스트 보기
	public List<PersonalMainDto> findCategory(int startNum, Integer id) {
		List<PersonalMainDto> personalMainPS = jobPostingBoardDao.findCategory(startNum, id);
		// TimeStamp to String
		for (PersonalMainDto deadLine : personalMainPS) {
			Timestamp ts = deadLine.getJobPostingBoardDeadline();
			Date date = new Date();
			date.setTime(ts.getTime());
			String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
			deadLine.setFormatDeadLine(formattedDate);
		}
		return personalMainPS;
	}

	// 카테고리 별 검색 결과 리스트
	public List<PersonalMainDto> findCategorySearch(int startNum, String keyword, Integer id) {
		List<PersonalMainDto> personalMainPS = jobPostingBoardDao.findCategorySearch(startNum, keyword, id);
		// TimeStamp to String
		for (PersonalMainDto deadLine : personalMainPS) {
			Timestamp ts = deadLine.getJobPostingBoardDeadline();
			Date date = new Date();
			date.setTime(ts.getTime());
			String formattedDate = new SimpleDateFormat("yyyy년MM월dd일").format(date);
			deadLine.setFormatDeadLine(formattedDate);
		}
		return personalMainPS;
	}

}
