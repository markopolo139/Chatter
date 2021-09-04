package com.chatter.chatter.app.data.embedabble;

import org.springframework.lang.Nullable;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Table;

@Embeddable
public class UserEntityDetails {

    private String firstName;

    private String lastName;

    @Lob
    @Nullable
    private byte[] photo;

    public UserEntityDetails(String firstName, String lastName, byte[] photo) {
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
