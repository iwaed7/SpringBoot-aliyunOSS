package com.example.testossagain.controller;

/**
 * @Author 钱琦瑛
 * @Date 2019/9/12 17:34
 */

import com.aliyun.oss.model.OSSObjectSummary;
import com.example.testossagain.service.FileUploadService;
import com.example.testossagain.vo.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("file/upload")
    @ResponseBody
    public FileUploadResult upload(@RequestParam("file") MultipartFile uploadFile)
            throws Exception {
        return this.fileUploadService.upload(uploadFile);
    }

    @RequestMapping("file/delete")
    @ResponseBody
    public FileUploadResult delete(@RequestParam("fileName") String objectName) throws Exception {
        return this.fileUploadService.delete(objectName);
    }

    @RequestMapping("file/list")
    @ResponseBody
    public List<OSSObjectSummary> list() throws Exception {
        return this.fileUploadService.list();
    }

    @RequestMapping("file/download")
    @ResponseBody
    public void download(@RequestParam("fileName") String objectName, HttpServletResponse response) throws IOException {
        //通知浏览器以附件形式下载
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(objectName.getBytes(), "ISO-8859-1"));
        this.fileUploadService.exportOssFile(response.getOutputStream(),objectName);
    }
}