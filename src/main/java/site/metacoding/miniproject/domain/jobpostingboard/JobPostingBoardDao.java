package site.metacoding.miniproject.domain.jobpostingboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingBoardAllRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingBoardDetailRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.JobPostingDetailWithPersonalRespDto;
import site.metacoding.miniproject.dto.jobpostingboard.JobPostingBoardRespDto.PagingDto;

public interface JobPostingBoardDao {
	public void insert(JobPostingBoard jobPostingBoard);

	public JobPostingBoard findById(Integer jobPostingBoardId);

	public void update(JobPostingBoard jobPostingBoard);

	public void deleteById(Integer jobPostingBoardId);

	// 회사가 작성한 채용공고 목록 보기
	public List<JobPostingBoardAllRespDto> jobPostingBoardList(Integer jobPostingBoardId);

	// 채용공고 상세보기
	public JobPostingBoardDetailRespDto findByJobPostingBoard(Integer jobPostingBoardId);

	public JobPostingDetailWithPersonalRespDto findByJobPostingBoardToPer(Integer jobPostingBoardId);

	// 채용공고 리스트
	// public List<PersonalMainDto> findAll(int startNum);

	// 페이징
	public PagingDto jobPostingBoardPaging(@Param("page") Integer page, @Param("keyword") String keyword);

	// 검색 결과 리스트
	// public List<PersonalMainDto> findSearch(@Param("startNum") int startNum,
	// @Param("keyword") String keyword);

	// 전체 채용공고 목록 보기 (페이징+검색+카테고리id별)
	public List<JobPostingBoard> findCategory(@Param("startNum") Integer startNum, @Param("id") Integer id);

	public List<JobPostingBoard> findCategorySearch(@Param("startNum") int startNum, @Param("keyword") String keyword,
			@Param("id") Integer id);
}
