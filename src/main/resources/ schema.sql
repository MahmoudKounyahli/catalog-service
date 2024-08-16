DROP TABLE IF EXISTS part;
CREATE TABLE part (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    partNumber varchar(255) UNIQUE NOT NULL,
    quantity integer NOT NULL,
    price float8 NOT NULL,
    category varchar(255) NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_date timestamp NOT NULL,
    version integer NOT NULL
);