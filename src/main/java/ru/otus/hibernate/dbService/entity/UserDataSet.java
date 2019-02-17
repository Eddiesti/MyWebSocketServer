package ru.otus.hibernate.dbService.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    private String name;
    private int age;

    @Override
    public String toString() {
        return "Name: " + name + "\n Age: " + age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDataSet that = (UserDataSet) o;
        return age == that.age &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public UserDataSet() {

    }


}
