package com.demo.banking.service;

import com.demo.banking.errorcodes.ErrorCodes;
import com.demo.banking.exception.InvalidResponseException;
import com.demo.banking.exception.ServiceException;
import com.demo.banking.model.ATM;
import com.demo.banking.model.ATMData;
import com.demo.banking.model.ATMDetailsResponse;
import com.demo.banking.repository.ATMRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/** ATM Locator Service to get the list of ATMS */
@Slf4j
@Service
@AllArgsConstructor
public class ATMLocatorService implements ATMService {

  @NonNull private final ATMRepository atmRepository;

  /**
   * Retrieves the list of ATMS for the given identification
   *
   * @param identification the identification
   * @return the list of ATMS
   */
  @Override
  public List<ATM> getATMByIdentification(@NonNull String identification) {
    ATMDetailsResponse atms = atmRepository.getATMS();
    List<ATMData> atmData =
        Optional.ofNullable(atms.getData())
            .orElseThrow(
                () -> new InvalidResponseException(ErrorCodes.INVALID_RESPONSE.getDescription()));
    List<ATM> atmsList = getAtms(atmData, identification);
    if (CollectionUtils.isEmpty(atmsList)) {
      throw new ServiceException(ErrorCodes.NO_ATMS_DATA.getDescription());
    }
    log.info("ATMS List received for given identification:{}", atmsList);
    return atmsList;
  }

  private List<ATM> getAtms(List<ATMData> atmData, String identification) {
    return atmData.stream()
        .flatMap(brand -> brand.getBrands().stream())
        .flatMap(data -> data.getAtms().stream())
        .filter(atm -> atm.getIdentification().equals(identification))
        .collect(Collectors.toList());
  }
}
