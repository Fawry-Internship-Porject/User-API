package com.example.user_api.data;

public record UserAuditDTO(String name, String role, String title) {
    public static UserAuditDTO from(User user) {
        return new UserAuditDTO(user.getName(), user.getRole(), user.getTitle());
    }
}