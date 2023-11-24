package com.tamas.gyorkis.mobilprog_project;

public class Subject {

    private Long id;
    private String name;
    private String code;
    private int credit;

    public Subject(Long id, String name, String code, int credit) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credit = credit;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
