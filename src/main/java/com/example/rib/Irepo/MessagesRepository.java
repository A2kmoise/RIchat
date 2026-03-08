package com.example.rib.Irepo;

import com.example.rib.Imodel.Messages;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, String> {
}
