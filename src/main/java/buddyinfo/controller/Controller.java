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

    private Sort getSort() {
        List<String> sorts = new ArrayList<>();
        sorts.add("name");
        return new Sort(Sort.Direction.ASC, sorts);
    }

    @GetMapping(value = "/ui", produces = "text/html")
    public String getUI(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        Stream<BuddyInfo> buddies;

        if (name.equals("")) {
            buddies = StreamSupport.stream(buddyInfoRepository.findAll(getSort()).spliterator(), false);
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

        ret += "<form id=\"createForm\" method=\"post\" action=\"/\" target=\"_blank\">" +
                "<h2>Create Buddy</h2>" +

                "<label for=\"name\">Name </label>" +
                "<input id=\"name\" name=\"name\" type=\"text\" maxlength=\"255\" value=\"\">" +

                "<label for=\"address\">Address </label>" +
                "<input id=\"address\" name=\"address\" type=\"text\" maxlength=\"255\" value=\"\">" +

                "<input id=\"saveForm\" class=\"button_text\" type=\"submit\" name=\"submit\" value=\"Submit\">" +
                "</form>";

        ret += "<form id=\"filterForm\" method=\"get\" action=\"/ui\" target=\"_self\">" +
                "<h2>Filter Buddies</h2>" +

                "<label for=\"filter_name\">Name </label>" +
                "<input id=\"filter_name\" name=\"name\" type=\"text\" maxlength=\"255\" value=\"\">" +

                "<input id=\"saveForm\" class=\"button_text\" type=\"submit\" name=\"submit\" value=\"Submit\">" +
                "</form>";

        ret += "</body>";
        ret += "<script>" +
                "function addBuddy(name, address) {\n" +
                "    return new Promise((resolve) => {\n" +
                "        let data = null;\n" +
                "\n" +
                "        const xhr = new XMLHttpRequest();\n" +
                "        xhr.withCredentials = true;\n" +
                "\n" +
                "        xhr.addEventListener('readystatechange', function () {\n" +
                "            if (this.readyState === 4) {\n" +
                "                console.log(this.responseText);\n" +
                "                resolve(JSON.parse(this.responseText));\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        xhr.open('POST', `http://localhost:8090?name=${name}&address=${encodeURIComponent(address)}`);\n" +
                "        xhr.setRequestHeader('cache-control', 'no-cache');\n" +
                "\n" +
                "        xhr.send(data);\n" +
                "    })\n" +
                "}\n" +
                "\n" +
                "function getBuddies(name) {\n" +
                "    return new Promise(resolve => {\n" +
                "        var data = null;\n" +
                "\n" +
                "        var xhr = new XMLHttpRequest();\n" +
                "        xhr.withCredentials = true;\n" +
                "\n" +
                "        xhr.addEventListener('readystatechange', function () {\n" +
                "            if (this.readyState === 4) {\n" +
                "                console.log(this.responseText);\n" +
                "                resolve(JSON.parse(this.responseText));\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        xhr.open('GET', `http://localhost:8090?name=${name}`);\n" +
                "        xhr.setRequestHeader('cache-control', 'no-cache');\n" +
                "\n" +
                "        xhr.send(data);\n" +
                "    })\n" +
                "}\n" +
                "\n" +
                "function createBuddyElement(buddyInfo) {\n" +
                "    const buddyElement = document.createElement('p');\n" +
                "    buddyElement.innerText = `${buddyInfo.name} - ${buddyInfo.address}`;\n" +
                "    return buddyElement;\n" +
                "}\n" +
                "\n" +
                "document.getElementById('createForm').addEventListener('submit', function (event) {\n" +
                "    const name = document.getElementById('name').value;\n" +
                "    const address = document.getElementById('address').value;\n" +
                "\n" +
                "    event.preventDefault();\n" +
                "    addBuddy(name, address)\n" +
                "        .then(([newBuddy]) => {\n" +
                "            const buddyElement = createBuddyElement(newBuddy);\n" +
                "            const buddiesElement = document.getElementById('buddies');\n" +
                "            buddiesElement.appendChild(buddyElement);\n" +
                "        })\n" +
                "\n" +
                "}, false);\n" +
                "\n" +
                "document.getElementById('filterForm').addEventListener('submit', function (event) {\n" +
                "    const name = document.getElementById('filter_name').value;\n" +
                "\n" +
                "    event.preventDefault();\n" +
                "    getBuddies(name)\n" +
                "        .then((newBuddies) => {\n" +
                "            const buddiesElement = document.getElementById('buddies');\n" +
                "            buddiesElement.innerText = '';\n" +
                "\n" +
                "            newBuddies.map(createBuddyElement).forEach(b => buddiesElement.appendChild(b))\n" +
                "        })\n" +
                "\n" +
                "}, false);\n" +
                "</script>";
        ret += "</html>";

        return ret;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<BuddyInfo> getBuddy(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        List<BuddyInfo> buddies;

        if (name.equals("")) {
            buddies = new ArrayList<>();
            buddyInfoRepository.findAll(getSort()).forEach(buddies::add);
        } else {
            buddies = buddyInfoRepository.findByName(name);
        }

        return buddies;
    }

    @PostMapping(value = "/", produces = "application/json")
    public List<BuddyInfo> addBuddy(@RequestParam(name = "name", required = false, defaultValue = "") String name, @RequestParam(name = "address", required = false, defaultValue = "342 road") String address, Model model) {
        buddyInfoRepository.save(new BuddyInfo(name, address));
        return buddyInfoRepository.findByName(name);
    }
}
