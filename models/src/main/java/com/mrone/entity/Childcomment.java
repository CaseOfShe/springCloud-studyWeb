package com.mrone.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @TableName childcomment
 */
@TableName(value ="childcomment")
public class Childcomment implements Serializable {
    /**
     * 子评论者的连接id
     */
    @TableId
    private Integer cid;

    /**
     * 父评论id
     */
    private Integer fid;

    /**
     * 内容
     */
    private String ccomment;

    /**
     * 图片
     */
    private String cimage;

    /**
     * 评论时间
     */
    private Date ctime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 子评论者的连接id
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 子评论者的连接id
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 父评论id
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 父评论id
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * 内容
     */
    public String getCcomment() {
        return ccomment;
    }

    /**
     * 内容
     */
    public void setCcomment(String ccomment) {
        this.ccomment = ccomment;
    }

    /**
     * 图片
     */
    public String getCimage() {
        return cimage;
    }

    /**
     * 图片
     */
    public void setCimage(String cimage) {
        this.cimage = cimage;
    }

    /**
     * 评论时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 评论时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
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
        Childcomment other = (Childcomment) that;
        return (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getFid() == null ? other.getFid() == null : this.getFid().equals(other.getFid()))
            && (this.getCcomment() == null ? other.getCcomment() == null : this.getCcomment().equals(other.getCcomment()))
            && (this.getCimage() == null ? other.getCimage() == null : this.getCimage().equals(other.getCimage()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getFid() == null) ? 0 : getFid().hashCode());
        result = prime * result + ((getCcomment() == null) ? 0 : getCcomment().hashCode());
        result = prime * result + ((getCimage() == null) ? 0 : getCimage().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", fid=").append(fid);
        sb.append(", ccomment=").append(ccomment);
        sb.append(", cimage=").append(cimage);
        sb.append(", ctime=").append(ctime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}