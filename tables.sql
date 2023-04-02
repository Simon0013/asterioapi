CREATE TABLE banner (
  id bigint primary key auto_increment,
  name varchar(100),
  banner_text varchar(1000),
  price decimal(8,2),
  deleted bool
);
CREATE TABLE category (
  id bigint primary key auto_increment,
  name varchar(100),
  request_id bigint,
  deleted bool
);
CREATE TABLE banner_in_category (
  id_banner bigint,
  id_category bigint,
  foreign key (id_banner) references banner (id),
  foreign key (id_category) references category (id)
);
CREATE TABLE log_record (
  id bigint primary key auto_increment,
  ip_address varchar(15),
  user_agent varchar(200),
  request_time time,
  id_banner bigint,
  no_content_reason varchar(1000),
  foreign key (id_banner) references banner (id)
);
CREATE PROCEDURE delete_banner (id_banner bigint)
  BEGIN
    UPDATE banner SET deleted = true WHERE id = id_banner;
  END
CREATE PROCEDURE delete_category (id_category bigint)
  BEGIN
    UPDATE category SET deleted = true WHERE id = id_category;
  END
CREATE PROCEDURE show_banners_in_category (id_cat bigint)
  BEGIN
    SELECT banner.id, name, banner_text, price, deleted
      FROM banner INNER JOIN banner_in_category ON banner.id = id_banner
      WHERE id_category = id_cat;
  END
CREATE PROCEDURE show_banner_by_log (id_log_record bigint)
  BEGIN
    SELECT banner.id, name, banner_text, price, deleted
      FROM banner INNER JOIN log_record ON banner.id = id_banner
      WHERE log_record.id = id_log_record;
  END
CREATE PROCEDURE create_banner_of_category (_name varchar(100), _text varchar(1000), _price decimal(8,2), id_cat bigint)
  BEGIN
    INSERT INTO banner (name, banner_text, price, deleted) VALUES (_name, _text, _price, false);
    INSERT INTO banner_in_category VALUES ((SELECT max(id) FROM banner), id_cat);
  END