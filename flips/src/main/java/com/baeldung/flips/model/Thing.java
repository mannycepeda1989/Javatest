package com.baeldung.flips.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Thing {

  private final String name;
  private final int id;

}
