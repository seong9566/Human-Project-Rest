package site.metacoding.miniproject.service.personal;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
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
import site.metacoding.miniproject.dto.resumes.ResumesRespDto.ResumesInsertRespDto;
import site.metacoding.miniproject.web.dto.request.personal.PersonalUpdateDto;
import site.metacoding.miniproject.web.dto.request.resume.ResumesUpdateDto;
import site.metacoding.miniproject.web.dto.response.company.CompanyMainDto;
import site.metacoding.miniproject.web.dto.response.etc.PagingDto;
import site.metacoding.miniproject.web.dto.response.personal.PersonalAddressDto;
import site.metacoding.miniproject.web.dto.response.resume.ResumesDetailDto;

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

		Category category = new Category(resumesInsertReqDto);
		Category categoryPS = categoryDao.insert(category);

		Portfolio portfolio = new Portfolio(resumesInsertReqDto);
		Portfolio portfolioPS = portfolioDao.insert(portfolio);

		Career career = new Career(resumesInsertReqDto);
		Career careerPS = careerDao.insert(career);

		Resumes resumes = new Resumes(resumesInsertReqDto);
		resumes.setPersonalId(personalId);
		resumes.setCareerId(career.getCareerId());
		resumes.setPortfolioId(portfolio.getPortfolioId());
		resumes.setResumesCategoryId(category.getCategoryId());
		Resumes resumesPS = resumesDao.insert(resumes);

		ResumesInsertRespDto resumesInsertRespDto = new ResumesInsertRespDto();
		resumesInsertRespDto.get

		return resumesInsertRespDto;

	}

	// public PersonalInfoDto personalInfoById(Integer personalId) {
	// return personalDao.personalInfoById(personalId);
	// }

	// 사진
	// @RequestPart("file") MultipartFile file,
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
	// System.out.println("사진 업로드 됨");
	// }
	// resumesInsertDto.setResumesPicture(imgName);

	// 내가 작성한 이력서 목록 보기
	public List<Resumes> myresumesAll(Integer personalId) {
		return resumesDao.findMyresumesAll(personalId);
	}

	// 이력서 상세 보기
	public ResumesDetailDto resumesById(Integer resumesId) {
		return resumesDao.resumesById(resumesId);
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
		// Personal personal = new Personal();
		// personal.setPersonalName("ssar");
		// personal.setPersonalPhoneNumber("010-9459-5116");
		// personal.setPersonalEmail("cndtjq1248@naver.com");
		// personal.setPersonalAddress("asdfasdfa");
		return personalDetailRespDto;
	}

	// 내 정보 수정에서 데이터 보여주기
	public PersonalUpdateDto personalUpdateById(Integer personalId) {
		return personalDao.personalUpdateById(personalId);
	}

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
