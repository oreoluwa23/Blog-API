package com.example.blogapi.repository;

import com.example.blogapi.model.Connection;
import com.example.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
Optional<Connection> findConnectionByConnectorAndConnection(User userConnector, User userConnection);
Optional<Connection> findConnectionByConnection(User userConnection);
}
