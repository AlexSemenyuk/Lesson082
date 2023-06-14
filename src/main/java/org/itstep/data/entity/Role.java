package org.itstep.data.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false, unique = true, name = "mean")
    private String mean;
    @OneToMany (mappedBy = "role", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    public Role() {
    }

    public Role(String mean) {
        this.mean = mean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", mean='" + mean + '\'' +
                '}';
    }
}
