package com.web.hr.recruitment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employers")
@Getter
@Setter
public class Employer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String logo;     // tên file logo
  private String website;  // link website
}
