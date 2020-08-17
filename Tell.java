package com.company;

public class Tell {

    private int id;
    private String tellerName;
    private String title;
    private String keyword;

    public Tell(int id, String tellerName, String title, String keyword) {
        this.id = id;
        this.tellerName = tellerName;
        this.title = title;
        this.keyword = keyword;
    }

    public int getId() {
        return id;
    }

    public String getTellerName() {
        return tellerName;
    }

    public String getTitle() {
        return title;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public String toString() {

        return String.format("Tell{%n\tid = %d,%n\ttitle = %s,%n\tteller name = %s%n\tkeyword = %s%n}", id, title, tellerName, keyword);
    }
}
