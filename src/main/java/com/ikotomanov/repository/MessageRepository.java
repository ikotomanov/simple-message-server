package com.ikotomanov.repository;

import com.ikotomanov.entity.PersistentMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<PersistentMessage, Long> {
}
