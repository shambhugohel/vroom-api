package com.carzhub.shared.exceptions.common;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Base class for sub-errors in API responses (e.g., validation errors).
 */
@Schema(description = "Represents a sub-error in API error responses")
public abstract class ApiSubError {

}
