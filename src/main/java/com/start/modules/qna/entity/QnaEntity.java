package com.start.modules.qna.entity;

import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자, 무분별한 객체 생성 방지
public class QnaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long id;

    public String title;

    @Lob //데이터 양 : 4기가
    public String content;

    @Builder
    public QnaEntity(String title, String content){
        this.title = title;
        this.content = content;
    }
}
