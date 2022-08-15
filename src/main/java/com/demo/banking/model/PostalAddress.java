package com.demo.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostalAddress {

  @JsonProperty("AddressLine")
  private List<String> addressLine;

  @JsonProperty("StreetName")
  private String streetName;

  @JsonProperty("TownName")
  private String townName;

  @JsonProperty("CountrySubDivision")
  private List<String> countrySubDivision;

  @JsonProperty("Country")
  private String country;

  @JsonProperty("PostCode")
  private String postCode;
}
