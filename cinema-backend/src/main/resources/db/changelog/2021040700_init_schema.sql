create table movie
(
    id            varchar(255)     not null,
    description   varchar(255),
    director      varchar(255),
    duration_time integer          not null,
    genre         varchar(255),
    image         varchar(255),
    rate          double precision not null,
    rates_number  integer          not null,
    release_date  date,
    title         varchar(255),
    primary key (id)
);
create table place
(
    id             varchar(255) not null,
    is_reserved    bit          not null,
    number         integer      not null,
    reservation_id varchar(255),
    seance_id      varchar(255),
    primary key (id)
);
create table rate
(
    id        varchar(255)     not null,
    user_rate double precision not null,
    movie_id  varchar(255),
    user_id   varchar(255),
    primary key (id)
);
create table reservation
(
    id          varchar(255) not null,
    secret_word varchar(255),
    seance_id   varchar(255),
    user_id     varchar(255),
    primary key (id)
);
create table screening_room
(
    id           varchar(255) not null,
    name         varchar(255),
    place_number integer      not null,
    primary key (id)
);
create table screening_room_places_plan
(
    screening_room_id varchar(255) not null,
    places_plan       varchar(255)
);
create table seance
(
    id                varchar(255) not null,
    start_date        datetime(6),
    movie_id          varchar(255),
    screening_room_id varchar(255),
    primary key (id)
);
create table user
(
    id               varchar(255) not null,
    leading_answer   varchar(255),
    leading_question varchar(255),
    username         varchar(255),
    primary key (id)
);
alter table place
    add constraint FKe3psxojekx01euctc1uyq7glu foreign key (reservation_id) references reservation (id);
alter table place
    add constraint FKi8wr7k7h91jdbfy070nigvapq foreign key (seance_id) references seance (id);
alter table rate
    add constraint FKhr60xnk86b63dsdy7as98fms2 foreign key (movie_id) references movie (id);
alter table rate
    add constraint FKqa3bu60wco5ipgfi8rhmxr6aq foreign key (user_id) references user (id);
alter table reservation
    add constraint FKnlda1yh45srl4l3iybpca77bm foreign key (seance_id) references seance (id);
alter table reservation
    add constraint FKm4oimk0l1757o9pwavorj6ljg foreign key (user_id) references user (id);
alter table screening_room_places_plan
    add constraint FK88a428g1yia8t1o6kviix7an1 foreign key (screening_room_id) references screening_room (id);
alter table seance
    add constraint FKkp2dahvxtxshdlgmotq06uury foreign key (movie_id) references movie (id);
alter table seance
    add constraint FKb2h7oc9ac0ipsohgtnw42tcin foreign key (screening_room_id) references screening_room (id);
