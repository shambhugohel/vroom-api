package com.carportal.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PaginationUtils {

  public static final int DEFAULT_PAGE = 0;
  public static final int DEFAULT_SIZE = 20;
  public static final int MAX_SIZE = 100;
  public static final String DEFAULT_SORT = "id";
  public static final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.ASC;

  public static Pageable createPageable(Integer page, Integer size, String sort, String direction) {
    int p = (page == null || page < 0) ? DEFAULT_PAGE : page;
    int s = (size == null || size <= 0) ? DEFAULT_SIZE : Math.min(size, MAX_SIZE);
    String sortBy = (sort == null || sort.isBlank()) ? DEFAULT_SORT : sort;
    Sort.Direction dir = (direction == null) ? DEFAULT_DIRECTION
        : Sort.Direction.fromOptionalString(direction).orElse(DEFAULT_DIRECTION);

    return PageRequest.of(p, s, Sort.by(dir, sortBy));
  }

}
