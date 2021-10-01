package com.nw.service;

import java.util.List;

import com.nw.domain.dto.ArtistDto;

public interface ArtistService {

  List<ArtistDto> findAll();

  ArtistDto save(ArtistDto artistDto);
  
}
