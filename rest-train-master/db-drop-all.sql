-- drop all foreign keys
alter table if exists reservation drop constraint if exists fk_reservation_outbound_train_id;
drop index if exists ix_reservation_outbound_train_id;

alter table if exists reservation drop constraint if exists fk_reservation_return_train_id;
drop index if exists ix_reservation_return_train_id;

alter table if exists reservation drop constraint if exists fk_reservation_passenger_id;
drop index if exists ix_reservation_passenger_id;

alter table if exists reservation drop constraint if exists fk_reservation_seat_id;

alter table if exists seat drop constraint if exists fk_seat_train_id;
drop index if exists ix_seat_train_id;

alter table if exists seat drop constraint if exists fk_seat_reservation_id;

-- drop all
drop table if exists passenger cascade;

drop table if exists reservation cascade;

drop table if exists seat cascade;

drop table if exists train cascade;

