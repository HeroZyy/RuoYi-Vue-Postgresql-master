package com.ruoyi.system.service.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service

public class B2bDownloadService {
    public ResponseEntity<Resource> downloadFile(String addr , String name) throws IOException {
        // 读取文件
        Resource resource = new ClassPathResource(addr);
        String fileName = "filename="+name+".xlsx";

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; " + fileName);

        // 创建一个ResponseEntity对象，并设置响应内容、响应头和状态码
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
