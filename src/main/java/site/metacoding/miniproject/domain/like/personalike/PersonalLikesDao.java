package site.metacoding.miniproject.domain.like.personalike;

import java.util.List;

import site.metacoding.miniproject.dto.like.LikeReqDto.PersonalLikeReqDto;

public interface PersonalLikesDao {
	public void insert(PersonalLike personalLike);

	public List<PersonalLikeReqDto> findAll(Integer companyId);

	public PersonalLikeReqDto findById(PersonalLikeReqDto personalLikeReqDto);

	public void update(PersonalLike personalLike);

	public void deleteById(PersonalLike personalLike);
}
