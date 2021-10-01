package com.nw.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
public class ArtistEntity {
  
  @Id
  private long id;  // 작가 번호

  @Column(nullable = false)
  private String name;  // 작가 이름
}
