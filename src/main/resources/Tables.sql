CREATE TABLE `tbl_car_details`
(
    `id`              bigint       NOT NULL,
    `carManufacturer` varchar(50)  NOT NULL,
    `carModel`        varchar(75)  NOT NULL,
    `description`     varchar(255) NOT NULL,
    `kiloMeterDriven` bigint       NOT NULL,
    `modelYear`       int          NOT NULL,
    `owner`           varchar(255) NOT NULL,
    `price` double NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `tbl_car_engine`
(
    `driveType`        varchar(255) NOT NULL,
    `engineDisplacement` double NOT NULL,
    `engineType`       varchar(255) NOT NULL,
    `fuel`             varchar(255) NOT NULL,
    `maxPower` double NOT NULL,
    `maxTourque` double NOT NULL,
    `mileage` double NOT NULL,
    `numberOfCylinder` int          NOT NULL,
    `transmissionType` varchar(255) NOT NULL,
    `carDetails_id`    bigint       NOT NULL,
    PRIMARY KEY (`carDetails_id`),
    CONSTRAINT `FK8ad38q9r7l9yxog6qqdo5noo` FOREIGN KEY (`carDetails_id`) REFERENCES `tbl_car_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `tbl_car_fetures`
(
    `adjustableSteering`            tinyint(1) DEFAULT '0',
    `airConditioner`                tinyint(1) NOT NULL DEFAULT '0',
    `antiLockBrakingSystem`         tinyint(1) DEFAULT '0',
    `antiTheftAlarm`                tinyint(1) DEFAULT '0',
    `centralLocking`                tinyint(1) DEFAULT '0',
    `childSafetyLock`               tinyint(1) DEFAULT '0',
    `driverAirbags`                 tinyint(1) DEFAULT '0',
    `electricFoldingRearViewMirror` tinyint(1) DEFAULT '0',
    `heater`                        tinyint(1) DEFAULT '0',
    `leatherSeats`                  tinyint(1) DEFAULT '0',
    `leatherSteeringWheel`          tinyint(1) DEFAULT '0',
    `navigationSystem`              tinyint(1) DEFAULT '0',
    `passengerAirbag`               tinyint(1) DEFAULT '0',
    `powerDoorLocks`                tinyint(1) DEFAULT '0',
    `powerWindowsFront`             tinyint(1) DEFAULT '0',
    `powerWindowsRear`              tinyint(1) DEFAULT '0',
    `rearAcVents`                   tinyint(1) DEFAULT '0',
    `rearSeatCentreArmRest`         tinyint(1) DEFAULT '0',
    `rearSeatHeadrest`              tinyint(1) DEFAULT '0',
    `remoteFuelLidOpener`           tinyint(1) DEFAULT '0',
    `remoteTrunkOpener`             tinyint(1) DEFAULT '0',
    `reversingCamera`               tinyint(1) DEFAULT '0',
    `sunRoof`                       tinyint(1) DEFAULT '0',
    `carDetails_id`                 bigint NOT NULL,
    PRIMARY KEY (`carDetails_id`),
    CONSTRAINT `FKbbu8y3x461y2o0nmvcy0hkbyu` FOREIGN KEY (`carDetails_id`) REFERENCES `tbl_car_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `tbl_car_outer`
(
    `bootSpace` double NOT NULL,
    `carBodyType`     varchar(255) NOT NULL,
    `color`           varchar(255) NOT NULL,
    `frontBrakeType`  varchar(255) NOT NULL,
    `fuelTankCapacity` double NOT NULL,
    `groundClearance` double NOT NULL,
    `height` double NOT NULL,
    `length` double NOT NULL,
    `noOfDoors`       int          NOT NULL,
    `rearBrakeType`   varchar(255) NOT NULL,
    `seatingCapacity` int          NOT NULL,
    `width` double NOT NULL,
    `carDetails_id`   bigint       NOT NULL,
    PRIMARY KEY (`carDetails_id`),
    CONSTRAINT `FKf3i8tf2jjqxtdfvykx495vhhw` FOREIGN KEY (`carDetails_id`) REFERENCES `tbl_car_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

