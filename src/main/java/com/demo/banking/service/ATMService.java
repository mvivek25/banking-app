package com.demo.banking.service;

import com.demo.banking.model.ATM;
import java.util.List;

/** ATM Service to get the list of ATMS */
public interface ATMService {

  /**
   * Retrieves the list of ATMS for the given identification
   *
   * @param identification the identification
   * @return the list of ATMS
   */
  List<ATM> getATMByIdentification(String identification);
}
