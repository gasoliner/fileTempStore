package cn.fts.po;

public class Systemddl {
    private String uuid;

    private String keyword;

    private String ddlvalue;

    private String ddlname;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getDdlvalue() {
        return ddlvalue;
    }

    public void setDdlvalue(String ddlvalue) {
        this.ddlvalue = ddlvalue == null ? null : ddlvalue.trim();
    }

    public String getDdlname() {
        return ddlname;
    }

    public void setDdlname(String ddlname) {
        this.ddlname = ddlname == null ? null : ddlname.trim();
    }
}