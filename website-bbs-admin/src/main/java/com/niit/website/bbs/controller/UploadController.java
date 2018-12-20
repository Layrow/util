package com.niit.website.bbs.controller;




import com.niit.service.uploader.ResultData;
import com.niit.website.bbs.service.SkBbsTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




@RestController
public class UploadController {
    @Autowired
    private SkBbsTopicService skBbsTopicService;

    @PostMapping("/upload/{fileType}")
    public ResultData upload(MultipartFile[] file, @PathVariable String fileType) {
        ResultData uploader = skBbsTopicService.uploader(file, fileType);
        return uploader;


    }
}
