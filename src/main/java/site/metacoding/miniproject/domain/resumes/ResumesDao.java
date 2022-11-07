package site.metacoding.miniproject.domain.resumes;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.miniproject.dto.resumes.ResumesRespDto.PagingDto;
import site.metacoding.miniproject.dto.resumes.ResumesRespDto.ResumesDetailRespDto;

public interface ResumesDao {

	// 이력서 작성 하기
	public void insert(Resumes resumes);

	// 내가 작성한 이력서 목록 보기
	public List<Resumes> findAllMyResumes(Integer personalId);

	// 이력서 상세 보기
	public ResumesDetailRespDto findByResumesId(Integer resumesId);

	// 이력서 수정
	public Resumes findById(Integer resumesId);

	public void update(Resumes resumes);

	// 이력서 삭제
	public void deleteById(Integer resumesId);

	// // 전체 이력서 목록 보기
	// public List<Resumes> findAllResumes(int startNum);

	// public List<Resumes> findSearch(@Param("startNum") int startNum,
	// @Param("keyword") String keyword);

	// 페이징
	public PagingDto resumesPaging(@Param("page") Integer page, @Param("keyword") String keyword);

	// 검색 결과 목록 보기
	// public List<CompanyMainDto> findSearch(@Param("startNum") int startNum,
	// @Param("keyword") String keyword);

	// 전체 이력서 목록 보기(카테고리 별)
	public List<Resumes> findCategory(@Param("startNum") Integer startNum, @Param("id") Integer id);

	public List<Resumes> findCategorySearch(@Param("startNum") int startNum, @Param("keyword") String keyword,
			@Param("id") Integer id);

}
