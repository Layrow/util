package com.niit.service.uploader;





import java.io.Serializable;
import java.util.List;

public class ResultData implements Serializable {
    private String status;

    private String reason;
    //private String[] urls;
    private List<Attachement> attachements;/**/

    public List<Attachement> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<Attachement> attachements) {
        this.attachements = attachements;
    }

    private ResultData(String status, String reason, List<Attachement> attachements) {
        this.status = status;
        this.reason = reason;
        this.attachements = attachements;
    }

/*    public static ResultData success(String[] urls){
        return new ResultData("success",null,urls);
    }*/

     public  static  ResultData success(List<Attachement> attachements){
         return  new ResultData("success","",attachements);
     }

    public static ResultData failure(String reason){
        return new ResultData("failure",reason,null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
