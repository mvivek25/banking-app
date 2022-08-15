package com.demo.banking.service;

import static com.demo.banking.utils.TestUtils.atNonNullException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.demo.banking.exception.InvalidResponseException;
import com.demo.banking.exception.ServiceException;
import com.demo.banking.model.ATM;
import com.demo.banking.model.ATMDetailsResponse;
import com.demo.banking.repository.ATMRepository;
import com.demo.banking.utils.TestUtils;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class ATMServiceTest {

  ATMRepository atmRepository = mock(ATMRepository.class);
  @InjectMocks ATMLocatorService atmLocatorService = new ATMLocatorService(atmRepository);

  @Test
  void shouldFailToInitializeATMService() {
    NullPointerException ex =
        assertThrows(
            NullPointerException.class,
            () -> {
              new ATMLocatorService(null);
            });
    assertEquals(atNonNullException("atmRepository").getMessage(), ex.getMessage());
  }

  @Test
  void getATMByIdentificationTest() {
    ATMDetailsResponse atmDetailsResponse = TestUtils.createATMDetailsResponse();
    when(atmRepository.getATMS()).thenReturn(atmDetailsResponse);

    List<ATM> result = atmLocatorService.getATMByIdentification("LFDCDC11");
    assertThat(result).isNotEmpty();
    assertThat(result.get(0)).isNotNull();
    assertEquals("LFDCDC11", result.get(0).getIdentification());
    assertEquals(result.get(0).getSupportedCurrencies(), List.of("GBP"));
  }

  @Test
  void getATMSThrowsInvalidResponseExceptionTest() {

    when(atmRepository.getATMS()).thenReturn(new ATMDetailsResponse());

    InvalidResponseException ex =
        assertThrows(
            InvalidResponseException.class,
            () -> {
              atmLocatorService.getATMByIdentification("LFDCDC11");
            });

    assertEquals("Invalid Response received", ex.getMessage());
  }

  @Test
  void getATMSThrowsServiceExceptionTest() {
    ATMDetailsResponse atmDetailsResponse = TestUtils.createATMDetailsResponse();

    when(atmRepository.getATMS()).thenReturn(atmDetailsResponse);

    ServiceException ex =
        assertThrows(
            ServiceException.class,
            () -> {
              atmLocatorService.getATMByIdentification("LFDCDC12");
            });

    assertEquals("No Atms Data available", ex.getMessage());
  }
}
