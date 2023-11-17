package com.mrone.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


/**
 *
 * @TableName collect
 */
@TableName(value ="collect")
public class Collect implements Serializable {
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
     * 连接openid
     */
    private Integer uid;

    /**
     * 收藏
     */
    private Byte collect;

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
     * 连接openid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 连接openid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 收藏
     */
    public Byte getCollect() {
        return collect;
    }

    /**
     * 收藏
     */
    public void setCollect(Byte collect) {
        this.collect = collect;
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
        Collect other = (Collect) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAid() == null ? other.getAid() == null : this.getAid().equals(other.getAid()))
                && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getCollect() == null ? other.getCollect() == null : this.getCollect().equals(other.getCollect()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAid() == null) ? 0 : getAid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getCollect() == null) ? 0 : getCollect().hashCode());
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
        sb.append(", uid=").append(uid);
        sb.append(", collect=").append(collect);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}