package site.metacoding.miniproject.service.personal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.config.handler.exception.ApiException;
import site.metacoding.miniproject.domain.career.Career;
import site.metacoding.miniproject.domain.career.CareerDao;
import site.metacoding.miniproject.domain.category.Category;
import site.metacoding.miniproject.domain.category.CategoryDao;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.personal.PersonalDao;
import site.metacoding.miniproject.domain.portfolio.Portfolio;
import site.metacoding.miniproject.domain.portfolio.PortfolioDao;
import site.metacoding.miniproject.domain.resumes.Resumes;
import site.metacoding.miniproject.domain.resumes.ResumesDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.dto.personal.PersonalRespDto.PersonalDetailRespDto;
import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesInsertReqDto;
import site.metacoding.miniproject.dto.resumes.ResumesRespDto.ResumesAllRespDto;
import site.metacoding.miniproject.dto.resumes.ResumesRespDto.ResumesDetailRespDto;
import site.metacoding.miniproject.dto.resumes.ResumesRespDto.ResumesInsertRespDto;
import site.metacoding.miniproject.web.dto.request.personal.PersonalUpdateDto;
import site.metacoding.miniproject.web.dto.request.resume.ResumesUpdateDto;
import site.metacoding.miniproject.web.dto.response.company.CompanyMainDto;
import site.metacoding.miniproject.web.dto.response.etc.PagingDto;
import site.metacoding.miniproject.web.dto.response.personal.PersonalAddressDto;

@Service
@RequiredArgsConstructor
public class PersonalService {

	private final PersonalDao personalDao;
	private final ResumesDao resumesDao;
	private final CategoryDao categoryDao;
	private final PortfolioDao portfolioDao;
	private final CareerDao careerDao;
	private final UsersDao userDao;

	// 이력서 작성 하기
	@Transactional(rollbackFor = RuntimeException.class)
	public ResumesInsertRespDto insertResumes(ResumesInsertReqDto resumesInsertReqDto) {

		try {
			resumesInsertReqDto.ResumesInsertDtoPictureSet();
		} catch (Exception e) {
			throw new ApiException("멀티파트 폼 에러");
		}

		Category categoryPS = resumesInsertReqDto.ResumesInsertRespDtoToCategoryEntity();
		categoryDao.insert(categoryPS);

		Portfolio portfolioPS = resumesInsertReqDto.ResumesInsertRespDtoToPortfolioEntity();
		portfolioDao.insert(portfolioPS);

		Career careerPS = resumesInsertReqDto.ResumesInsertRespDtoToCareerEntity();
		careerDao.insert(careerPS);

		// if 체크
		if (!(categoryPS.getCategoryId() != null && portfolioPS.getPortfolioId() != null
				&& careerPS.getCareerId() != null)) {
			throw new ApiException("이력서 작성 에러");
		}

		Resumes resumesPS = resumesInsertReqDto.ResumesInsertRespDtoToResumesEntity();
		resumesPS.setCareerId(careerPS.getCareerId());
		resumesPS.setPortfolioId(portfolioPS.getPortfolioId());
		resumesPS.setResumesCategoryId(categoryPS.getCategoryId());
		resumesDao.insert(resumesPS);

		ResumesInsertRespDto resumesInsertRespDto = new ResumesInsertRespDto(resumesPS, categoryPS, careerPS,
				portfolioPS);

		return resumesInsertRespDto;

	}

	// 내가 작성한 이력서 목록 보기
	public List<ResumesAllRespDto> findAllMyResumes(ResumesAllRespDto resumesAllRespDto) {
		List<Resumes> resumesList = resumesDao.findAllMyResumes(resumesAllRespDto.getPersonalId());
		List<ResumesAllRespDto> resumesAllRespDtoList = new ArrayList<>();
		for (Resumes resumes : resumesList) {
			resumesAllRespDtoList.add(new ResumesAllRespDto(resumes));
		}
		return resumesAllRespDtoList;
	}

	// 이력서 상세 보기
	@Transactional(readOnly = true)
	public ResumesDetailRespDto findByResumesId(Integer resumesId) {
		ResumesDetailRespDto resumesDetailRespDto = resumesDao.findByResumesId(resumesId);
		return resumesDetailRespDto;
	}

	// 이력서 수정 하기
	@Transactional(rollbackFor = RuntimeException.class)
	public void updateResumes(Integer resumesId, ResumesUpdateDto updateResumesDto) {

		Resumes resumes = new Resumes(resumesId, updateResumesDto);
		resumesDao.update(resumes);

		Category category = new Category(updateResumesDto.getCategoryId(), updateResumesDto);
		categoryDao.update(category);

		Portfolio portfolio = new Portfolio(updateResumesDto.getPortfolioId(), updateResumesDto);
		portfolioDao.update(portfolio);

		Career career = new Career(updateResumesDto.getCareerId(), updateResumesDto);
		careerDao.update(career);

	}

	// 이력서 삭제
	public void deleteResumes(@PathVariable Integer resumesId) {
		resumesDao.deleteById(resumesId);
	}

	// 전체 이력서 목록 보기
	public List<CompanyMainDto> resumesAll(Integer startNum) {
		return resumesDao.findAll(startNum);
	}

	// 페이징
	public PagingDto resumesPaging(Integer page, String keyword) {
		return resumesDao.resumesPaging(page, keyword);
	}

	// 검색 결과 목록 보기
	public List<CompanyMainDto> findSearch(Integer startNum, String keyword) {
		return resumesDao.findSearch(startNum, keyword);
	}

	// 카테고리 별 리스트 보기
	public List<CompanyMainDto> findCategory(int startNum, Integer id) {
		return resumesDao.findCategory(startNum, id);
	}

	// 카테고리 별 검색 결과 리스트
	public List<CompanyMainDto> findCategorySearch(int startNum, String keyword, Integer id) {
		return resumesDao.findCategorySearch(startNum, keyword, id);
	}

	// 개인 정보에 보기
	@Transactional(readOnly = true)
	public PersonalDetailRespDto findByPersonal(Integer personalId) {
		PersonalDetailRespDto personalDetailRespDto = personalDao.personalformById(personalId);
		return personalDetailRespDto;
	}

	// 내 정보 수정에서 데이터 보여주기
	// @Transactional(readOnly = true)
	// public PersonalFormRespDto personalUpdateById(Integer personalId) {
	// PersonalFormRespDto personalDetailRespDto =
	// personalDao.personalformById(personalId);
	// return personalDao.personalUpdateById(personalId);
	// }

	public PersonalAddressDto personalAddress(Integer personalId) {
		return personalDao.personalAddressById(personalId);
	}

	// 내 정보 수정
	@Transactional(rollbackFor = Exception.class)
	public void updatePersonal(Integer userId, Integer personalId, PersonalUpdateDto personalUpdateDto) {
		Users personaluserPS = userDao.findById(userId);
		personaluserPS.update(personalUpdateDto);
		userDao.update(personaluserPS);

		Personal personalPS = personalDao.findById(personalId);
		personalPS.updatePersonal(personalUpdateDto);
		personalDao.update(personalPS);

	}
}
