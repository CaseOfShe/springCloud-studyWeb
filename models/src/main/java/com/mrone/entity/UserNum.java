package com.mrone.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


/**
 * 
 * @TableName usernum
 */
@Data
@TableName(value ="usernum")
public class UserNum implements Serializable {
    /**
     * 第几个注册的用户
     */
    @TableId
    private int id;

    /**
     * openid
     */
    private String openid;

//    @TableField(exist = false)
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 第几个注册的用户
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     * 第几个注册的用户
//     */
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    /**
//     * openid
//     */
//    public String getOpenid() {
//        return openid;
//    }
//
//    /**
//     * openid
//     */
//    public void setOpenid(String openid) {
//        this.openid = openid;
//    }
//
//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        UserNum other = (UserNum) that;
//        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
//            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
//        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", openid=").append(openid);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}