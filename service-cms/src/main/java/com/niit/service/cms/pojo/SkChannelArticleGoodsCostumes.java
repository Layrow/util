package com.niit.service.cms.pojo;

public class SkChannelArticleGoodsCostumes {
    private Integer id;

    private Integer goodId;

    private String md5;

    private String type;

    private String info;

    private String costumename;

    private Integer baselayerid;

    private String baselayermd5;

    private Integer bitmapresolution;

    private Integer rotationcenterx;

    private Integer rotationcentery;

    private Integer currentcostumeindex;

    private Integer scratchx;

    private Integer scratchy;

    private Integer scale;

    private Integer direction;

    private String rotationstyle;

    private Boolean isdraggable;

    private Boolean visible;

    private String spriteinfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getCostumename() {
        return costumename;
    }

    public void setCostumename(String costumename) {
        this.costumename = costumename == null ? null : costumename.trim();
    }

    public Integer getBaselayerid() {
        return baselayerid;
    }

    public void setBaselayerid(Integer baselayerid) {
        this.baselayerid = baselayerid;
    }

    public String getBaselayermd5() {
        return baselayermd5;
    }

    public void setBaselayermd5(String baselayermd5) {
        this.baselayermd5 = baselayermd5 == null ? null : baselayermd5.trim();
    }

    public Integer getBitmapresolution() {
        return bitmapresolution;
    }

    public void setBitmapresolution(Integer bitmapresolution) {
        this.bitmapresolution = bitmapresolution;
    }

    public Integer getRotationcenterx() {
        return rotationcenterx;
    }

    public void setRotationcenterx(Integer rotationcenterx) {
        this.rotationcenterx = rotationcenterx;
    }

    public Integer getRotationcentery() {
        return rotationcentery;
    }

    public void setRotationcentery(Integer rotationcentery) {
        this.rotationcentery = rotationcentery;
    }

    public Integer getCurrentcostumeindex() {
        return currentcostumeindex;
    }

    public void setCurrentcostumeindex(Integer currentcostumeindex) {
        this.currentcostumeindex = currentcostumeindex;
    }

    public Integer getScratchx() {
        return scratchx;
    }

    public void setScratchx(Integer scratchx) {
        this.scratchx = scratchx;
    }

    public Integer getScratchy() {
        return scratchy;
    }

    public void setScratchy(Integer scratchy) {
        this.scratchy = scratchy;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getRotationstyle() {
        return rotationstyle;
    }

    public void setRotationstyle(String rotationstyle) {
        this.rotationstyle = rotationstyle == null ? null : rotationstyle.trim();
    }

    public Boolean getIsdraggable() {
        return isdraggable;
    }

    public void setIsdraggable(Boolean isdraggable) {
        this.isdraggable = isdraggable;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getSpriteinfo() {
        return spriteinfo;
    }

    public void setSpriteinfo(String spriteinfo) {
        this.spriteinfo = spriteinfo == null ? null : spriteinfo.trim();
    }
}