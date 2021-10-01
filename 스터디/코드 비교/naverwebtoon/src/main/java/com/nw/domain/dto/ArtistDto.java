package com.nw.domain.dto;

import com.nw.domain.entity.ArtistEntity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ArtistDto {
  private long id;
  private String name;

  public ArtistDto(ArtistEntity entity) {
    this.id = entity.getId();
    this.name = entity.getName();
  }

  public ArtistEntity toEntity() {
    return ArtistEntity.builder().id(id).name(name).build();
  }
}
