package com.niit.service.uploader.path.impl;


import com.niit.service.uploader.UploaderProperties;
import com.niit.service.uploader.path.ChildPath;
import com.niit.service.uploader.path.FileSavePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileSavePathImpl implements FileSavePath {
    @Autowired
    private UploaderProperties uploaderProperties;

    @Autowired
    private ChildPath childPath;

    @Override
    public String fullPath(String type) {
        return rootPath(type) + childPath();
    }



    @Override
    public String relativePath() {
        return childPath();
    }

    @Override
    public String Path(String type) {
        return rootPath(type);
    }


    private String childPath() {
        return childPath.getChildPath();
    }

    private String rootPath(String type) {
        switch (type) {
            case "file":
                return uploaderProperties.getCommonFilePath();
            case "image":
                return uploaderProperties.getImagePath();
            case "attachment":
                return uploaderProperties.getAttachmentPath();
            default:
                return "";
        }
    }


}
