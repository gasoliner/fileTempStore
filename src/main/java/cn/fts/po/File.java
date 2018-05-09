package cn.fts.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class File {
    private String fileid;

    private String name;

    private Integer size;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date start;

    private Integer keep;

    private Integer access;

    private String authoricode;

    private String uploadby;

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid == null ? null : fileid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Integer getKeep() {
        return keep;
    }

    public void setKeep(Integer keep) {
        this.keep = keep;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public String getAuthoricode() {
        return authoricode;
    }

    public void setAuthoricode(String authoricode) {
        this.authoricode = authoricode == null ? null : authoricode.trim();
    }

    public String getUploadby() {
        return uploadby;
    }

    public void setUploadby(String uploadby) {
        this.uploadby = uploadby == null ? null : uploadby.trim();
    }
}