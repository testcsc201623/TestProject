package com.example.demo.common.entity;

import java.util.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;
 
@Entity
@Data
@Table(name = "user_mst")
public class User {
    @Id
    @Column(name = "user_id")
    public String userId;
 
    @Column(name = "password")
    public String password;
 
    @Column(name = "user_name")
    public String userName;
 
    @Column(name = "create_date_time")
    public Date createDateTime;
 
    @Column(name = "update_date_time")
    public Date updateDateTime;
}