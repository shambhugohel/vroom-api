package com.carzhub.car.service.impl;

import com.carzhub.shared.ai.AiEnrichmentService;
import com.carzhub.shared.ai.dto.EnrichmentRequest;
import com.carzhub.shared.ai.dto.EnrichmentResult;
import com.carzhub.car.dto.CarDetailsDTO;
import com.carzhub.car.entity.ECarDetails;
import com.carzhub.car.repository.CarDetailRepository;
import com.carzhub.car.service.CarDetailService;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CarDetailServiceImpl implements CarDetailService {

  private final CarDetailRepository carDetailRepository;
  private final AiEnrichmentService aiEnrichmentService;
  private final org.springframework.context.ApplicationEventPublisher eventPublisher;

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
      // Create a request DTO for the shared interface
      var request = new EnrichmentRequest(
          carDetails.getCarManufacturer(),
          carDetails.getCarModel(),
          carDetails.getManufacturedYear(),
          carDetails.getDescription());

      var result = aiEnrichmentService.enrichCarDetails(request);

      if (result != null) {
        // Map result back to entities using a helper or mapper
        // Assuming result.engine(), result.features(), result.outer() are models.
        // We need to convert Models to Entities.
        // Since ECarEngine etc are entities, we might need new instances or mappers.
        // For now, let's create simple mapper methods or assume mappers exist.
        // But ECarEngine.java etc are in this module.
        // We can create them here.

        com.carzhub.car.entity.ECarEngine eEngine = mapToEntity(result.engine());
        com.carzhub.car.entity.ECarFeatures eFeatures = mapToEntity(result.features());
        com.carzhub.car.entity.ECarOuter eOuter = mapToEntity(result.outer());

        carDetails.enrichWith(eEngine, eFeatures, eOuter);
      }
    }

    ECarDetails savedCar = carDetailRepository.save(carDetails);

    try {
      eventPublisher.publishEvent(new com.carzhub.events.CarCreatedEvent(
          savedCar.getId(),
          savedCar.getCarManufacturer(),
          savedCar.getCarModel(),
          savedCar.getManufacturedYear()));
    } catch (Exception e) {
      // Log but don't fail the transaction just for search indexing?
      // Or should we fail? For now, let's log.
      // Actually, Spring Events are synchronous by default in the same transaction
      // context usually unless @Async.
      // Modulith handles this well.
      // Let's just publish.
    }

    return savedCar;
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

  // Helper methods to map Models to Entities
  private com.carzhub.car.entity.ECarEngine mapToEntity(com.carzhub.shared.model.CarEngineModel model) {
    if (model == null)
      return null;
    var entity = com.carzhub.car.entity.ECarEngine.newInstanceForCreation();
    BeanUtils.copyProperties(model, entity);
    return entity;
  }

  private com.carzhub.car.entity.ECarFeatures mapToEntity(com.carzhub.shared.model.CarFeaturesModel model) {
    if (model == null)
      return null;
    var entity = com.carzhub.car.entity.ECarFeatures.newInstanceForCreation();
    BeanUtils.copyProperties(model, entity);
    return entity;
  }

  private com.carzhub.car.entity.ECarOuter mapToEntity(com.carzhub.shared.model.CarOuterModel model) {
    if (model == null)
      return null;
    var entity = com.carzhub.car.entity.ECarOuter.newInstanceForCreation();
    BeanUtils.copyProperties(model, entity);
    return entity;
  }

}
