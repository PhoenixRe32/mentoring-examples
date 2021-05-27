package com.pittacode.restspringtemp;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ftocservice")
public class FtoCService {

    @GetMapping(produces = "application/json")
    public ResponseEntity<String> reference() {

        JSONObject jsonObject = new JSONObject();
        double fahrenheit = 98.24;
        double celsius = (fahrenheit - 32) * 5 / 9;
        jsonObject.put("F Value", fahrenheit);
        jsonObject.put("C Value", celsius);

        return ResponseEntity.ok(jsonObject.toString(2));
    }

    @GetMapping(path = "/{f}", produces = "application/json")
    public ResponseEntity<String> convertFtoCfromInput(@PathVariable(name = "f") double f) {

        JSONObject jsonObject = new JSONObject();
        double celsius = (f - 32) * 5 / 9;
        jsonObject.put("F Value", f);
        jsonObject.put("C Value", celsius);

        return ResponseEntity.ok(jsonObject.toString(2));
    }
}
