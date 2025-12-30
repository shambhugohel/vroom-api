package com.carportal.dto.common;

import com.carportal.utils.PaginationUtils;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Pageable;

public record PageRequestDTO(
    @Parameter(description = "Page number (0-based)") Integer page,
    @Parameter(description = "Page size") Integer size,
    @Parameter(description = "Sort field") String sort,
    @Parameter(description = "Sort direction: ASC or DESC") String direction
) {

  public Pageable toPageable() {
    return PaginationUtils.createPageable(page, size, sort, direction);
  }
}
