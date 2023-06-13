package com.letswatch.watchparty.dto;

import com.letswatch.watchparty.models.Party;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private long eventId;
    private String eventName;
    private String eventDescription;
    private String eventPhotoUrl;
    private LocalDateTime eventCreatedOn;
    private LocalDateTime eventUpdatedOn;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventEndTime;
    private Party party;
}
