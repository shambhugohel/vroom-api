package com.carzhub.car.api.rest.controller;

import static com.carzhub.shared.utils.URLConstant.MappingConstant.ROOTURL;

import com.carzhub.car.entity.ECarOuter;
import com.carzhub.shared.exceptions.ServiceException;
import com.carzhub.car.service.CarOuterService;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = ROOTURL)
public class CarOuterController {

  private final static Logger LOGGER = LoggerFactory.getLogger(CarOuterController.class.getName());

  private final CarOuterService carOuterService;

  @Autowired
  public CarOuterController(CarOuterService carOuterService) {
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace("** CarOuterController()");
    }
    this.carOuterService = carOuterService;
  }

  @PostMapping(path = "/cardetails/carouter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ECarOuter> addCarOuter(@RequestBody ECarOuter ECarOuter,
      @RequestParam Long carDetailId)
      throws ServiceException {

    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace(">> addCarOuter()");
    }

    ECarOuter newECarOuter = ECarOuter;
    carOuterService.saveCarOuter(newECarOuter, carDetailId);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{carOuterId}")
        .buildAndExpand(newECarOuter.getId()).toUri();
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace("<< addCarOuter()");
    }

    return ResponseEntity.created(uri).build();
  }

}
