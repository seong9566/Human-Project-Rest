package site.metacoding.miniproject.utill;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ObjectUtils;

import site.metacoding.miniproject.config.handler.exception.NormalException;
import site.metacoding.miniproject.config.handler.exception.ValCheckException;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumesInsertDto;
import site.metacoding.miniproject.dto.resume.ResumeReqDto.ResumesUpdateDto;

public class ResumesValidationCheck {

	public static void valCheckToInsertResumes(ResumesInsertDto insertResumesDto) {

		if (insertResumesDto == null)
			throw new NormalException("잘못된 요청입니다.");

		// 검증 오류 결과 보관
		Map<String, String> errors = new HashMap<>();

		// 검증 로직
		if (ObjectUtils.isEmpty(insertResumesDto.getResumesTitle())) {
			errors.put("title", "이력서 제목을 입력해주세요.");
		}
		if (ObjectUtils.isEmpty(insertResumesDto.getResumesPlace())) {
			errors.put("place", "희망 근무 지역을 입력해주세요.");
		}
		if (ObjectUtils.isEmpty(insertResumesDto.getResumesIntroduce())) {
			errors.put("introduce", "자기소개 글을 입력해주세요.");
		}

		if (!errors.isEmpty()) {
			for (String key : errors.keySet()) {
				throw new ValCheckException(errors.get(key));
			}
		}
	}

	public static void valCheckToUpdateResumes(ResumesUpdateDto updateResumesDto) {

		if (updateResumesDto == null)
			throw new NormalException("잘못된 요청입니다.");

		// 검증 오류 결과 보관
		Map<String, String> errors = new HashMap<>();

		if (ObjectUtils.isEmpty(updateResumesDto.getResumesTitle())) {
			errors.put("updatetitle", "수정할 이력서 제목을 입력해주세요.");
		}
		if (ObjectUtils.isEmpty(updateResumesDto.getResumesIntroduce())) {
			errors.put("updatetitle", "수정할 이력서 자기소개 글을 입력해주세요.");
		}
		if (ObjectUtils.isEmpty(updateResumesDto.getCategoryFrontend()) &&
				ObjectUtils.isEmpty(updateResumesDto.getCategoryBackend()) &&
				ObjectUtils.isEmpty(updateResumesDto.getCategoryDevops())) {
			errors.put("category", "관심분야를 하나 이상 선택해주세요.");
		}

		if (!errors.isEmpty()) {
			for (String key : errors.keySet()) {
				throw new ValCheckException(errors.get(key));
			}
		}

	}

}
