package com.letswatch.watchparty.mapper;

import com.letswatch.watchparty.dto.PartyDto;
import com.letswatch.watchparty.models.Party;
import com.letswatch.watchparty.models.Event;

import java.util.stream.Collectors;

import static com.letswatch.watchparty.mapper.EventMapper.mapToEventDto;

public class PartyMapper {
    public static Party maptoParty(PartyDto party){
        Party partyDto = Party.builder()
                .id(party.getId())
                .name(party.getName())
                .description(party.getDescription())
                .imageUrl(party.getImageUrl())
                .createdOn(party.getCreatedOn())
                .createdBy(party.getUser())
                .lastUpdatedOn(party.getLastUpdatedOn())
                .build();
        return partyDto;
    }

    public static PartyDto maptoPartyDto(Party party){
        PartyDto partyDto = PartyDto.builder()
                .id(party.getId())
                .name(party.getName())
                .description((party.getDescription()))
                .imageUrl(party.getImageUrl())
                .createdOn(party.getCreatedOn())
                .user(party.getCreatedBy())
                .lastUpdatedOn(party.getLastUpdatedOn())
                .events(party.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return partyDto;
    }
}
