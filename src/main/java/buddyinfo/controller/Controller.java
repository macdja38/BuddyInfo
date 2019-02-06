package buddyinfo.controller;

import buddyinfo.model.AddressBook;
import buddyinfo.model.BuddyInfo;

import buddyinfo.model.BuddyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    private int targetIndex;
    private BuddyInfo targetValue;
    @Autowired
    BuddyInfoRepository buddyInfoRepository;

    Controller() {
    }

    @PostMapping("/addressBook")
    public void create() {

    }

    @GetMapping(value="/ui", produces="text/html")
    public String getUI(@RequestParam(name="name", required=false, defaultValue="Fred") String name, Model model) {
        String ret = "<!DOCTYPE html>";
        ret += "<html>";
        ret += "<body>";

        ret += "<h1>Buddies</h1>";

        ret += "<p>";
        ret += buddyInfoRepository.findByName(name).stream().map(buddy -> buddy.toString()).collect(Collectors.joining("<br>"));
        ret += "</p>";

        ret += "</body>";
        ret += "</html>";

        return ret;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<BuddyInfo> getBuddy(@RequestParam(name="name", required=false, defaultValue="Fred") String name, Model model) {
        buddyInfoRepository.findByName(name);
        return buddyInfoRepository.findByName(name);
    }

    @PostMapping(value = "/", produces = "application/json")
    public List<BuddyInfo> addBuddy(@RequestParam(name="name", required=false, defaultValue="Fred") String name, @RequestParam(name="address", required=false, defaultValue="342 road") String address, Model model) {
        buddyInfoRepository.save(new BuddyInfo(name, address));
        return buddyInfoRepository.findByName(name);
    }
}
