package com.anand.urlshortner;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    private final UrlService urlService;
    public UrlController(UrlService urlService){
        this.urlService=urlService;
    }
    @PostMapping("/shorten")
    public ResponseEntity<String>shorten(@RequestParam String url){
        Url saved=urlService.shortenUrl(url);
        return ResponseEntity.ok("Short code: "+saved.getShortCode());
    }
    @GetMapping("/{code}")
    public ResponseEntity<Void>redirect(@PathVariable String code){
        String originalUrl=urlService.getOriginalUrl(code);
        HttpHeaders headers=new HttpHeaders();
        headers.add("Location",originalUrl);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }
    }

