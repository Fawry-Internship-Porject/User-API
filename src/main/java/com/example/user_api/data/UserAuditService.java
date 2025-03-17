package com.example.user_api.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuditService {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Number> getUserAuditHistory(int userId){
        AuditReader auditReader= AuditReaderFactory.get(entityManager);
        return auditReader.createQuery()
                .forRevisionsOfEntity(User.class,true,true)
                .add(AuditEntity.id().eq(userId))
                .getResultList();
    }
}
