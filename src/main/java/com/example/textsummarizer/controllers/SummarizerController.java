package com.example.textsummarizer.controllers;

import com.example.textsummarizer.models.Payload;
import com.example.textsummarizer.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/demo")
public class SummarizerController {
    @Autowired
    private DemoService demoService;

    @GetMapping
    public ResponseEntity<Optional<Payload>> summarize (@RequestBody Payload reqPayload){
        Optional<Payload> response = demoService.summarize(reqPayload);

        if(response.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
