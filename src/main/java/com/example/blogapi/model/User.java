package com.example.blogapi.model;

import com.example.blogapi.enums.UserAccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "user_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseModel {

  private String name;
  private String email;
  private String password;
  private String userName;
  @Enumerated(value = EnumType.STRING)
  private UserAccountStatus status;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Post> posts;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

  @JsonIgnore
  @OneToMany(mappedBy = "connector", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Connection> connections;

  @JsonIgnore
  @OneToMany(mappedBy = "connection", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Connection> connectors;
  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Favorite> favorites;

}
