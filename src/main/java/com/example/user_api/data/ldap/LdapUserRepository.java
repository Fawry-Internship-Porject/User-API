package com.example.user_api.data.ldap;

import lombok.RequiredArgsConstructor;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LdapUserRepository extends LdapRepository<LdapUser> {
    void deleteByUsername(String username);
    Optional<LdapUser> findByUsername(String username);
}
