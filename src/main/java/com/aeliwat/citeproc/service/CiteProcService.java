package com.aeliwat.citeproc.service;

import com.aeliwat.citeproc.model.Accessed;
import com.aeliwat.citeproc.model.Issued;
import com.aeliwat.citeproc.model.Model;
import com.aeliwat.citeproc.model.Types;
import com.google.gson.GsonBuilder;
import de.undercouch.citeproc.csl.CSLName;
import de.undercouch.citeproc.csl.CSLNameBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class CiteProcService {


    public static Map collectMetaData() {

        // collect Author (Given + family)
        List<CSLName> cslNamesAuthors = new ArrayList<>();
        cslNamesAuthors.add(new CSLNameBuilder().given("Ammar").family("Eliwat").build());
        cslNamesAuthors.add(new CSLNameBuilder().given("Jarvis").family("Robert M.").build());

        // collect Isuued Date
        Issued issued= new Issued();
        issued.setDateParts(Arrays.asList(Arrays.asList(1993,22,1)));

        // collect Current Access Date
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        Accessed accessed = new Accessed();
        accessed.setDateParts(Arrays.asList(Arrays.asList(currentYear,currentMonth,currentDay)));

        Model model= new Model();

        model.setId("item-4");
        model.setTitle("Founder of the West Publishing Company");
        model.setType(Types.ARTICLE.name());
        model.setAuthor(cslNamesAuthors);
        model.setContainerTitle("The American Journal of Legal History");
        model.setIssued(issued);
        model.setDOI("10.11/2222");
        model.setURL("10.11/2222");
        model.setVolume("2");
        model.setIssue("4");
        model.setPage("10-80");
        model.setAccessed(accessed);

        // finally perpare Json element the JSON structure

        Map m = new HashMap();
        m.put("item-4",model);

        return m;
    }


    /*
    *  Read Custom CSL file From the current class path
    * */
    public String readCustomCSLFile(String style) throws IOException, URISyntaxException {


        final String DIR_NAME = "classpath:customCitationStyles";
        String fileName=DIR_NAME+"/custom-"+style+".txt";

        File file = ResourceUtils.getFile(fileName);
        if (file.exists()) {

            BufferedReader br = new BufferedReader(new FileReader(file.toPath().toString()));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line.trim());
                    line = br.readLine();
                }
                String everything = sb.toString().trim();

                return  everything;

            } finally {
                br.close();
            }
        }

        return "";
    }

    /*
    *  Convert Object to JSON object
    * */

    public String convertToJSONObject(Map items) {
        try {
            return new GsonBuilder().create().toJson(items);
        } catch (Exception ex) {
            return "";
        }
    }
}
