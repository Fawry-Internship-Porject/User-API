package com.example.user_api.service;

import com.example.user_api.data.Role;
import com.example.user_api.data.ldap.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LdapUserService {
    private final LdapUserRepository userRepository;
    private final LdapGroupRepository groupRepository;
    private final LdapTemplate ldapTemplate;

    public Optional<LdapUser> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void add(String username, String password, Role role) {
        Name dn = getUserDn(username);
        DirContextAdapter context = new DirContextAdapter(dn.toString());
        context.setAttributeValues("objectClass", new String[]{"top", "user"});
        context.setAttributeValue("userid", username);
        context.setAttributeValue("userPassword", password);

        ldapTemplate.bind(context);

        addUserToGroup(username, role);
    }

    public void addUserToGroup(String username, Role role) {
        String groupName = getGroupNameForRole(role);

        Optional<LdapUser> user = userRepository.findByUsername(username);
        Optional<LdapGroup> group = groupRepository.findByName(groupName);

        if (user.isPresent() && group.isPresent()) {
            Name userDn = user.get().getDn();
            if (!group.get().getMembers().contains(userDn)) {
                group.get().getMembers().add(userDn);
                this.groupRepository.save(group.get());
            }
        }
    }

    public void modify(String username, String newPassword, Role newRole) {
        delete(username);
        add(username, newPassword, newRole);
    }

    public void delete(String username) {
        Optional<LdapUser> user = this.userRepository.findByUsername(username);
        if (user.isPresent()) {
            this.ldapTemplate.unbind(getUserDn(username));
            removeUserFromAllGroups(user.get());
        }
    }

    private Name getUserDn(String username) {
        return LdapNameBuilder.newInstance("ou=users")
                .add("userid", username)
                .build();
    }

    private Name getGroupDn(String groupName) {
        return LdapNameBuilder.newInstance("ou=groups")
                .add("cn", groupName)
                .build();
    }

    private String getGroupNameForRole(Role role) {
        return switch (role) {
            case MANAGER -> "MANAGERS";
            case COMPANY_MANAGER -> "COMPANYMANAGERS";
            case EMPLOYEE -> "EMPLOYEES";
        };
    }

    private void removeUserFromAllGroups(LdapUser user) {
        Name userDn = user.getDn();

        List<String> groupsContainingUser = findGroupsWithMember(userDn);

        for (String groupName : groupsContainingUser) {
            removeMemberFromGroup(getGroupDn(groupName), userDn);
        }
    }

    private void removeMemberFromGroup(Name groupDn, Name memberToRemove) {
        ModificationItem[] modifications = new ModificationItem[]{
                new ModificationItem(DirContext.REMOVE_ATTRIBUTE,
                        new BasicAttribute("member", memberToRemove.toString()))
        };
        ldapTemplate.modifyAttributes(groupDn, modifications);
    }

    private List<String> findGroupsWithMember(Name memberDn) {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectClass", "groupOfNames"));
        filter.and(new EqualsFilter("member", memberDn.toString()));

        return ldapTemplate.search(
                "ou=groups",
                filter.encode(),
                SearchControls.SUBTREE_SCOPE,
                (AttributesMapper<String>) attrs -> attrs.get("cn").get().toString()
        );
    }

}
