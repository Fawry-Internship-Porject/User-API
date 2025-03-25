package com.example.user_api.data;

import com.example.user_api.controller.Level;

public interface UserProjection {
    int getId();
    String getName();
    String getTitle();
    String getRole();
    String getMail();
    String getPhone();
    Level getLevel();
    Integer getManagerId();
    Integer getDepartmentId();
}
