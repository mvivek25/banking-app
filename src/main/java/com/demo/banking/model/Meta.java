package com.demo.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Meta {

  @JsonProperty("LastUpdated")
  private String lastUpdated;

  @JsonProperty("TotalResults")
  private String totalResults;

  @JsonProperty("Agreement")
  private String agreement;

  @JsonProperty("License")
  private String license;

  @JsonProperty("TermsOfUse")
  private String termsOfUse;
}
