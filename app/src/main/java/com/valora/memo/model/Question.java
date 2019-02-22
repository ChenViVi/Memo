package com.valora.memo.model;

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

    @Generated(hash = 279833596)
    public Question(Long id, Long setId, String content, String answer) {
        this.id = id;
        this.setId = setId;
        this.content = content;
        this.answer = answer;
    }

    @Generated(hash = 1868476517)
    public Question() {
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
}
