# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table citizen (
  id                        bigint not null,
  name                      varchar(255) not null,
  first_name                varchar(255) not null,
  city_id                   bigint,
  constraint pk_citizen primary key (id))
;

create table city (
  id                        bigint not null,
  name                      varchar(255) not null,
  constraint pk_city primary key (id))
;

create sequence citizen_seq;

create sequence city_seq;

alter table citizen add constraint fk_citizen_city_1 foreign key (city_id) references city (id) on delete restrict on update restrict;
create index ix_citizen_city_1 on citizen (city_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists citizen;

drop table if exists city;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists citizen_seq;

drop sequence if exists city_seq;

