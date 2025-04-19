package com.example.user_api.data.ldap;

import lombok.*;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(
        base = "ou=users",
        objectClasses = { "user", "top" }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class LdapUser {
    @Id
    private Name dn;

    @Attribute(name = "userid")
    private String username;

    @Attribute(name = "userPassword")
    private String password;
}
