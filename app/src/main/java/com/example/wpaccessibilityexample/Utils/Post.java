package com.example.wpaccessibilityexample.Utils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName =  "post")
public class Post {

    @ColumnInfo(name = "body")
    private String body;

    @ColumnInfo ( name = "id" )
    private int id ;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "userId")
    private int userId;

    @Ignore
    public Post(String body, int id, String title, int userId) {
        this.body = body;
        this.id = id;
        this.title = title;
        this.userId = userId;
    }

    public Post() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
