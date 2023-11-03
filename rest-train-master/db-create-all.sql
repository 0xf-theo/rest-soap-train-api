-- apply changes
create table passenger (
  id                            uuid not null,
  first_name                    varchar(255) not null,
  last_name                     varchar(255) not null,
  email                         varchar(255) not null,
  phone_number                  varchar(255) not null,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  constraint pk_passenger primary key (id)
);

create table reservation (
  id                            uuid not null,
  outbound_train_id             uuid not null,
  return_train_id               uuid,
  passenger_id                  uuid not null,
  seat_id                       uuid not null,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  constraint uq_reservation_seat_id unique (seat_id),
  constraint pk_reservation primary key (id)
);

create table seat (
  id                            uuid not null,
  name                          varchar(255) not null,
  train_id                      uuid not null,
  category                      varchar(13) not null,
  reservation_id                uuid,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  constraint ck_seat_category check ( category in ('FIRST_CLASS','PREMIUM_CLASS','STANDARD')),
  constraint uq_seat_reservation_id unique (reservation_id),
  constraint pk_seat primary key (id)
);

create table train (
  id                            uuid not null,
  departure                     varchar(255) not null,
  arrival                       varchar(255) not null,
  departure_date_time           timestamp not null,
  arrival_date_time             timestamp not null,
  default_price                 float not null,
  created_at                    timestamptz not null,
  updated_at                    timestamptz not null,
  constraint pk_train primary key (id)
);

-- foreign keys and indices
create index ix_reservation_outbound_train_id on reservation (outbound_train_id);
alter table reservation add constraint fk_reservation_outbound_train_id foreign key (outbound_train_id) references train (id) on delete restrict on update restrict;

create index ix_reservation_return_train_id on reservation (return_train_id);
alter table reservation add constraint fk_reservation_return_train_id foreign key (return_train_id) references train (id) on delete restrict on update restrict;

create index ix_reservation_passenger_id on reservation (passenger_id);
alter table reservation add constraint fk_reservation_passenger_id foreign key (passenger_id) references passenger (id) on delete restrict on update restrict;

alter table reservation add constraint fk_reservation_seat_id foreign key (seat_id) references seat (id) on delete restrict on update restrict;

create index ix_seat_train_id on seat (train_id);
alter table seat add constraint fk_seat_train_id foreign key (train_id) references train (id) on delete restrict on update restrict;

alter table seat add constraint fk_seat_reservation_id foreign key (reservation_id) references reservation (id) on delete restrict on update restrict;

