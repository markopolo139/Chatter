create table user_info(
                          user_id int not null primary key auto_increment,
                          login varchar(100) not null,
                          password varchar(150) not null,
                          email varchar(100) not null,
                          password_token varchar(100) null,
                          enabled boolean not null,
                          photo MEDIUMBLOB
);

create table user_friends(
                          user_id int not null,
                          friend_id int not null,
                          constraint user_to_user foreign key (user_id) references user_info (user_id),
                          constraint friend_to_user foreign key (friend_id) references user_info (user_id)
);

create table user_friends_request(
                          user_id int not null,
                          friend_id int not null,
                          constraint user_to_user foreign key (user_id) references user_info (user_id),
                          constraint friend_to_user foreign key (friend_id) references user_info (user_id)
);

create table user_groups(
                            group_id int not null primary key auto_increment,
                            group_name String not null,
                            admin_id int not null,
                            constraint user_to_user foreign key (admin_id) references user_info (user_id),
);

create table group_users(
                          group_id int not null,
                          friend_id int not null,
                          constraint friend_to_user foreign key (friend_id) references user_groups (group_id)
);

create table user_roles(
                           user_id int not null,
                           `role` varchar(50) not null,
                           constraint role_to_user foreign key (user_id) references user_info (user_id)
);