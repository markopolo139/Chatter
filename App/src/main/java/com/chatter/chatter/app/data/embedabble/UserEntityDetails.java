package com.chatter.chatter.app.data.embedabble;

import org.springframework.lang.Nullable;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Table;

@Embeddable
public class UserEntityDetails {

    private String firstName;

    private String lastName;

    @Nullable
    private String photo;

    public UserEntityDetails(String firstName, String lastName, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }

    protected UserEntityDetails() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
