package com.start.modules.qna.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import java.time.LocalDateTime;

@Entity
@Table(name = "qna_file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("파일 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_id", nullable = false)
    @Comment("문의글 ID")
    private Qna qna;

    @Column(name = "file_name", length = 255, nullable = false)
    @Comment("원본 파일명")
    private String fileName;

    @Column(name = "file_path", length = 500, nullable = false)
    @Comment("서버 저장 경로")
    private String filePath;

    @Column(name = "uploaded_at")
    @Comment("업로드 일시")
    private LocalDateTime uploadedAt;

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }
}
