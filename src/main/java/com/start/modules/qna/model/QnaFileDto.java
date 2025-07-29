package com.start.modules.qna.model;

import com.start.modules.qna.entity.QnaFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaFileDto {
    private String fileName;
    private String filePath;
}
