package ru.otus.hibernate.entity;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDataSet that = (UserDataSet) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDataSet() {

    }

    public String getName() {
        return name;
    }
}
