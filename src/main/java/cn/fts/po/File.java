package cn.fts.po;

import java.util.Date;

public class File {
    private String fileid;

    private String name;

    private Date start;

    private Integer keep;

    private Integer access;

    private String authoricode;

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

    @Override
    public String toString() {
        return "File{" +
                "fileid='" + fileid + '\'' +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", keep=" + keep +
                ", access=" + access +
                ", authoricode='" + authoricode + '\'' +
                '}';
    }
}