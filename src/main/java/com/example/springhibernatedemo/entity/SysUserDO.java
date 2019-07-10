package com.example.springhibernatedemo.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sys_user", schema = "shop", catalog = "")
public class SysUserDO {
    private Long id;
    private String realname;
    private String username;
    private String password;
    private String picId;
    private String phone;
    private String roleId;
    private String sex;
    private String proDesc;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "realname")
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "pic_id")
    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "pro_desc")
    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserDO sysUserDO = (SysUserDO) o;
        return id == sysUserDO.id &&
                Objects.equals(realname, sysUserDO.realname) &&
                Objects.equals(username, sysUserDO.username) &&
                Objects.equals(password, sysUserDO.password) &&
                Objects.equals(picId, sysUserDO.picId) &&
                Objects.equals(phone, sysUserDO.phone) &&
                Objects.equals(roleId, sysUserDO.roleId) &&
                Objects.equals(sex, sysUserDO.sex) &&
                Objects.equals(proDesc, sysUserDO.proDesc) &&
                Objects.equals(createTime, sysUserDO.createTime) &&
                Objects.equals(updateTime, sysUserDO.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, realname, username, password, picId, phone, roleId, sex, proDesc, createTime, updateTime);
    }
}
