package com.cousin.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件上传类
 *
 * @author cousin
 * @created 2016/11/29 21:14
 */
@Controller
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);


    @RequestMapping("/file")
    public String file() {
        return "/file";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpLoad(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(multipartFile.getOriginalFilename())));
            bufferedOutputStream.write(multipartFile.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return "上传成功~";
        } else {
            return "上传失败！文件为空";
        }
    }


}
