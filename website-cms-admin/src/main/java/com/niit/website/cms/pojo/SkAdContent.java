package com.niit.website.cms.pojo;

public class SkAdContent {
    private Integer id;

    private String adUrl;

    private String adHref;

    private Integer adOrder;

    private Integer adStatus;

    private Integer adId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl == null ? null : adUrl.trim();
    }

    public String getAdHref() {
        return adHref;
    }

    public void setAdHref(String adHref) {
        this.adHref = adHref == null ? null : adHref.trim();
    }

    public Integer getAdOrder() {
        return adOrder;
    }

    public void setAdOrder(Integer adOrder) {
        this.adOrder = adOrder;
    }

    public Integer getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(Integer adStatus) {
        this.adStatus = adStatus;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }
}