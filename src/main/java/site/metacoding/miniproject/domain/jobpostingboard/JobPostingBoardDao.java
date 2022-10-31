package site.metacoding.miniproject.domain.jobpostingboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.miniproject.dto.etc.PagingRespDto.PagingDto;
import site.metacoding.miniproject.dto.jobposting.JobPostingRespDto.JobPostingBoardDetailDto;
import site.metacoding.miniproject.dto.jobposting.JobPostingRespDto.JobPostingBoardListDto;
import site.metacoding.miniproject.dto.personal.PersonalRespDto.PersonalMainDto;

public interface JobPostingBoardDao {
	public void insert(JobPostingBoard jobPostingBoard);

	public JobPostingBoard findById(Integer jobPostingBoardId);

	public void update(JobPostingBoard jobPostingBoard);

	public void deleteById(Integer jobPostingBoardId);

	// 회사가 작성한 채용공고 목록 보기
	public List<JobPostingBoardListDto> jobPostingBoardList(Integer companyId);

	// 채용공고 자세히 보기
	public JobPostingBoardDetailDto findByDetail(Integer jobPostingBoardId);

	// 채용공고 리스트
	public List<PersonalMainDto> findAll(int startNum);

	// 페이징
	public PagingDto jobPostingBoardPaging(@Param("page") Integer page, @Param("keyword") String keyword);

	// 검색 결과 리스트
	public List<PersonalMainDto> findSearch(@Param("startNum") int startNum, @Param("keyword") String keyword);

	// 카테고리별 목록보기
	public List<PersonalMainDto> findCategory(@Param("startNum") Integer startNum, @Param("id") Integer id);

	public List<PersonalMainDto> findCategorySearch(@Param("startNum") int startNum, @Param("keyword") String keyword,
			@Param("id") Integer id);
}
