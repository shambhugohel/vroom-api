package com.carportal.service.impl;

import com.carportal.ai.service.impl.AiCarEnrichmentService;
import com.carportal.dto.CarDetailsDTO;
import com.carportal.entity.ECarDetails;
import com.carportal.repository.CarDetailRepository;
import com.carportal.service.CarDetailService;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CarDetailServiceImpl implements CarDetailService {

  private final CarDetailRepository carDetailRepository;
  private final AiCarEnrichmentService aiCarEnrichmentService;

  // @Override
  // public ECarDetails saveCarDetail(CarDetailsDTO detailsDTO) {
  // // get details from dto and create model Cardetailsmodel
  // // check user role delaler, buyer, seller, agent, inspection,
  // // car valuation
  // // car inspection
  // // Service history fetch
  // // Accident history check
  // return null;
  // }

  @Override
  public ECarDetails saveCarDetail(ECarDetails carDetails) {
    // Generate Business ID
    String prefix = carDetails.getClass().getSimpleName().length() >= 3
        ? carDetails.getClass().getSimpleName().substring(0, 3).toUpperCase()
        : "ENT";
    String timestamp = java.time.LocalDateTime.now()
        .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    long random = (long) (Math.random() * 1000);
    String businessId = prefix + timestamp + String.format("%03d", random);

    carDetails.assignBusinessId(businessId);

    // Logic to check if user is paid (mocked for now)
    boolean isPaidUser = true;

    if (isPaidUser) {
      // Create a DTO from the entity to pass to the enrichment service
      // In a real app, we might have the DTO available or use a mapper.
      // Here we manually construct a minimal DTO for the AI service.
      var dto = new CarDetailsDTO();
      dto.setCarManufacturer(carDetails.getCarManufacturer());
      dto.setCarModel(carDetails.getCarModel());
      dto.setManufacturedYear(carDetails.getManufacturedYear());
      dto.setDescription(carDetails.getDescription());

      var enrichedDetails = aiCarEnrichmentService.enrichCarDetails(dto);
      if (enrichedDetails != null) {
        carDetails.enrichWith(enrichedDetails.getECarEngine(), enrichedDetails.getECarFeatures(),
            enrichedDetails.getECarOuter());
      }
    }

    return carDetailRepository.save(carDetails);
  }

  @Override
  public Collection<ECarDetails> findAll() {
    return carDetailRepository.findAll();
  }

  @Override
  public Optional<ECarDetails> findById(Long carDetailId) {
    return carDetailRepository.findById(carDetailId);
  }

  @Override
  public Optional<ECarDetails> updateById(ECarDetails ECarDetails, Long carDetailId) {
    Optional<ECarDetails> optionalCarDetails = Optional.of(ECarDetails);
    optionalCarDetails.ifPresent(car -> {
      carDetailRepository.save(ECarDetails);
    });
    return Optional.of(ECarDetails);
  }

  @Override
  public void deleteById(Long carDetailId) {
    if (carDetailRepository.findById(carDetailId).isPresent()) {
      carDetailRepository.deleteById(carDetailId);
    }
  }

}
