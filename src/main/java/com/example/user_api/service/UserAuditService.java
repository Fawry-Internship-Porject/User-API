package com.example.user_api.service;

import com.example.user_api.data.User;
import com.example.user_api.data.UserAuditDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserAuditService {
    @PersistenceContext
    private EntityManager entityManager;
    public List<UserAuditDTO> getUserAuditHistory(int userId) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        List<Number> revisions = reader.getRevisions(User.class, userId);

        return revisions.stream()
                .map(rev -> reader.find(User.class, userId, rev))
                .filter(Objects::nonNull)
                .map(UserAuditDTO::from)
                .toList();
    }
//    public List<Number> getUserAuditHistory(int userId){
//        AuditReader auditReader= AuditReaderFactory.get(entityManager);
//        return auditReader.createQuery()
//                .forRevisionsOfEntity(User.class,true,true)
//                .add(AuditEntity.id().eq(userId))
//                .getResultList();
//    }
}
