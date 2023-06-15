package com.letswatch.watchparty.controller;

import com.letswatch.watchparty.dto.PartyDto;
import com.letswatch.watchparty.models.Party;
import com.letswatch.watchparty.models.UserEntity;
import com.letswatch.watchparty.security.PageSecurity;
import com.letswatch.watchparty.services.PartyServices;
import com.letswatch.watchparty.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PartyController {
    private PartyServices partyServices;
    private UserServices userServices;

    @Autowired
    public PartyController(PartyServices partyServices, UserServices userServices){
        this.partyServices = partyServices;
        this.userServices = userServices;
    }

    /*
     GET Method to generate the full list of Parties
     */
    @GetMapping("/parties")
    public String listParties(Model model){
        UserEntity user = new UserEntity();
        List<PartyDto> parties = partyServices.findAllParties();
        String email = PageSecurity.getUserSession();
        if(email != null){
            user= userServices.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("parties", parties);
        return "parties-list";
    }

    /*
    GET Method to generate the full list of Parties
     */
    @GetMapping("/parties/new")
    public String createPartyForm(Model model){
        Party party = new Party();
        model.addAttribute("party", party);
        return "party-create";
    }

    /*
     POST Method to push the changes to the DB,
     along with error checking for missing or incomplete fields
     */
    @PostMapping("/parties/new")
    public String saveParty(@Valid @ModelAttribute("party") PartyDto partyDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("party", partyDto);
            return "party-create";
        }
        partyServices.saveParty(partyDto);
        return "redirect:/parties";
    }

    /*
     GET Method to search the DB for a given Party, by full word.
     */
    @GetMapping("/parties/search")
    public String searchForParty(@RequestParam(value = "query") String query, Model model){
        List<PartyDto> parties = partyServices.searchParties(query);
        model.addAttribute("parties", parties);
        return "parties-list";

    }

    /*
     GET Method to produce the form to edit a Party
     */

    @GetMapping("/parties/{partyId}/edit")
    public String editParty(@PathVariable("partyId") long id, Model model){
        PartyDto party = partyServices.findPartyById(id);
        model.addAttribute("party", party);
        return "party-edit";
    }
    /*
     POST Method to push the edited party to the DB, along with
     error checking for missing or incomplete fields.
     */
    @PostMapping("/parties/{partyId}/edit")
    public String updateParty(@PathVariable("partyId") long id, @Valid @ModelAttribute("party") PartyDto party,
                              BindingResult result, Model model  ){
        if(result.hasErrors()){
            model.addAttribute("party", party);
            return "party-edit";
        }
        party.setId(id);
        partyServices.updateParty(party);
        return "redirect:/parties";
    }
    /*
     GET method to show the Party details and show a list of events.
     */
    @GetMapping("/parties/{partyId}")
    public String partyDetails(@PathVariable("partyId") long partyId, Model model){
        UserEntity user = new UserEntity();
        PartyDto partyDto = partyServices.findPartyById(partyId);
        String email = PageSecurity.getUserSession();
        if(email != null){
            user= userServices.findByEmail(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("party", partyDto);
        return "party-detail";


    }
    /*
     GET method to delete a party.
     DELETE method not used because the form used does not support it
     */
    @GetMapping("/parties/{partyId}/delete")
    public String deleteParty(@PathVariable("partyId") long partyId){
        partyServices.delete(partyId);
        return "redirect:/parties";
    }




}
