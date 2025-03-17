package com.example.user_api.data;

import org.springframework.beans.factory.annotation.Value;

public interface UserProjection {
    String getName();
    String getEmail();
    String getRole();
}
