--liquibase formatted sql

--achangeset liquibase-demo-service:add-user-name-constraint
--ALTER TABLE user_details ADD CONSTRAINT user_details_username_key UNIQUE (username);

--changeset liquibase-demo-service:create-table-suggestion
CREATE TABLE Suggestion (
    name VARCHAR,
    latitude FLOAT,
    longitude FLOAT,
    score FLOAT
);
--changeset liquibase-demo-service:add-test-data
insert into Suggestion(name,latitude,longitude) values('London, ON, Canada',42.98339,-81.23304);
insert into Suggestion(name,latitude,longitude) values('London, OH, USA',39.88645,-83.44825);
insert into Suggestion(name,latitude,longitude) values('Londontowne, MD, USA',38.93345,-76.54941);
insert into Suggestion(name,latitude,longitude) values('London, KY, USA',37.12898,-84.0832);
insert into Suggestion(name,latitude,longitude) values('Abbotsford',49.05798,-122.25257);


