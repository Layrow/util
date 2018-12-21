package com.niit.service.uploader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @Auther: huangwei
 * @Date:2018/12/20 9:53
 * @Description:
 */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Attachement  implements Serializable {
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
