package com.niit.service.uploader.controller;


import com.niit.service.uploader.Attachement;
import com.niit.service.uploader.ResultData;
import com.niit.service.uploader.UploaderProperties;
import com.niit.service.uploader.path.FileSavePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@CrossOrigin
@RestController
public class UploadController {
    @Autowired
    private UploaderProperties properties;

    @Autowired
    private FileSavePath fileSavePath;
     /**
     * 功能描述:上传文件
     * @author huangwei
     * @date :2018/12/21
     * @params [file, fileType]
     * @return com.niit.service.uploader.ResultData
     */
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
                String originalFilename = mf.getOriginalFilename();
                System.out.println(originalFilename);
                String newName = saveFile(fullPath, mf);
               // String newName = new String(newNames.)
                System.out.println(newName);
                String[] split = newName.split("\\.");
                String name = split[0];
                String suffix  = split[split.length-1];
                String[] split1 = name.split("/");
                attachement.setAttachementName(originalFilename);
                attachement.setAttachementSuffix(suffix);
                long size1 = mf.getSize();
                Double size =(double)(size1/1024);
                attachement.setAttachementSize(size);
                attachement.setAttachmentUrl(relativePath+"/"+newName);
                attachements.add(attachement);

            }
              System.out.println(attachements);
               return ResultData.success(attachements);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.failure(e.getMessage());
        }
    }
    /**
    * 功能描述:删除文件
    * @author huangwei
    * @date :2018/12/21
    * @params [path, fileType]
    * @return java.lang.Boolean
    */
    @DeleteMapping("/deleteUpload/{fileType}")
    public  Boolean deleteFile(@RequestParam  String path,@PathVariable String fileType){
        String s = fileSavePath.Path(fileType);
        String  allPath =s + path;
        System.out.println(allPath);
        File file = new File(allPath);
        boolean b = file.delete();
        return b;
    }
    private void checkParams(MultipartFile[] file, String fileType) {
        if (file == null || file.length == 0) throw new RuntimeException("未选择上传文件");
        if (fileType == "file" || fileType =="image" || fileType =="attachment") throw new RuntimeException("文件类型参数错误");
    }
    private String saveFile(String fullPath, MultipartFile mf) throws IOException {
        String filename = mf.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String newName = filename;
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
