package com.start.modules.qna.controller;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface QnaRestController {
    @AliasFor(annotation = Controller.class)
    String value() default "";
}
