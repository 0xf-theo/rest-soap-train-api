package fr.train.rest.filter.model;

import io.ebean.Model;
import io.ebean.annotation.Expose;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public class BaseModel extends Model implements Serializable {

    @Id
    @Expose
    UUID id;

    @WhenCreated
    Timestamp created_at;

    @WhenModified
    Timestamp updated_at;

    public UUID getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdatedAt() {
        return updated_at;
    }

    public BaseModel setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel baseModel = (BaseModel) o;
        return Objects.equals(id, baseModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + getId() + '}';
    }

}
