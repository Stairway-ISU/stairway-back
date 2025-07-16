package com.start.modules.qna.service;

import com.start.modules.qna.controller.QnaPostController;
import com.start.modules.qna.entity.QnaEntity;
import com.start.modules.qna.repository.QnaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;

    public void write(@Valid QnaEntity qnaDto){
        QnaEntity qnaEntity = QnaEntity.builder()
                .title(qnaDto.getTitle())
                .content(qnaDto.getContent())
                .build();
        qnaRepository.save(qnaEntity);
    }
}
