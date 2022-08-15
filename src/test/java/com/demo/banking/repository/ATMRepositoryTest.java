package com.demo.banking.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.demo.banking.exception.DataAccessException;
import com.demo.banking.model.ATMDetailsResponse;
import com.demo.banking.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ATMRepositoryTest {

  @InjectMocks ATMRepository atmRepository;
  @Mock private RestTemplate restTemplate;

  @BeforeEach
  public void setup() {
    ReflectionTestUtils.setField(atmRepository, "url", "http://localhost:8080/api/atms");
  }

  @Test
  void getATMSTest() {

    ATMDetailsResponse atmDetailsResponse = TestUtils.createATMDetailsResponse();

    Mockito.when(
            restTemplate.getForObject("http://localhost:8080/api/atms", ATMDetailsResponse.class))
        .thenReturn(atmDetailsResponse);

    ATMDetailsResponse result = atmRepository.getATMS();
    assertEquals(result, atmDetailsResponse);
  }

  @Test
  void getATMSThrowsDataAccessExceptionTest() {

    Mockito.when(
            restTemplate.getForObject("http://localhost:8080/api/atms", ATMDetailsResponse.class))
        .thenThrow(RuntimeException.class);
    DataAccessException ex =
        assertThrows(
            DataAccessException.class,
            () -> {
              atmRepository.getATMS();
            });
    assertEquals("Unable to retrieve data from open API", ex.getMessage());
  }
}
