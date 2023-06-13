package com.letswatch.watchparty.services;

import com.letswatch.watchparty.dto.PartyDto;
import com.letswatch.watchparty.models.Party;

import java.util.List;

public interface PartyServices {
    List<PartyDto> findAllParties();
    Party saveParty(PartyDto partyDto);

    PartyDto findPartyById(long partyId);

    void updateParty(PartyDto party);

    void delete(long partyId);

    List<PartyDto> searchParties(String query);
}
