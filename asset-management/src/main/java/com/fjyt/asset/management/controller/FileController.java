package com.fjyt.asset.management.controller;

import com.fjyt.asset.management.POJO.DO.AssetPicture;
import com.fjyt.asset.management.POJO.R;
import com.fjyt.asset.management.mapper.AssetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author keQiLong
 * @date 2023年04月28日 16:30
 * 文件上传控制器
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${filePath}")
    private String filePath;
    @Autowired
    private AssetMapper assetMapper;

    @PostMapping("/upload")
    @Transactional(rollbackFor = Exception.class)
    public R upload(@RequestParam("file") MultipartFile multipartFile, String assetCode) throws IOException {
        //判断文件目录是否存在
        File fileExists = new File(filePath);
        if (!fileExists.exists()){
            // 不存在创建目录
            fileExists.mkdirs();
        }
        //判断是否存在
        if(assetMapper.getAssetPicturePath(assetCode) != null){
            assetMapper.delAssetPicturePath(assetCode);
        }
        // 获取源文件后缀名
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String path = filePath+assetCode+suffix;
        File file = new File(path);
        //判断文件是否已存在 存在删除文件
        if (file.exists()){
            file.delete();
        }
        multipartFile.transferTo(file);
        AssetPicture assetPicture = new AssetPicture();
        assetPicture.setAssetCode(assetCode);
        assetPicture.setPicturePath(path);
        assetMapper.saveAssetPicturePath(assetPicture);
        return R.ok("上传成功,文件已保存");
    }

    @GetMapping("/{assetCode}")
    public String imageLook (HttpServletResponse response,@PathVariable String assetCode) {
        String path = assetMapper.getAssetPicturePath(assetCode);
        if (path == null){
            return "fail";
        }
        File file = new File(path);
        byte[] bytes = new byte[1024];
        try (OutputStream os = response.getOutputStream();
             FileInputStream fis = new FileInputStream(file)){
            while ((fis.read(bytes)) != -1) {
                os.write(bytes);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
