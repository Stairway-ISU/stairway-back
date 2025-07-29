package com.start.modules.qna.repository;

import com.start.modules.qna.entity.QnaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaFileRepository extends JpaRepository<QnaFile, Long> {}
