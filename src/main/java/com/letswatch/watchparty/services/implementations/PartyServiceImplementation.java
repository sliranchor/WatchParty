package com.letswatch.watchparty.services.implementations;

import com.letswatch.watchparty.dto.PartyDto;
import com.letswatch.watchparty.models.Party;
import com.letswatch.watchparty.models.UserEntity;
import com.letswatch.watchparty.repository.PartyRepository;
import com.letswatch.watchparty.repository.UserRepository;
import com.letswatch.watchparty.security.PageSecurity;
import com.letswatch.watchparty.services.PartyServices;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.letswatch.watchparty.mapper.PartyMapper.maptoParty;
import static com.letswatch.watchparty.mapper.PartyMapper.maptoPartyDto;

@Service
public class PartyServiceImplementation implements PartyServices {

    private PartyRepository partyRepository;
    private UserRepository userRepository;

    @Autowired
    public PartyServiceImplementation(PartyRepository partyRepository, UserRepository userRepository){
        this.userRepository = userRepository;
        this.partyRepository = partyRepository;
    }

    @Override
    public List<PartyDto> findAllParties() {
        List<Party> parties = partyRepository.findAll();

        return parties.stream()
                .map((party) -> maptoPartyDto(party))
                .collect(Collectors.toList());
    }

    @Override
    public Party saveParty(PartyDto partyDto) {
        String userName = PageSecurity.getUserSession();
        UserEntity user = userRepository.findByUsername(userName);
        Party party = maptoParty(partyDto);
        party.setCreatedBy(user);
        return partyRepository.save(party);
    }


    @Override
    public PartyDto findPartyById(long partyId) {
        Party party = partyRepository.findById(partyId).get();
        return maptoPartyDto(party);
    }

    @Override
    public void updateParty(PartyDto partyDto) {
        String userName = PageSecurity.getUserSession();
        UserEntity user = userRepository.findByUsername(userName);
        Party party = maptoParty(partyDto);
        party.setCreatedBy(user);
        partyRepository.save(party);
    }

    @Override
    public void delete(long partyId) {
        partyRepository.deleteById(partyId);
    }

    @Override
    public List<PartyDto> searchParties(String query) {
        List<Party> parties = partyRepository.searchParties(query);
        return parties.stream()
                .map(party -> maptoPartyDto(party))
                .collect(Collectors.toList());
    }



}
