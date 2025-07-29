package com.start.modules.qna.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaRequestDto {

    private String email;
    private Boolean isPrivate;
    private List<QnaFileDto> files;  // 첨부파일 여러 개

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
