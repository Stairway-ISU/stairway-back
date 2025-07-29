package com.start.modules.qna.model;

import com.start.modules.qna.entity.Qna;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class QnaResponseDto {
    private Long id;
    private String email;
    private String title;
    private String contents;
    private Boolean isPrivate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<QnaFileDto> files;

    public QnaResponseDto(Qna qna) {
        this.id = qna.getId();
        this.email = qna.getEmail();
        this.title = qna.getTitle();
        this.contents = qna.getContents();
        this.isPrivate = qna.getIsPrivate();
        this.status = qna.getStatus();
        this.createdAt = qna.getCreatedAt();
        this.updatedAt = qna.getUpdatedAt();

        // QnaFile -> QnaFileDto 변환
        this.files = qna.getFiles() != null
                ? qna.getFiles().stream().map(QnaFileDto::new).collect(Collectors.toList())
                : List.of();
    }
}
