package com.aeliwat.citeproc.controller;


import com.aeliwat.citeproc.service.CiteProcService;
import de.undercouch.citeproc.ItemDataProvider;
import de.undercouch.citeproc.csl.CSLItemData;
import de.undercouch.citeproc.csl.CSLItemDataBuilder;
import de.undercouch.citeproc.csl.CSLType;
import de.undercouch.citeproc.helper.json.JsonBuilder;
import de.undercouch.citeproc.helper.json.JsonBuilderFactory;
import de.undercouch.citeproc.helper.json.JsonObject;
import de.undercouch.citeproc.helper.json.StringJsonBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    private JsonBuilderFactory factory = new StringJsonBuilderFactory();
    @Autowired
    private  CiteProcService citeProcService;

    @Autowired(required = false)
    ApplicationInstanceInfo instanceInfo;

    @GetMapping("/")
    public  String showHome(Model model) throws URISyntaxException {
        // information about current cloud instance running
        model.addAttribute("instanceInfo", instanceInfo);

        return "index";
    }

    @GetMapping("/getMetaData")
    @ResponseBody
    public  String getMeataData(){
       String str=this.citeProcService.collectMetaData();
        return str;

    }

    @GetMapping(value="/readCSLFile/{selectedStyle}",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String readTargetFile(@PathVariable("selectedStyle") String style) throws IOException, URISyntaxException {
        String str=this.citeProcService.readCustomCSLFile(style);
        return str;
    }


}
