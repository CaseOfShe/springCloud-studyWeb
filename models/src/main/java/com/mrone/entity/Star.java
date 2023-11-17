package com.mrone.entity;

import java.io.Serializable;

/**
 * 
 * @TableName star
 */
public class Star implements Serializable {
    /**
     * id
     */

    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 点赞用户id
     */
    private Integer uid;

    /**
     * 点赞状态 0取消 1点赞
     */
    private Byte stauts;

    private static final long serialVersionUID = 1L;

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
     * 点赞用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 点赞用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 点赞状态 0取消 1点赞
     */
    public Byte getStauts() {
        return stauts;
    }

    /**
     * 点赞状态 0取消 1点赞
     */
    public void setStauts(Byte stauts) {
        this.stauts = stauts;
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
        Star other = (Star) that;
        return (this.getAid() == null ? other.getAid() == null : this.getAid().equals(other.getAid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getStauts() == null ? other.getStauts() == null : this.getStauts().equals(other.getStauts()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAid() == null) ? 0 : getAid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getStauts() == null) ? 0 : getStauts().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aid=").append(aid);
        sb.append(", uid=").append(uid);
        sb.append(", stauts=").append(stauts);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}