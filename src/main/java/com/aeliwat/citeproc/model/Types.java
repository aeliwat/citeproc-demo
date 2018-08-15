package com.aeliwat.citeproc.model;


public enum Types {

    ARTICLE("article"), ARTICLE_JOURNAL("article-journal");
    private String name;

    private Types(String name) {
        this.name=name;
    }
    @Override
    public String toString() {
        return name;
    }
}
