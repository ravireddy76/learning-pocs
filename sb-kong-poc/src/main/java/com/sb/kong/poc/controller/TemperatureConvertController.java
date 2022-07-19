package com.sb.kong.poc.controller;

import com.sb.kong.poc.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping(value = "/sbk-service/v1/temperature", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/sbk-service/v1/temperature", description = "Temperature convert Service")
@Slf4j
public class TemperatureConvertController {

    /**
     * Method to convert celsius to fahrenheit.
     *
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Convert to fahrenheit")
    @RequestMapping(value = "/fahrenheit/{celsius}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> toFahrenheit(@PathVariable String celsius) {
        try {
            log.info("Inside convert to fahrenheit");

            double fahrenheit = 0;
            if (Optional.ofNullable(celsius).isPresent()) {
                fahrenheit = (9 * Double.parseDouble(celsius) + (32 * 5)) / 5;
            }

            /* Building as response message object.*/
            Map<String, String> response = new HashMap<>();
            response.put("iv_celsius", celsius);
            response.put("ov_fahrenheit", String.valueOf(fahrenheit));
            response.put("scale", "Celsius(C) to Fahrenheit(F)");

            /* Converting response object to JSON string. */
            String responseMessage = JsonUtil.serializeJson(response);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to convert celsius to fahrenheit exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to convert celsius to fahrenheit.
     *
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Convert to Celsius")
    @RequestMapping(value = "/celsius/{fahrenheit}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> toCelsius(@PathVariable String fahrenheit) {
        try {
            log.info("Inside convert fahrenheit to celsius");

            double celsius = 0;
            if (Optional.ofNullable(fahrenheit).isPresent()) {
                celsius = ((5 * (Double.parseDouble(fahrenheit) - 32.0)) / 9.0);
            }
            /* Building as response message object.*/
            Map<String, String> response = new HashMap<>();
            response.put("iv_fahrenheit", fahrenheit);
            response.put("ov_celsius", String.valueOf(celsius));
            response.put("scale", "Fahrenheit(F) to Celsius(C)");

            /* Converting response object to JSON string. */
            String responseMessage = JsonUtil.serializeJson(response);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to convert fahrenheit to celsius exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
