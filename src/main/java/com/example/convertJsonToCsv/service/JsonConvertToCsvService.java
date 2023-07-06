package com.example.convertJsonToCsv.service;
import java.io.File;
import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@Service
public class JsonConvertToCsvService {
    public String convertionMethod(MultipartFile file) throws IOException, ParseException, JSONException {
        byte[] bytes = file.getBytes();
        String jsonString = new String(bytes);
        Gson gson = new Gson();
        JsonObject jsonrootObject = gson.fromJson(jsonString, JsonObject.class);
        JsonObject hitObject = jsonrootObject.getAsJsonObject("hits");
        JsonElement hitElement = hitObject.getAsJsonObject().get("hits");
        JsonArray hitArray=hitElement.getAsJsonArray();
        StringBuilder sb=new StringBuilder();
        sb.append("title").append(",").append("isbn").append(",").append("doi").append("\n");
        for(JsonElement experienceElement : hitArray)
        {
            JsonObject companyObject = experienceElement.getAsJsonObject();
            JsonElement source = companyObject.getAsJsonObject().get("_source");
            sb.append(source.getAsJsonObject().get("title")).append(",");
            JsonObject sourceobj= (JsonObject) source.getAsJsonObject().get("identifiers");
            sb.append(sourceobj.getAsJsonObject().get("isbn")).append(",");
            sb.append(sourceobj.getAsJsonObject().get("doi")).append("\n");
    }
        File csvfile = new File("output.csv");
        FileUtils.writeStringToFile(csvfile, sb.toString());
        return "Data has been Sucessfully Writeen to file";
    }
}