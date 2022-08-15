package com.demo.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ATM {

  @JsonProperty("Identification")
  private String identification;

  @JsonProperty("SupportedCurrencies")
  private List<String> supportedCurrencies;

  @JsonProperty("Location")
  private Location location;
}
