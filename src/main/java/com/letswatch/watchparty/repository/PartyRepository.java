package com.letswatch.watchparty.repository;

import com.letswatch.watchparty.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Long> {
    Optional<Party> findByName(String url);
    @Query("SELECT p from Party p where p.name LIKE CONCAT('%', :query, '%' ) ")
    List<Party> searchParties(String query);
}
