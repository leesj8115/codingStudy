package com.nw.domain.entity;

import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@Entity
public class WebtoonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;  // 웹툰 id

  @ManyToOne(fetch = FetchType.EAGER)  // 단방향. 기본값 EAGER 그대로 사용
  @JoinColumn(name = "story_id")
  private ArtistEntity story_artist;  // 스토리 작가

  @ManyToOne(fetch = FetchType.EAGER)  // 단방향. 기본값 EAGER 그대로 사용
  @JoinColumn(name = "draw_id")
  private ArtistEntity draw_artist;  // 그림 작가 id

  @Column(nullable = false)
  private String title;     // 제목

  @Column(nullable = false)
  private DayOfWeek update_day;  // 업데이트 요일 (1 = 월요일)

  @Column(nullable = false)
  private boolean finished; // 완결 여부

  @Column(nullable = false)
  private long score; // 인기도(하트)

  @Column(nullable = false)
  private String img_title; // 작품 소개 이미지 (경로만)

  @Column(nullable = false)
  private String genre; // 장르

  @Column(nullable = false)
  private String slogan;  // 슬로건

}