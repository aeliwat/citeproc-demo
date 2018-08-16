package com.aeliwat.citeproc.service;

import de.undercouch.citeproc.ItemDataProvider;
import de.undercouch.citeproc.csl.CSLItemData;
import de.undercouch.citeproc.csl.CSLItemDataBuilder;
import de.undercouch.citeproc.csl.CSLType;
import de.undercouch.citeproc.helper.json.JsonBuilder;
import de.undercouch.citeproc.helper.json.JsonBuilderFactory;
import de.undercouch.citeproc.helper.json.JsonObject;
import de.undercouch.citeproc.helper.json.StringJsonBuilderFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class CiteProcService {

    private static JsonBuilderFactory factory = new StringJsonBuilderFactory();

    public static String collectMetaData() {

        ItemDataProvider itemDataProvider = new ItemDataProvider() {
            @Override
            public CSLItemData retrieveItem(String id) {
                return new CSLItemDataBuilder()
                        .id(id)
                        .type(CSLType.ARTICLE_JOURNAL)
                        .title("This is title")
                        .author("John", "Smith")
                        .containerTitle("Dummy Journal title ")
                        .issued(1993, 12, 1)
                        .DOI("10.222/212121")
                        .URL("10.222/212121")
                        .volume(12)
                        .issue(3)
                        .page("10-4")
                        .accessed(2018, 22, 8)
                        .build();
            }

            @Override
            public String[] getIds() {
                return new String[]{"item-4"};
            }
        };

        // convert meta Date to Json Object
        return convertToJSONObject(itemDataProvider, "item-4");
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

                return everything;

            } finally {
                br.close();
            }
        }

        return "";
    }

    /*
    *  Convert Object to JSON object
    * */

    public static String convertToJSONObject(ItemDataProvider itemDataProvider, String id) {

        JsonObject obj = new JsonObject() {
            @Override
            public Object toJson(JsonBuilder builder) {
                builder.add(id, itemDataProvider.retrieveItem(id));
                return builder.build();
            }
        };

        return obj.toJson(factory.createJsonBuilder()).toString();
    }
}
