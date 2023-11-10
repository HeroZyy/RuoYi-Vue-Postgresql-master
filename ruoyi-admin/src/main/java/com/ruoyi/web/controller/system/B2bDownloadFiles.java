package com.ruoyi.web.controller.system;

import com.ruoyi.system.service.impl.B2bDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/system/down")
public class B2bDownloadFiles {
    @Autowired
    B2bDownloadService downloadService;
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(String addr , String fileName) throws IOException {
        // 读取文件
        ResponseEntity<Resource> resourceResponseEntity = downloadService.downloadFile(addr, fileName);

        // 创建一个ResponseEntity对象，并设置响应内容、响应头和状态码
        return resourceResponseEntity;
    }
}
