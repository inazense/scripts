-- Recreate the schema
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
​
-- Restore default permissions
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;