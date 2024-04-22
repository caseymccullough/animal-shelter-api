package com.mccullough.animalshelter.model;

public class Guest {
    private int id;
    private int typeId;
    private String name;
    private String gender;
    private String background;

    public Guest() {
    }

    public Guest(int id, int typeId, String name, String gender, String background) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
        this.gender = gender;
        this.background = background;
    }

    public int getId() {
        return id;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBackground() {
        return background;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
