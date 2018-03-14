package com.yatoooon.managementsystem.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yatoooon
 * @since 2018-03-14
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色权限
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 名称
     */
    private String name;
    /**
     * 登录名
     */
    private String loginname;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建者
     */
    @TableField("create_user_id")
    private Integer createUserId;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新者
     */
    @TableField("update_user_id")
    private Integer updateUserId;
    /**
     * 状态
     */
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", name=" + name +
        ", loginname=" + loginname +
        ", password=" + password +
        ", avatar=" + avatar +
        ", createTime=" + createTime +
        ", createUserId=" + createUserId +
        ", updateTime=" + updateTime +
        ", updateUserId=" + updateUserId +
        ", state=" + state +
        "}";
    }
}
