package com.carportal.api.rest.controller;

import static com.carportal.utils.URLConstant.MappingConstant.ROOTURL;

import com.carportal.dto.common.PageRequestDTO;
import com.carportal.dto.common.PagedResponse;
import com.carportal.entity.ECarDetails;
import com.carportal.service.CarDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ROOTURL)
public class CarDetailController {

  private final CarDetailService service;


  @GetMapping
  public PagedResponse<ECarDetails> getCars(@ModelAttribute PageRequestDTO request) {
//    var page = repository.findAll(request.toPageable());
    return PagedResponse.fromPage(null);
  }

}
