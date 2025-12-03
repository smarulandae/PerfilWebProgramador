package com.example.devprofile.model;

public class Skill {
    private int id;
    private String name;
    private String level;

    public Skill() {}

    public Skill(int id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLevel() { return level; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLevel(String level) { this.level = level; }
}
