package com.yyc.controller;

import ch.qos.logback.core.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 文件上传下载相关接口
 */

@RestController
@RequestMapping("/files")
public class fileController {

    @Value("${files.upload.path}")
    private  String uploadParentPath;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        //获取文件后缀
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(suffixName);
        //判断是否存在，不存在则创建新的文件目录
        File uploadParentFile = new File(uploadParentPath);
        if (!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }
        System.out.println("2");
        //定义唯一文件名
        String uuid = System.currentTimeMillis() + UUID.randomUUID().toString() + suffixName;

        File uploadFile = new File(uploadParentPath + uuid);
        System.out.println(uploadFile);
        //把后去到文件存储到本地
        file.transferTo(uploadFile);
        String fileUrl = "http:localhost:81/files/" + uuid;
        return fileUrl;
    }

    @GetMapping("/{uuid}")
    public void download(@PathVariable String uuid, HttpServletResponse response) throws IOException {
        //根据唯一文件名获取文件
        File uploadFile = new File(uploadParentPath + uuid);
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(uuid, "UTF-8"));
        response.setContentType("application/octet-stream");

        //读取上传字节流
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        fis = new FileInputStream(uploadFile);
        bis = new BufferedInputStream(fis);
        int i = bis.read(buffer);
        while (i != -1){
            os.write(buffer,0,i);
            i = bis.read(buffer);
        }
        os.flush();
        os.close();
    }
}
