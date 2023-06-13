package com.letswatch.watchparty.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;
    private String eventName;
    private String eventDescription;
    private String eventPhotoUrl;
    @CreationTimestamp
    private LocalDateTime eventCreatedOn;
    @UpdateTimestamp
    private LocalDateTime eventUpdatedOn;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;

    @ManyToOne
    @JoinColumn(name="party_id", nullable = false)
    private Party party;

}
