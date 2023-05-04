package com.fjyt.asset.management.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author keQiLong
 * @date 2023年04月28日 16:30
 * 文件上传控制器
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public void upload(MultipartFile file,String assetCode){
        System.out.println(assetCode);
    }
}
