package cn.fts.po;

import java.util.Date;

public class Action {
    private Long id;

    private String fileid;

    private String name;

    private String description;

    private String bywho;

    private String kind;

    private Date createtime;

    public Action(String fileid, String name, String description, String bywho, String kind, Date createtime) {
        this.fileid = fileid;
        this.name = name;
        this.description = description;
        this.bywho = bywho;
        this.kind = kind;
        this.createtime = createtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBywho() {
        return bywho;
    }

    public void setBywho(String bywho) {
        this.bywho = bywho == null ? null : bywho.trim();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}