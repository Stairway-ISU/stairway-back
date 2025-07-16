package com.start.modules.qna.controller;

import com.start.modules.qna.entity.QnaEntity;
import com.start.modules.qna.service.QnaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QnaPostController {
    private final QnaService qnaService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid QnaEntity qnaEntity) {
        qnaService.write(qnaEntity);
    }
}
