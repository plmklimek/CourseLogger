package com.example.demo.models;

public class AppUserDetailsWithId extends AppUserDetails {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUserDetailsWithId(User user, Long id) {
        super(user);
        this.id = id;
    }
}
