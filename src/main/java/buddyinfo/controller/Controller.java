package buddyinfo.controller;

import buddyinfo.model.AddressBook;
import buddyinfo.model.BuddyInfo;

import buddyinfo.model.BuddyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
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
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    @GetMapping(value = "/ui", produces = "text/html")
    public String getUI(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        Stream<BuddyInfo> buddies;
        List<String> sorts = new ArrayList<>();
        sorts.add("name");
        if (name.equals("")) {
            buddies = StreamSupport.stream(buddyInfoRepository.findAll(new Sort(Sort.Direction.ASC, sorts)).spliterator(), false);
        } else {
            buddies = buddyInfoRepository.findByName(name).stream();
        }

        String ret = "<!DOCTYPE html>";
        ret += "<html>";
        ret += "<body>";

        ret += "<h1>Buddies</h1>";

        ret += "<p id=\"buddies\">";
        ret += buddies.map(BuddyInfo::toString).collect(Collectors.joining("<br>"));
        ret += "</p>";

        ret += "<br>";

        ret += "<form method=\"post\" action=\"/\" target=\"_blank\">" +
                "<h2>Create Buddy</h2>" +

                "<label for=\"name\">Name </label>" +
                "<input id=\"name\" name=\"name\" type=\"text\" maxlength=\"255\" value=\"\">" +

                "<label for=\"address\">Address </label>" +
                "<input id=\"address\" name=\"address\" type=\"text\" maxlength=\"255\" value=\"\">" +

                "<input id=\"saveForm\" class=\"button_text\" type=\"submit\" name=\"submit\" value=\"Submit\">" +
                "</form>";

        ret += "<form method=\"get\" action=\"/ui\" target=\"_self\">" +
                "<h2>Filter Buddies</h2>" +

                "<label for=\"filter_name\">Name </label>" +
                "<input id=\"filter_name\" name=\"name\" type=\"text\" maxlength=\"255\" value=\"\">" +

                "<input id=\"saveForm\" class=\"button_text\" type=\"submit\" name=\"submit\" value=\"Submit\">" +
                "</form>";

        ret += "</body>";
        ret += "</html>";

        return ret;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<BuddyInfo> getBuddy(@RequestParam(name = "name", required = false, defaultValue = "Fred") String name, Model model) {
        buddyInfoRepository.findByName(name);
        return buddyInfoRepository.findByName(name);
    }

    @PostMapping(value = "/", produces = "application/json")
    public List<BuddyInfo> addBuddy(@RequestParam(name = "name", required = false, defaultValue = "Fred") String name, @RequestParam(name = "address", required = false, defaultValue = "342 road") String address, Model model) {
        buddyInfoRepository.save(new BuddyInfo(name, address));
        return buddyInfoRepository.findByName(name);
    }
}
