package com.doorboard3.doorboardtablet;

import android.graphics.Bitmap;

/**
 * Created by charmipatel on 4/5/17.
 */

public class RoomOwner {

    private String name;
    private String description;
    private int image;
    private String roomNum;
    private String email;
    private String phoneNumber;

    public RoomOwner(String name, String description, int image, String roomNum, String email, String phoneNumber) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.roomNum = roomNum;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
