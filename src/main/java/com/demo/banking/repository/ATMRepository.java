package com.demo.banking.repository;

import com.demo.banking.errorcodes.ErrorCodes;
import com.demo.banking.exception.DataAccessException;
import com.demo.banking.model.ATMDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/** ATM Repository to fetch the list of ATMS from open API */
@Slf4j
@Repository
public class ATMRepository {

  @Autowired RestTemplate restTemplate;
  @Value("${openapi.banking.url}")
  private String url;

  /**
   * Invokes the open API for Lloyds Atms
   *
   * @return atmDetailsResponse the list of all ATMS
   */
  public ATMDetailsResponse getATMS() {
    ATMDetailsResponse atmDetailsResponse;
    try {
      atmDetailsResponse = restTemplate.getForObject(url, ATMDetailsResponse.class);
      log.debug("Response received from openapi: {}", atmDetailsResponse);
    } catch (Exception exception) {
      throw new DataAccessException(ErrorCodes.DATA_ACCESS.getDescription(), exception);
    }
    return atmDetailsResponse;
  }
}
