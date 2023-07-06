package com.example.convertJsonToCsv.controller;
import com.example.convertJsonToCsv.service.JsonConvertToCsvService;
import com.example.convertJsonToCsv.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class JsonConvertToCsvContoller {
    @Autowired
    JsonConvertToCsvService jsonConvertToCsvService;
    @PostMapping("/json-to-csv")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("jsonfile") MultipartFile file) {
        String message = "";
        try {
            jsonConvertToCsvService.convertionMethod(file);
            message = "Converted JSON to CSV successfully" + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Couldn't Convert JSON to CSV" + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
