package com.example.covid19app.NewsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source
{
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
