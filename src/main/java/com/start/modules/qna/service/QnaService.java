package com.start.modules.qna.service;

import com.start.modules.qna.entity.Qna;
import com.start.modules.qna.entity.QnaFile;
import com.start.modules.qna.model.QnaRequestDto;

import com.start.modules.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    public void write(QnaRequestDto dto) {
        Qna qna = Qna.builder()
                .email(dto.getEmail())
                .title(dto.getTitle())
                .contents(dto.getContents())
                .isPrivate(dto.getIsPrivate() != null ? dto.getIsPrivate() : false)
                .status("waiting")
                .build();

        // 파일 리스트가 있을 경우 QnaFile 리스트 생성
        if (dto.getFiles() != null && !dto.getFiles().isEmpty()) {
            List<QnaFile> files = dto.getFiles().stream()
                    .map(fileDto -> QnaFile.builder()
                            .fileName(fileDto.getFileName())
                            .filePath(fileDto.getFilePath())
                            .qna(qna)
                            .build())
                    .collect(Collectors.toList());

            qna.setFiles(files);
        }

        qnaRepository.save(qna);
    }
}
