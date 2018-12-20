package com.niit.service.uploader;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xin.uploader")
public class UploaderProperties {

    private String commonFilePath;
    private String imagePath;
    private String attachmentPath;
    private String childPathStrategy = "month";

    public String getCommonFilePath() {
        return commonFilePath;
    }

    public void setCommonFilePath(String commonFilePath) {
        this.commonFilePath = commonFilePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getChildPathStrategy() {
        return childPathStrategy;
    }

    public void setChildPathStrategy(String childPathStrategy) {
        this.childPathStrategy = childPathStrategy;
    }
}
