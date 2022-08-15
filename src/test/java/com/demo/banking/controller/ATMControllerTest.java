package com.demo.banking.controller;

import static com.demo.banking.utils.TestUtils.atNonNullException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.demo.banking.model.ATM;
import com.demo.banking.service.ATMService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class ATMControllerTest {

  ATMService atmService = mock(ATMService.class);
  @InjectMocks ATMController atmController = new ATMController(atmService);

  @Test
  void shouldFailToInitializeATMController() {
    NullPointerException ex =
        assertThrows(
            NullPointerException.class,
            () -> {
              new ATMController(null);
            });
    assertEquals(atNonNullException("atmService").getMessage(), ex.getMessage());
  }

  @Test
  void shouldFailToInvokeATMService() {
    NullPointerException ex =
        assertThrows(
            NullPointerException.class,
            () -> {
              atmController.getATMByIdentifier(null);
            });
    assertEquals(atNonNullException("identification").getMessage(), ex.getMessage());
  }

  @Test
  void getATMSTest() {

    ATM atm =
        ATM.builder().identification("identification").supportedCurrencies(List.of("GBP")).build();

    when(atmService.getATMByIdentification("identification")).thenReturn(List.of(atm));

    var result = atmController.getATMByIdentifier("identification");
    assertThat(result).isNotEmpty();
    assertEquals(result, List.of(atm));
  }
}
