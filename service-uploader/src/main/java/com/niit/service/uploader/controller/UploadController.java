package com.niit.service.uploader.controller;


import com.niit.service.uploader.Attachement;
import com.niit.service.uploader.ResultData;
import com.niit.service.uploader.UploaderProperties;
import com.niit.service.uploader.path.FileSavePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.UUID;
import java.util.List;


@RestController
public class UploadController {
    @Autowired
    private UploaderProperties properties;

    @Autowired
    private FileSavePath fileSavePath;

    @PostMapping("/upload/{fileType}")
    public ResultData upload(MultipartFile[] file, @PathVariable String fileType) {
        try {
            checkParams(file, fileType);
            List<Attachement> attachements = new ArrayList<>();
            String fullPath = fileSavePath.fullPath(fileType);
            String relativePath = fileSavePath.relativePath();
            checkPath(fullPath);
            for (MultipartFile mf : file) {
                Attachement attachement = new Attachement();
                String newName = saveFile(fullPath, mf);
                System.out.println(newName);
                String[] split = newName.split("\\.");
                String name = split[0];
                String suffix  = split[split.length-1];
                String[] split1 = name.split("/");
                String name1  = split1[split1.length-1];
                attachement.setAttachementName(name1);
                attachement.setAttachementSuffix(suffix);
                long size1 = mf.getSize();
                Double size =(double)(size1/1024);
                attachement.setAttachementSize(size);
                attachement.setAttachmentUrl(relativePath+newName);
                attachements.add(attachement);


            }
              System.out.println(attachements);
               return ResultData.success(attachements);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.failure(e.getMessage());
        }


    }

    private void checkParams(MultipartFile[] file, String fileType) {
        if (file == null || file.length == 0) throw new RuntimeException("未选择上传文件");
        if (fileType == "file" || fileType =="image" || fileType =="attachment") throw new RuntimeException("文件类型参数错误");
    }

    private String saveFile(String fullPath, MultipartFile mf) throws IOException {
        String filename = mf.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String newName = UUID.randomUUID() + suffix;
        File dest = new File(fullPath + "/" + newName);
        mf.transferTo(dest);
        return newName;
    }

    private void checkPath(String pathStr) {
        File path = new File(pathStr);
        if (!path.exists()) {
            path.mkdirs();
        }
    }
}
