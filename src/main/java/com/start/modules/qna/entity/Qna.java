package com.start.modules.qna.entity;

import io.swagger.v3.oas.annotations.info.Contact;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qna")
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("문의 ID")
    private Long id;

    @Column(name = "email", length = 100, nullable = false)
    @Comment("고객 이메일")
    private String email;

    @Column(name = "title", length = 100, nullable = false)
    @Comment("문의 제목")
    private String title;

    @Lob
    @Column(name = "contents", nullable = false)
    @Comment("문의 내용")
    private String contents;

    @Column(name = "is_private", nullable = false)
    @Comment("비공개 여부")
    private Boolean isPrivate = false;

    @Column(name = "status", length = 20, nullable = false)
    @Comment("문의 상태 (waiting, answered, deleted)")
    private String status = "waiting";

    @Column(name = "created_at")
    @Comment("작성 일자")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Comment("수정 일자")
    private LocalDateTime updatedAt;

    @Column(name = "admin_create_dt")
    @Comment("관리자 댓글 최초 등록일")
    private LocalDateTime adminCreateDt;

    @Column(name = "admin_mod_dt")
    @Comment("관리자 댓글 수정일")
    private LocalDateTime adminModDt;

    @Column(name = "admin_delete_dt")
    @Comment("관리자 댓글 삭제일")
    private LocalDateTime adminDeleteDt;

    @Column(name = "cust_create_dt")
    @Comment("고객 댓글 최초 등록일")
    private LocalDateTime custCreateDt;

    @Column(name = "cust_mod_dt")
    @Comment("고객 댓글 수정일")
    private LocalDateTime custModDt;

    @Column(name = "cust_delete_dt")
    @Comment("고객 댓글 삭제일")
    private LocalDateTime custDeleteDt;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QnaComment> comments;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QnaFile> files;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.custCreateDt = this.createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
