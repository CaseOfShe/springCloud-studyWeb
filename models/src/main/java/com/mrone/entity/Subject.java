package com.mrone.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @TableName subject
 */
@TableName(value ="subject")
public class Subject implements Serializable {
    /**
     * 题库id
     */
    private Integer id;

    /**
     * 发布人的uid连接openid
     */
    private Integer uid;

    /**
     * 标签选择
     */
    private String tag;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 题库类型（单选，简答题）
     */
    private String type;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 具体是那一篇内容
     */
    private String subject;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 题库id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 题库id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 发布人的uid连接openid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 发布人的uid连接openid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 标签选择
     */
    public String getTag() {
        return tag;
    }

    /**
     * 标签选择
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 题库类型（单选，简答题）
     */
    public String getType() {
        return type;
    }

    /**
     * 题库类型（单选，简答题）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 题目标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 题目标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 具体是那一篇内容
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 具体是那一篇内容
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Subject other = (Subject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getSubject() == null ? other.getSubject() == null : this.getSubject().equals(other.getSubject()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", tag=").append(tag);
        sb.append(", createtime=").append(createtime);
        sb.append(", type=").append(type);
        sb.append(", title=").append(title);
        sb.append(", subject=").append(subject);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}