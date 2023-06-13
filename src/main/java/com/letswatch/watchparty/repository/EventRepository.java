package com.letswatch.watchparty.repository;

import com.letswatch.watchparty.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
