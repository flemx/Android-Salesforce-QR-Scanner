package com.example.salesforcescanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact {

    private String date;
    private String name;
    private String id;

    public Contact(String name, String id) {
        this.name = name;
        this.date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
