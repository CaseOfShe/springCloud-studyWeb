package com.mrone.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName shortanswer
 */
@TableName(value ="shortanswer")
public class Shortanswer implements Serializable {
    /**
     * 题库id
     */
    @TableId
    private Integer sid;

    /**
     * 简答题题目
     */
    private String answertitle;

    /**
     * 简答题答案
     */
    private String answer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 题库id
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 题库id
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 简答题题目
     */
    public String getAnswertitle() {
        return answertitle;
    }

    /**
     * 简答题题目
     */
    public void setAnswertitle(String answertitle) {
        this.answertitle = answertitle;
    }

    /**
     * 简答题答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 简答题答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
        Shortanswer other = (Shortanswer) that;
        return (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getAnswertitle() == null ? other.getAnswertitle() == null : this.getAnswertitle().equals(other.getAnswertitle()))
            && (this.getAnswer() == null ? other.getAnswer() == null : this.getAnswer().equals(other.getAnswer()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getAnswertitle() == null) ? 0 : getAnswertitle().hashCode());
        result = prime * result + ((getAnswer() == null) ? 0 : getAnswer().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", answertitle=").append(answertitle);
        sb.append(", answer=").append(answer);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}