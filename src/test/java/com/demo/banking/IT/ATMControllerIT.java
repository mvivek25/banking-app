package com.demo.banking.IT;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.demo.banking.controller.ATMController;
import com.demo.banking.model.ATM;
import com.demo.banking.model.PostalAddress;
import com.demo.banking.service.ATMService;
import com.demo.banking.utils.TestUtils;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ATMControllerIT {

  @Autowired private ATMService atmService;

  @Test
  void getATMByIdentifier() {
    var controller = new ATMController(atmService);
    List<ATM> result = controller.getATMByIdentifier("LFFBFC11");
    PostalAddress expectedPostalAddress = TestUtils.createPostalAddress();

    assertThat(result).isNotEmpty();
    assertEquals("LFFBFC11", result.get(0).getIdentification());
    assertEquals(result.get(0).getSupportedCurrencies(), List.of("GBP"));
    assertThat(result.get(0).getLocation()).isNotNull();
    assertThat(result.get(0).getLocation().getPostalAddress()).isEqualTo(expectedPostalAddress);
  }
}
