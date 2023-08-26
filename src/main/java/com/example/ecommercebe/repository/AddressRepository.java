package com.example.ecommercebe.repository;

import com.example.ecommercebe.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
