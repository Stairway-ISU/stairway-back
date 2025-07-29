package com.start.modules.qna.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "qna_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("댓글 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_id", nullable = false)
    @Comment("문의글 ID")
    private Qna qna;

    @Column(name = "writer_type", length = 20, nullable = false)
    @Comment("작성자 유형(admin, customer)")
    private String writerType;

    @Column(name = "writer_id", length = 100, nullable = false)
    @Comment("작성자 ID")
    private String writerId;

    @Column(name = "contents", nullable = false)
    @Comment("댓글 내용")
    private String contents;

    @Column(name = "is_deleted", nullable = false)
    @Comment("삭제 여부")
    private Boolean isDeleted = false;

    @Column(name = "created_at", nullable = false)
    @Comment("작성 일시")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Comment("수정 일시")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
