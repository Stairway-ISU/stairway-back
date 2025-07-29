package com.start.modules.qna.controller;

import com.start.modules.qna.model.QnaRequestDto;
import com.start.modules.qna.service.QnaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/qna")
@RequiredArgsConstructor
public class QnaPostController {
    private final QnaService qnaService;

    public QnaPostController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> post(@RequestBody @Valid QnaRequestDto qnaDto) {
        qnaService.write(qnaDto);
        return ResponseEntity.ok().build();
    }
}
