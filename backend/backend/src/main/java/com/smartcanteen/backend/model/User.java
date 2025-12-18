package com.smartcanteen.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String role;        // STUDENT / STAFF / ADMIN
    private String registerNo;
    private String password;

    public User() {}

    public User(String role, String registerNo, String password) {
        this.role = role;
        this.registerNo = registerNo;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
