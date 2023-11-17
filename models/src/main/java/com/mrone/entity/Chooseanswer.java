package com.mrone.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName chooseanswer
 */
@TableName(value ="chooseanswer")
public class Chooseanswer implements Serializable {
    /**
     * 题库id
     */
    @TableId
    private Integer sid;

    /**
     * 选择题题目
     */
    private String choosename;

    /**
     * A
     */
    private String anwsera;

    /**
     * B
     */
    private String anwserb;

    /**
     * C
     */
    private String anwserc;

    /**
     * D
     */
    private String anwserd;

    private String answer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 题库id
     */
    public Integer getSid() {
        return sid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 题库id
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 选择题题目
     */
    public String getChoosename() {
        return choosename;
    }

    /**
     * 选择题题目
     */
    public void setChoosename(String choosename) {
        this.choosename = choosename;
    }

    /**
     * A
     */
    public String getAnwsera() {
        return anwsera;
    }

    /**
     * A
     */
    public void setAnwsera(String anwsera) {
        this.anwsera = anwsera;
    }

    /**
     * B
     */
    public String getAnwserb() {
        return anwserb;
    }

    /**
     * B
     */
    public void setAnwserb(String anwserb) {
        this.anwserb = anwserb;
    }

    /**
     * C
     */
    public String getAnwserc() {
        return anwserc;
    }

    /**
     * C
     */
    public void setAnwserc(String anwserc) {
        this.anwserc = anwserc;
    }

    /**
     * D
     */
    public String getAnwserd() {
        return anwserd;
    }

    /**
     * D
     */
    public void setAnwserd(String anwserd) {
        this.anwserd = anwserd;
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
        Chooseanswer other = (Chooseanswer) that;
        return (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getChoosename() == null ? other.getChoosename() == null : this.getChoosename().equals(other.getChoosename()))
            && (this.getAnwsera() == null ? other.getAnwsera() == null : this.getAnwsera().equals(other.getAnwsera()))
            && (this.getAnwserb() == null ? other.getAnwserb() == null : this.getAnwserb().equals(other.getAnwserb()))
            && (this.getAnwserc() == null ? other.getAnwserc() == null : this.getAnwserc().equals(other.getAnwserc()))
            && (this.getAnwserd() == null ? other.getAnwserd() == null : this.getAnwserd().equals(other.getAnwserd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getChoosename() == null) ? 0 : getChoosename().hashCode());
        result = prime * result + ((getAnwsera() == null) ? 0 : getAnwsera().hashCode());
        result = prime * result + ((getAnwserb() == null) ? 0 : getAnwserb().hashCode());
        result = prime * result + ((getAnwserc() == null) ? 0 : getAnwserc().hashCode());
        result = prime * result + ((getAnwserd() == null) ? 0 : getAnwserd().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", choosename=").append(choosename);
        sb.append(", anwsera=").append(anwsera);
        sb.append(", anwserb=").append(anwserb);
        sb.append(", anwserc=").append(anwserc);
        sb.append(", anwserd=").append(anwserd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}