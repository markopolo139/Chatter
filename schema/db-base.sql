create table app_users(
                          user_id int not null primary key auto_increment,
                          login varchar(100) not null,
                          password varchar(150) not null,
                          email varchar(100) not null,
                          password_token varchar(100),
                          enabled boolean not null,
                          first_name varchar(100) not null,
                          last_name varchar(150) not null,
                          photo MEDIUMBLOB
);

create table user_friends(
                          user_id int not null,
                          friend_id int not null,
                          constraint user_to_user foreign key (user_id) references app_users (user_id),
                          constraint friend_to_user foreign key (friend_id) references app_users (user_id)
);

create table user_friends_request(
                          user_id int not null,
                          friend_id int not null,
                          constraint user_request_to_user foreign key (user_id) references app_users (user_id),
                          constraint friend_request_to_user foreign key (friend_id) references app_users (user_id)
);

create table user_groups(
                            group_id int not null primary key auto_increment,
                            group_name varchar(200) not null,
                            admin_id int not null,
                            constraint group_admin_to_user foreign key (admin_id) references app_users (user_id)
);

create table group_users(
                          group_id int not null,
                          friend_id int not null,
                          constraint group_user_to_user foreign key (friend_id) references user_groups (group_id)
);

create table user_roles(
                           user_id int not null,
                           `role` varchar(50) not null,
                           constraint role_to_user foreign key (user_id) references app_users (user_id)
);