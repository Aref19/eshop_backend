
CREATE TABLE Provider
(
    id      char(36)     NOT NULL,
    name    VARCHAR(255) NULL,
    webLink VARCHAR(255) NULL,
    CONSTRAINT pk_provider PRIMARY KEY (id)
);

CREATE TABLE Item
(
    id          char(36)     NOT NULL,
    price       VARCHAR(255) NULL,
    title       VARCHAR(255) NULL,
    des         VARCHAR(255) NULL,
    Provider_Id char(36)     NULL,
    CONSTRAINT pk_item PRIMARY KEY (id)
);

ALTER TABLE Item
    ADD CONSTRAINT FK_ITEM_ON_PROVIDER FOREIGN KEY (Provider_Id) REFERENCES Provider (id);
CREATE TABLE Image
(
    id      char(36)     NOT NULL,
    url     VARCHAR(255) NULL,
    item_Id char(36)     NULL,
    CONSTRAINT pk_image PRIMARY KEY (id)
);

ALTER TABLE Image
    ADD CONSTRAINT FK_IMAGE_ON_ITEM FOREIGN KEY (item_Id) REFERENCES Item (id);

CREATE TABLE Address
(
    id          char(36)     NOT NULL,
    plz         VARCHAR(255) NULL,
    str         VARCHAR(255) NULL,
    number      VARCHAR(255) NULL,
    Provider_Id char(36)     NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

ALTER TABLE Address
    ADD CONSTRAINT FK_ADDRESS_ON_PROVIDER FOREIGN KEY (Provider_Id) REFERENCES Provider (id);