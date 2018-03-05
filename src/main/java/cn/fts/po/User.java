package cn.fts.po;

public class User {
    private String account;

    private String password;

    private String nickname;

    private Double max;

    private Double used;

    private String defaultcode;

    private Integer locked;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getUsed() {
        return used;
    }

    public void setUsed(Double used) {
        this.used = used;
    }

    public String getDefaultcode() {
        return defaultcode;
    }

    public void setDefaultcode(String defaultcode) {
        this.defaultcode = defaultcode == null ? null : defaultcode.trim();
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", max=" + max +
                ", used=" + used +
                ", defaultcode='" + defaultcode + '\'' +
                ", locked=" + locked +
                '}';
    }
}