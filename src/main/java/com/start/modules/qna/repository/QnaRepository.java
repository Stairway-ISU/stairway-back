package com.start.modules.qna.repository;


import com.start.modules.qna.entity.QnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnaEntity, Long> {

}
