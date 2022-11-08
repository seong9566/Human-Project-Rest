package site.metacoding.miniproject.utill;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ObjectUtils;

import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesInsertReqDto;
import site.metacoding.miniproject.dto.resumes.ResumesReqDto.ResumesUpdateReqDto;
import site.metacoding.miniproject.exception.NormalException;
import site.metacoding.miniproject.exception.ValCheckException;

public class ResumesValidationCheck {

	public static void valCheckToInsertResumes(ResumesInsertReqDto resumesInsertReqDto) {

		if (resumesInsertReqDto == null)
			throw new NormalException("잘못된 요청입니다.");

		// 검증 오류 결과 보관
		Map<String, String> errors = new HashMap<>();

		// 검증 로직
		if (ObjectUtils.isEmpty(resumesInsertReqDto.getResumesTitle())) {
			errors.put("title", "이력서 제목을 입력해주세요.");
		}
		if (ObjectUtils.isEmpty(resumesInsertReqDto.getResumesPlace())) {
			errors.put("place", "희망 근무 지역을 입력해주세요.");
		}
		if (ObjectUtils.isEmpty(resumesInsertReqDto.getResumesIntroduce())) {
			errors.put("introduce", "자기소개 글을 입력해주세요.");
		}

		if (!errors.isEmpty()) {
			for (String key : errors.keySet()) {
				throw new ValCheckException(errors.get(key));
			}
		}
	}

	public static void valCheckToUpdateResumes(ResumesUpdateReqDto resumesUpdateReqDto) {

		if (resumesUpdateReqDto == null)
			throw new NormalException("잘못된 요청입니다.");

		// 검증 오류 결과 보관
		Map<String, String> errors = new HashMap<>();

		if (ObjectUtils.isEmpty(resumesUpdateReqDto.getResumesTitle())) {
			errors.put("updatetitle", "수정할 이력서 제목을 입력해주세요.");
		}
		if (ObjectUtils.isEmpty(resumesUpdateReqDto.getResumesIntroduce())) {
			errors.put("updatetitle", "수정할 이력서 자기소개 글을 입력해주세요.");
		}
		if (ObjectUtils.isEmpty(resumesUpdateReqDto.getCategoryFrontend()) &&
				ObjectUtils.isEmpty(resumesUpdateReqDto.getCategoryBackend()) &&
				ObjectUtils.isEmpty(resumesUpdateReqDto.getCategoryDevops())) {
			errors.put("category", "관심분야를 하나 이상 선택해주세요.");
		}

		if (!errors.isEmpty()) {
			for (String key : errors.keySet()) {
				throw new ValCheckException(errors.get(key));
			}
		}

	}

}
