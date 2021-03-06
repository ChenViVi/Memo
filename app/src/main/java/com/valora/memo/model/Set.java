package com.valora.memo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class Set implements Serializable{

    private static final long serialVersionUID = -8940196742313994740L;

    @Id(autoincrement = true)
    private Long id;
    private String name;

    @Generated(hash = 1490007889)
    public Set(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 865962195)
    public Set() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
