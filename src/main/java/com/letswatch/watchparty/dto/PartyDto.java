package com.letswatch.watchparty.dto;
import java.time.LocalDateTime;
import java.util.List;

import com.letswatch.watchparty.models.Event;
import com.letswatch.watchparty.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PartyDto {
    private Long id;
    @NotEmpty(message = "Please add a Name for your Party")
    private String name;
    @NotEmpty(message = "Please add a Description for your Party")
    private String description;
    @NotEmpty(message = "Please add an image for your Party")
    private String imageUrl;
    private UserEntity user;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdatedOn;
    private List<EventDto> events;
}
