package wechat.app.dao.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private String openId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "gender")
    private int gender;

    @Column(name = "country")
    private String country;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "avatarUrl")
    private String avatarUrl;

    @Column(name = "phone")
    private String phone;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCreateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    //    @Override
//    public int hashCode() {
//        int result = 17;
//        result = 31 * result + this.userName.hashCode();
//        result = 31 * result + this.province.hashCode();
//        result = 31 * result + this.phone.hashCode();
//        result = 31 * result + this.openId.hashCode();
//        result = 31 * result + this.gender;
//        result = 31 * result + this.avatarUrl.hashCode();
//        result = 31 * result + this.city.hashCode();
//        result = 31 * result + this.country.hashCode();
//        return result;
//    }

    /**
     * 重写hashCode,此方法和上面注释方法效果一致
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.userName,
                this.province,
                this.phone,
                this.openId,
                this.gender,
                this.avatarUrl,
                this.city,
                this.country);
    }

    /**
     * 重写equals,判断是否为同一个客户或者客户信息有更新.
     * 不将id和session_id纳入判断指标,session_id可能每次不一样
     *
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (obj instanceof UserInfo) {
            UserInfo u = (UserInfo) obj;
            return u.avatarUrl.equals(this.avatarUrl) &&
                    u.city.equals(this.city) &&
                    u.country.equals(this.country) &&
                    u.gender == this.gender &&
                    u.openId.equals(this.openId) &&
                    u.phone.equals(this.phone) &&
                    u.province.equals(this.province) &&
                    u.userName.equals(this.userName);
        }
        return false;
    }
}
