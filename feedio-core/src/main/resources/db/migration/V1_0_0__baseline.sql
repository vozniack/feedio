/* Schema */

CREATE TABLE users
(
    id            UUID         NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),

    email         VARCHAR(255) NOT NULL UNIQUE,
    password      VARCHAR(255),

    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    language      VARCHAR(255) NOT NULL             DEFAULT 'en_EN',

    active        BOOLEAN      NOT NULL             DEFAULT FALSE,

    created_at    TIMESTAMP    NOT NULL             DEFAULT now(),
    updated_at    TIMESTAMP,
    verified_at   TIMESTAMP,
    deleted_at    TIMESTAMP
);
