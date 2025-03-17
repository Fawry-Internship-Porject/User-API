package com.example.user_api.data;

import org.springframework.beans.factory.annotation.Value;

public interface UserProjection {

    public String getName();
    public String getCompany();
    public String getDepartment();
    public int getId();
    public int getManagerId();

}
