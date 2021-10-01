package com.nw.controller;

import java.util.ArrayList;
import java.util.List;

import com.nw.domain.dto.ArtistDto;
import com.nw.domain.entity.ArtistEntity;
import com.nw.service.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



// @RestController
@Controller
public class ArtistController {
  private final ArtistService artistService;

  @Autowired
  public ArtistController(ArtistService artistService) {
    this.artistService = artistService;
  }

  @GetMapping("/artists")
  public ResponseEntity<List<ArtistDto>> findAll() {
    System.out.println("실행");

    List<ArtistDto> list = artistService.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(list);
  }
  
  @PostMapping(value="/artists")
  public ResponseEntity<ArtistDto> postMethodName(@RequestBody ArtistDto artistDto) {
    ArtistDto result = artistService.save(artistDto);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
