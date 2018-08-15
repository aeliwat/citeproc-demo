package com.aeliwat.citeproc.model;

import com.google.gson.annotations.SerializedName;
import de.undercouch.citeproc.csl.CSLName;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private String id;
    private String type;
    private String title;

    @SerializedName("container-title")
    private String containerTitle;

    private String page;
    private String volume;
    private String issue;
    private String URL;
    private String DOI;
    private String shortTitle;
    private String journalAbbreviation;
    List< CSLName > author = new ArrayList();

    private Issued issued ;
    private Accessed accessed;

    public Issued getIssued() {
        return issued;
    }

    public void setIssued(Issued issued) {
        this.issued = issued;
    }

    public List<CSLName> getAuthor() {
        return author;
    }

    public void setAuthor(List<CSLName> author) {
        this.author = author;
    }


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getContainerTitle() {
        return containerTitle;
    }

    public String getPage() {
        return page;
    }

    public String getVolume() {
        return volume;
    }

    public String getIssue() {
        return issue;
    }


    public String getURL() {
        return URL;
    }

    public String getDOI() {
        return DOI;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getJournalAbbreviation() {
        return journalAbbreviation;
    }


    public Accessed getAccessed() {
        return accessed;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContainerTitle(String containerTitle) {
        this.containerTitle=containerTitle;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }


    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public void setJournalAbbreviation(String journalAbbreviation) {
        this.journalAbbreviation = journalAbbreviation;
    }


    public void setAccessed(Accessed accessed) {
        this.accessed = accessed;
    }


}


