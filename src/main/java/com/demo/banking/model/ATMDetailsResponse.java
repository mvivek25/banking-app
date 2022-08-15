package com.demo.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ATMDetailsResponse {

  @JsonProperty("meta")
  private Meta meta;

  @JsonProperty("data")
  private List<ATMData> data;
}
