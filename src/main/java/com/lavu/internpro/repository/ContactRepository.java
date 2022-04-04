package com.lavu.internpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lavu.internpro.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}
