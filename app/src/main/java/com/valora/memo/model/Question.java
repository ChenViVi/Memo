package com.valora.memo.model;

import com.valora.memo.Tool;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class Question implements Serializable {

    private static final long serialVersionUID = -54635456843L;

    @Id(autoincrement = true)
    private Long id;
    private Long setId;
    private String content;
    private String answer;
    private int I = 1;
    private int n = 1;
    private double EF = 2.5;
    private Long time;

    public Question() {
        time = Tool.getTaskZeroPointTimestamps(1);
    }

    @Generated(hash = 953868463)
    public Question(Long id, Long setId, String content, String answer, int I,
            int n, double EF, Long time) {
        this.id = id;
        this.setId = setId;
        this.content = content;
        this.answer = answer;
        this.I = I;
        this.n = n;
        this.EF = EF;
        this.time = time;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getI() {
        return I;
    }

    public void setI(int i) {
        I = i;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getEF() {
        return EF;
    }

    public void setEF(double EF) {
        this.EF = EF;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
