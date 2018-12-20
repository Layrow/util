package com.niit.service.uploader;

/**
 * @Auther: huangwei
 * @Date:2018/12/20 9:53
 * @Description:
 */
public class Attachement {
    String attachementName;
    Double   attachementSize;
    String attachementSuffix;
    String attachmentUrl;

    public Double getAttachementSize() {
        return attachementSize;
    }

    public void setAttachementSize(Double attachementSize) {
        this.attachementSize = attachementSize;
    }

    public String getAttachementName() {
        return attachementName;
    }

    public void setAttachementName(String attachementName) {
        this.attachementName = attachementName;
    }



    public String getAttachementSuffix() {
        return attachementSuffix;
    }

    public void setAttachementSuffix(String attachementSuffix) {
        this.attachementSuffix = attachementSuffix;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }
}
