package site.metacoding.miniproject.domain.like.companylike;

import java.util.List;

import site.metacoding.miniproject.dto.like.LikeReqDto.CompanyLikeReqDto;

public interface CompanyLikesDao {
	public void insert(CompanyLike companyLike);

	public List<CompanyLikeReqDto> findAll();

	public CompanyLike findById(CompanyLikeReqDto companyLikeReqDto);

	public void update(CompanyLike companyLike);

	public void deleteById(CompanyLike companyLike);

}
