package com.example.devprofile.model;

public class Profile {
    private String name;
    private String bio;
    private String experience;
    private String contact;
    private String banner;
    private String photoPath;

    public Profile() {}

    public Profile(String name, String bio, String experience, String contact, String banner, String photoPath) {
        this.name = name;
        this.bio = bio;
        this.experience = experience;
        this.contact = contact;
        this.banner = banner;
        this.photoPath = photoPath;
    }

    public String getName() { return name; }
    public String getBio() { return bio; }
    public String getExperience() { return experience; }
    public String getContact() { return contact; }
    public String getBanner() { return banner; }
    public String getPhotoPath() { return photoPath; }

    public void setName(String name) { this.name = name; }
    public void setBio(String bio) { this.bio = bio; }
    public void setExperience(String experience) { this.experience = experience; }
    public void setContact(String contact) { this.contact = contact; }
    public void setBanner(String banner) { this.banner = banner; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
}
