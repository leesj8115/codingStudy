package com.nw.controller;

import java.util.List;

import com.nw.service.WebtoonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class WebtoonController {

  private final WebtoonService webtoonService;

  @Autowired
  public WebtoonController(WebtoonService webtoonService) {
    this.webtoonService = webtoonService;
  }

  

}
