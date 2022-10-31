package site.metacoding.miniproject.domain.like.companylike;

import java.util.List;

import site.metacoding.miniproject.dto.company.CompanyReqDto.CompanyLikeDto;

public interface CompanyLikesDao {
	public void insert(CompanyLike companyLike);

	public List<CompanyLikeDto> findAll();

	public CompanyLike findById(CompanyLike companyLike);

	public void update(CompanyLike companyLike);

	public void deleteById(CompanyLike companyLike);

}
