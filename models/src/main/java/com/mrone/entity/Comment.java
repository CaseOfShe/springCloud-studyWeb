package com.mrone.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName comment
 */
@TableName(value ="comment")
public class Comment implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 连接唯一标识符
     */
    private Integer fid;

    /**
     * 评论内容
     */
    private String fcomment;

    /**
     * 图片
     */
    private String fimage;

    /**
     * 评论时间
     */
    private Date ftime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 文章id
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 文章id
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 连接唯一标识符
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 连接唯一标识符
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * 评论内容
     */
    public String getFcomment() {
        return fcomment;
    }

    /**
     * 评论内容
     */
    public void setFcomment(String fcomment) {
        this.fcomment = fcomment;
    }

    /**
     * 图片
     */
    public String getFimage() {
        return fimage;
    }

    /**
     * 图片
     */
    public void setFimage(String fimage) {
        this.fimage = fimage;
    }

    /**
     * 评论时间
     */
    public Date getFtime() {
        return ftime;
    }

    /**
     * 评论时间
     */
    public void setFtime(Date ftime) {
        this.ftime = ftime;
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
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAid() == null ? other.getAid() == null : this.getAid().equals(other.getAid()))
                && (this.getFid() == null ? other.getFid() == null : this.getFid().equals(other.getFid()))
                && (this.getFcomment() == null ? other.getFcomment() == null : this.getFcomment().equals(other.getFcomment()))
                && (this.getFimage() == null ? other.getFimage() == null : this.getFimage().equals(other.getFimage()))
                && (this.getFtime() == null ? other.getFtime() == null : this.getFtime().equals(other.getFtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAid() == null) ? 0 : getAid().hashCode());
        result = prime * result + ((getFid() == null) ? 0 : getFid().hashCode());
        result = prime * result + ((getFcomment() == null) ? 0 : getFcomment().hashCode());
        result = prime * result + ((getFimage() == null) ? 0 : getFimage().hashCode());
        result = prime * result + ((getFtime() == null) ? 0 : getFtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", aid=").append(aid);
        sb.append(", fid=").append(fid);
        sb.append(", fcomment=").append(fcomment);
        sb.append(", fimage=").append(fimage);
        sb.append(", ftime=").append(ftime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}