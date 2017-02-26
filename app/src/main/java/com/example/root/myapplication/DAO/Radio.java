package com.example.root.myapplication.DAO;

/**
 * Created by cyrilstern1 on 25/02/2017.
 */

public class Radio {
    private String id;
    private String Name;
    private String url;
    private String channel;

    public Radio(String id, String Name, String url, String channel) {
        this.id = id;
        this.Name = Name;
        this.channel = channel;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
