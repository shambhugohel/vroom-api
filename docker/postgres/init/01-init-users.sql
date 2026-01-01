-- Create Roles
CREATE
USER dba_car_portal WITH PASSWORD 'dba_password' CREATEDB;
CREATE
USER app_car_portal WITH PASSWORD 'app_password';

-- Grant Connect
GRANT CONNECT
ON DATABASE carportal_dev TO dba_car_portal;
GRANT CONNECT
ON DATABASE carportal_dev TO app_car_portal;

-- DBA needs to be able to create schemas
GRANT CREATE
ON DATABASE carportal_dev TO dba_car_portal;

-- Set search path for convenience (optional)
ALTER
ROLE dba_car_portal SET search_path TO car_portal, public;
ALTER
ROLE app_car_portal SET search_path TO car_portal, public;
