DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (
  first_name text,
  last_name text,
  own_id text PRIMARY KEY,
  password text
);
INSERT INTO user (first_name, last_name, own_id, password) VALUES ("James", "Harden", "harden.james@gmail.com", "11111"), ("Kyre", "Irving", "kyre.irving@gmail.com", "22222"), ("Chris", "Paul", "chris.paul@gmail.com", "33333"), ("Lebron", "James", "lebron.james@gmail.com", "44444");

DROP TABLE IF EXISTS vehicle;
CREATE TABLE IF NOT EXISTS vehicle (
  owner_id text,
  type text,
  plate_number text PRIMARY KEY,
  make text,
  model text,
  fuel text,
  manufacture_date text,
  FOREIGN KEY (owner_id) REFERENCES user(own_id)
);

INSERT INTO vehicle (owner_id, plate_number, type, make, model, fuel, manufacture_date) VALUES ("harden.james@gmail.com", "JH1313", "MA", "BMW", "Z4", "petrol", "1/1/2014"), ("chris.paul@gmail.com", "CP3333", "MC", "JEEP", "Cherokee", "petrol", "1/1/2015"), ("chris.paul@gmail.com", "TRA111", "T", "TITAN", "Spare Wheel", "other", "1/1/2016");