package com.demo.banking.controller;

import com.demo.banking.model.ATM;
import com.demo.banking.service.ATMService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** This is an ATMController that handles the ATM api endpoints */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class ATMController {

  @Autowired @NonNull private final ATMService atmService;

  /**
   * Retrieves a list of ATMS by identification
   *
   * @param identification the input identification
   * @return list of ATMS for the given identification
   */
  @Operation(
      summary = "Get list of ATMS by identification",
      description = "This endpoint retrieves a list of ATMs based on the identification")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found the list of ATMS",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = ATM.class)))
            }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "404", description = "ATMS not found", content = @Content)
      })
  @GetMapping("/atms/{identification}")
  public List<ATM> getATMByIdentifier(@NonNull @PathVariable String identification) {
    log.info("Input identification: {}", identification);
    return atmService.getATMByIdentification(identification);
  }
}
