package com.apexsystems.demo.germanvillalba.api.repository;

import com.apexsystems.demo.germanvillalba.api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
