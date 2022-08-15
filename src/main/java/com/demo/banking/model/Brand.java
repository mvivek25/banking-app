package com.demo.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Brand {

  @JsonProperty("BrandName")
  private String brandName;

  @JsonProperty("ATM")
  private List<ATM> atms;
}
