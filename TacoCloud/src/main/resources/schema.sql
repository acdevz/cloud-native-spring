create table if not exists ingredient(
    id varchar(4) not null primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists taco_order(
     id identity primary key,
     delivery_name varchar(25) not null,
     delivery_zip varchar(10) not null,
     upiId varchar(20) not null,
     placed_at timestamp not null
);

create table if not exists taco(
    id identity primary key,
    name varchar(25) not null,
    createdAt timestamp not null,
    taco_order bigint not null,
    foreign key (taco_order) references taco_order(id)
);

create table if not exists ingredient_ref(
    taco bigint not null,
    ingredient varchar(4) not null,
    foreign key (taco) references taco(id),
    foreign key (ingredient) references ingredient(id)
);