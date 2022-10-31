package site.metacoding.miniproject.domain.like.personalike;

import java.util.List;

import site.metacoding.miniproject.dto.personal.PersonalReqDto.PersonalLikeDto;

public interface PersonalLikesDao {
	public void insert(PersonalLike personalLike);

	public List<PersonalLikeDto> findAll(Integer companyId);

	public PersonalLike findById(PersonalLike personalLike);

	public void update(PersonalLike personalLike);

	public void deleteById(PersonalLike personalLike);
}
