package com.carportal.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {

  public class Db {

    public static final String TBL_SCHEMA_CAR_PORTAL = "car_portal";
    public static final String TBL_CAR_DETAILS = "tbl_car_details";
    public static final String TBL_CAR_ENGINE = "tbl_car_engine";
    public static final String TBL_CAR_FEATURES = "tbl_car_feature";
    public static final String TBL_CAR_OUTER = "tbl_car_outer";
    public static final String TBL_CAR_PHOTOS = "tbl_car_photos";

  }

  public class Sequence {

    public static final String SEQ_GEN_NAME = "seq_gen";
    public static final String CAR_DETAILS_SEQ = "CAR_DETAILS_SEQ";
    public static final String CAR_ENGINE_SEQ = "CAR_ENGINE_SEQ";
    public static final String CAR_FEATURES_SEQ = "CAR_FEATURES_SEQ";
    public static final String CAR_OUTER_SEQ = "CAR_OUTER_SEQ";
    public static final String CAR_PHOTO_SEQ = "CAR_PHOTO_SEQ";

  }

  public class Column {

    // base & audit columns
    public static final String ID = "id";
    public static final String CREATED_AT = "CREATED_AT";
    public static final String CREATED_BY = "CREATED_BY";
    public static final String UPDATED_AT = "UPDATED_AT";
    public static final String UPDATED_BY = "UPDATED_BY";
    public static final String VERSION = "version";
    public static final String IS_ACTIVE = "IS_ACTIVE";
    public static final String DELETED_AT = "DELETED_AT";
    public static final String DELETED_BY = "DELETED_BY";

    // CarDetails
    public static final String CAR_DETAIL_ID = "CAR_DETAIL_ID";
    public static final String CAR_MANUFACTURER = "CAR_MANUFACTURER";
    public static final String CAR_MODEL = "CAR_MODEL";
    public static final String PRICE = "PRICE";
    public static final String MANUFACTURED_YEAR = "MANUFACTURED_YEAR";
    public static final String REGISTRATION_YEAR = "REGISTRATION_YEAR";
    public static final String KILOMETER_DRIVEN = "KILOMETER_DRIVEN";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String OWNER = "OWNER";

    // CarEngine
    public static final String CAR_ENGINE_ID = "CAR_ENGINE_ID";
    public static final String MILEAGE = "OWNER";
    public static final String TRANSMISSION_TYPE = "OWNER";
    public static final String ENGINE_TYPE = "OWNER";
    public static final String ENGINE_DISPLACEMENT = "OWNER";
    public static final String MAX_POWER = "OWNER";
    public static final String MAX_TORQUE = "OWNER";
    public static final String NUMBER_OF_CYLINDER = "OWNER";
    public static final String FUEL_TYPE = "OWNER";
    public static final String DRIVETRAIN = "DRIVETRAIN";

    // CarFeatures
    public static final String CAR_FEATURES_ID = "CAR_FEATURES_ID";
    public static final String AIR_CONDITIONER = "AIR_CONDITIONER";
    public static final String HEATER = "HEATER";
    public static final String SEAT_MATERIAL = "SEAT_MATERIAL";
    public static final String STEERING_WHEEL_MATERIAL = "STEERING_WHEEL_MATERIAL";
    public static final String POWER_WINDOWS = "POWER_WINDOWS";
    public static final String ADJUSTABLE_STEERING = "ADJUSTABLE_STEERING";
    public static final String REMOTE_TRUNK_OPENER = "REMOTE_TRUNK_OPENER";
    public static final String REAR_SEAT_HEADREST = "REAR_SEAT_HEADREST";
    public static final String REAR_SEAT_CENTRE_ARMREST = "REAR_SEAT_CENTRE_ARMREST";
    public static final String REAR_AC_VENTS = "REAR_AC_VENTS";
    public static final String NAVIGATION_SYSTEM = "NAVIGATION_SYSTEM";
    public static final String REVERSING_CAMERA = "REVERSING_CAMERA";
    public static final String REAR_VIEW_MIRROR_TYPE = "REAR_VIEW_MIRROR_TYPE";
    public static final String SUNROOF = "SUNROOF";
    public static final String ANTI_LOCK_BRAKING_SYSTEM = "ANTI_LOCK_BRAKING_SYSTEM";
    public static final String CENTRAL_LOCKING = "CENTRAL_LOCKING";
    public static final String POWER_DOOR_LOCKS = "POWER_DOOR_LOCKS";
    public static final String CHILD_SAFETY_LOCK = "CHILD_SAFETY_LOCK";
    public static final String ANTI_THEFT_ALARM = "ANTI_THEFT_ALARM";
    public static final String AIRBAG_CONFIG = "AIRBAG_CONFIG";

    // CarOuter
    public static final String CAR_OUTER_ID = "CAR_OUTER_ID";
    public static final String CAR_BODY_TYPE = "CAR_BODY_TYPE";
    public static final String COLOR = "COLOR";
    public static final String LENGTH = "LENGTH";
    public static final String HEIGHT = "HEIGHT";
    public static final String WIDTH = "WIDTH";
    public static final String GROUND_CLEARANCE = "GROUND_CLEARANCE";
    public static final String BOOT_SPACE = "BOOT_SPACE";
    public static final String SEATING_CAPACITY = "SEATING_CAPACITY";
    public static final String FUEL_TANK_CAPACITY = "FUEL_TANK_CAPACITY";
    public static final String NO_OF_DOORS = "NO_OF_DOORS";
    public static final String FRONT_BRAKE_TYPE = "FRONT_BRAKE_TYPE";
    public static final String REAR_BRAKE_TYPE = "REAR_BRAKE_TYPE";
    public static final String BUSINESS_ID = "BUSINESS_ID";

    // car photo
    public static final String CAR_PHOTOS_ID = "CAR_PHOTOS_ID";
  }

  public class Index {

    // CarDetails
    public static final String IDX_CAR_DETAILS_BUSINESS_ID = "IDX_CAR_DETAILS_BUSINESS_ID";
    public static final String IDX_CAR_DETAILS_IS_ACTIVE = "idx_car_details_is_active";

    // CarEngine
    public static final String IDX_CAR_ENGINE_BUSINESS_ID = "IDX_CAR_ENGINE_BUSINESS_ID";
    public static final String IDX_CAR_ENGINE_IS_ACTIVE = "idx_car_engine_is_active";

    // CarFeatures
    public static final String IDX_CAR_FEATURES_BUSINESS_ID = "IDX_CAR_FEATURES_BUSINESS_ID";
    public static final String IDX_CAR_FEATURES_IS_ACTIVE = "idx_car_features_is_active";

    // CarOuter
    public static final String IDX_CAR_OUTER_BUSINESS_ID = "IDX_CAR_OUTER_BUSINESS_ID";
    public static final String IDX_CAR_OUTER_IS_ACTIVE = "idx_car_outer_is_active";

    // CarPhoto
    public static final String IDX_CAR_PHOTO_BUSINESS_ID = "IDX_CAR_PHOTO_BUSINESS_ID";
    public static final String IDX_CAR_PHOTO_IS_ACTIVE = "idx_car_photo_is_active";
  }

  public class Exception {

    public static final String ERROR_RES_TYPE_API = "apierror";
    public static final String ERROR_RES_TYPE_PROBLEM = "problemjson";
    public static final String ERR_CAR_NOT_FOUND = "Car with id %s not found";
    public static final String ERR_CAR_ALREADY_EXISTS = "Car with id %s already exists";
    public static final String ERR_INVALID_CAR_DATA = "Invalid car data: %s";
    public static final String ERR_INTERNAL_SERVER = "Internal server error: %s";
  }

  // Bean & Config
  public static final String AUDIT_PROVIDER_NAME = "auditorProvider";
  public static final String PKG_REPOSITORY = "com.carportal.repository";

}
