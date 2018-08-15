package com.aeliwat.citeproc.controller;


import com.aeliwat.citeproc.service.CiteProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Controller
public class MainController {

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
        Map itemDataProvider=this.citeProcService.collectMetaData();
        return this.citeProcService.convertToJSONObject(itemDataProvider);

    }

    @GetMapping(value="/readCSLFile/{selectedStyle}",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String readTargetFile(@PathVariable("selectedStyle") String style) throws IOException, URISyntaxException {
        String str=this.citeProcService.readCustomCSLFile(style);
        return str;
    }


}
