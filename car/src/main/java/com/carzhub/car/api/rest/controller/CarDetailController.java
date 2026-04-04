package com.carzhub.car.api.rest.controller;

import static com.carzhub.shared.utils.URLConstant.MappingConstant.ROOTURL;

import com.carzhub.car.dto.common.PageRequestDTO;
import com.carzhub.car.dto.common.PagedResponse;
import com.carzhub.car.entity.ECarDetails;
import com.carzhub.car.service.CarDetailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    List<ECarDetails> cars = List.copyOf(service.findAll());
    Page<ECarDetails> page = new PageImpl<>(cars, request.toPageable(), cars.size());
    return PagedResponse.fromPage(page);
  }

}
