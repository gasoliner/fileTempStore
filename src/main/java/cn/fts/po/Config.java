package cn.fts.po;

public class Config {
    private Integer id;

    private String keyword;

    private String valuee;

    private Integer yn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getValuee() {
        return valuee;
    }

    public void setValuee(String valuee) {
        this.valuee = valuee == null ? null : valuee.trim();
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}