package com.example.blogapi.model;

import com.example.blogapi.enums.LikeStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LikeComment extends BaseModel{
    @Enumerated(value = EnumType.STRING)
    private LikeStatus status;
    @ManyToOne
    private Comment comment;
    @OneToOne
    private User user;
}
