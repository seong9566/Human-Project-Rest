package site.metacoding.miniproject.domain.like.companylike;

import java.util.List;

import site.metacoding.miniproject.dto.like.LikeReqDto.CompanyLikeReqDto;
import site.metacoding.miniproject.dto.like.LikeRespDto.CompanyLikeRespDto;

public interface CompanyLikesDao {
	public CompanyLikeRespDto insert(CompanyLike companyLike);

	public List<CompanyLikeReqDto> findAll();

	public CompanyLike findById(CompanyLikeReqDto companyLikeReqDto);

	public void update(CompanyLike companyLike);

	public void deleteById(CompanyLike companyLike);

}
