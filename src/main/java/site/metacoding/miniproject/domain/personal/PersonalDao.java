package site.metacoding.miniproject.domain.personal;

import java.util.List;

import site.metacoding.miniproject.dto.personal.PersonalRespDto.PersonalAddressRespDto;
import site.metacoding.miniproject.dto.personal.PersonalRespDto.PersonalUpdateFormRespDto;

public interface PersonalDao {
	public void insert(Personal personal);

	public List<Personal> findAll();

	public Personal findById(Integer personalId);

	public void deleteById(Integer id);

	// 개인정보 나의 정보페이지에 불러오기
	public Personal personaldetailById(Integer personalId);

	// 회원가입수정때 보여줄 개인정보
	public PersonalUpdateFormRespDto personalUpdateById(Integer personalId);

	// 회원가입 수정
	public void update(Personal personal);
	// public void updateById(PersonalUpdateDto personalUpdateDto);

	// 주소
	public PersonalAddressRespDto personalAddressById(Integer personalId);

	// 이력서 작성 폼에 개인정보 불러오기

}
