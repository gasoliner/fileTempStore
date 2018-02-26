package cn.fts.po;

import java.util.Date;

public class File {
    private Integer id;

    private String name;

    private Date start;

    private Integer keep;

    private String url;

    private Integer access;

    private String authoricode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", keep=" + keep +
                ", url='" + url + '\'' +
                ", access=" + access +
                ", authoricode='" + authoricode + '\'' +
                '}';
    }
}