package com.example.user_api.data.ldap;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LdapGroupRepository extends LdapRepository<LdapGroup> {
    Optional<LdapGroup> findByName(String name);
}