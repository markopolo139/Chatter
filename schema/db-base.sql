create table user_info(
                          user_id int  not null primary key auto_increment,
                          login varchar(100) not null,
                          password varchar(150) not null,
                          email varchar(100) not null,
                          password_token varchar(100) null,
                          enabled boolean not null
);

create table user_roles(
                           user_id int not null,
                           `role` varchar(50) not null,
                           constraint role_to_user foreign key (user_id) references user_info (user_id)
);