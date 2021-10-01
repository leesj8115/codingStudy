package com.nw.service;

import java.util.List;
import java.util.stream.Collectors;

import com.nw.domain.dto.ArtistDto;
import com.nw.domain.repository.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

  private final ArtistRepository repository;

  @Autowired
  public ArtistServiceImpl(ArtistRepository repository) {
    this.repository = repository;
  }


  @Override
  public List<ArtistDto> findAll() {
    List<ArtistDto> result = repository.findAll().stream().map(entity -> new ArtistDto(entity)).collect(Collectors.toList());

    return result;
  }


  @Override
  public ArtistDto save(ArtistDto artistDto) {
    ArtistDto result = new ArtistDto(repository.save(artistDto.toEntity()));
    
    return result;
  }
}
