package com.demo.banking.utils;

import com.demo.banking.model.ATM;
import com.demo.banking.model.ATMData;
import com.demo.banking.model.ATMDetailsResponse;
import com.demo.banking.model.Brand;
import com.demo.banking.model.Location;
import com.demo.banking.model.Meta;
import com.demo.banking.model.PostalAddress;
import java.util.List;

public class TestUtils {

  public static NullPointerException atNonNullException(String s) {
    return new NullPointerException(s + " is marked non-null but is null");
  }

  public static PostalAddress createPostalAddress() {
    return PostalAddress.builder()
        .addressLine(List.of("32 THE GOSFORTH CENTRE; GOSFORTH"))
        .postCode("NE3 1JZ")
        .country("GB")
        .townName("NEWCASTLE UPON TYNE")
        .countrySubDivision(List.of("TYNE AND WEAR"))
        .streetName("32 THE GOSFORTH CENTRE")
        .build();
  }

  public static ATMDetailsResponse createATMDetailsResponse() {

    ATMDetailsResponse atmDetailsResponse = new ATMDetailsResponse();

    List<ATM> atms =
        List.of(
            ATM.builder()
                .identification("LFDCDC11")
                .location(Location.builder().postalAddress(TestUtils.createPostalAddress()).build())
                .supportedCurrencies(List.of("GBP"))
                .build());

    ATMData atmData = new ATMData();
    atmData.setBrands(List.of(Brand.builder().brandName("brand").atms(atms).build()));
    atmDetailsResponse.setData(List.of(atmData));
    atmDetailsResponse.setMeta(
        Meta.builder().agreement("agreement").lastUpdated("lastupdated").build());

    return atmDetailsResponse;
  }
}
