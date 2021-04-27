package com.fh.entity.huoxing;

import java.io.Serializable;
import java.util.Date;

public class TbAppVersion implements Serializable {
    private Integer id;
    private String appname;
    private String version;
    private String vercode;
    private Date maketime;
    private Date modifytime;
    private String downurl;
    private String description;
    private String filepath;
    private String filename;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String r5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public Date getMaketime() {
        return maketime;
    }

    public void setMaketime(Date maketime) {
        this.maketime = maketime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getR4() {
        return r4;
    }

    public void setR4(String r4) {
        this.r4 = r4;
    }

    public String getR5() {
        return r5;
    }

    public void setR5(String r5) {
        this.r5 = r5;
    }

    @Override
    public String toString() {
        return "TbAppVersion{" +
                "id=" + id +
                ", appname='" + appname + '\'' +
                ", version='" + version + '\'' +
                ", vercode='" + vercode + '\'' +
                ", maketime=" + maketime +
                ", modifytime=" + modifytime +
                ", downurl='" + downurl + '\'' +
                ", description='" + description + '\'' +
                ", filepath='" + filepath + '\'' +
                ", filename='" + filename + '\'' +
                ", r1='" + r1 + '\'' +
                ", r2='" + r2 + '\'' +
                ", r3='" + r3 + '\'' +
                ", r4='" + r4 + '\'' +
                ", r5='" + r5 + '\'' +
                '}';
    }
}
