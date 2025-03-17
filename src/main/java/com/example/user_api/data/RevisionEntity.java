package com.example.user_api.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.envers.DefaultRevisionEntity;

import java.util.Date;

@Entity
@org.hibernate.envers.RevisionEntity
public class RevisionEntity extends DefaultRevisionEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date revisionDate = new Date();
    public Date getRevisionDate(){
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }
}
