create table app_users(
                          user_id int not null primary key auto_increment,
                          login varchar(100) not null,
                          password varchar(150) not null,
                          email varchar(100) not null,
                          password_token varchar(100),
                          enabled boolean not null,
                          first_name varchar(100) not null,
                          last_name varchar(150) not null,
                          photo varchar(150)
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

create table users_in_group(
                          group_id int not null,
                          friend_id int not null,
                          constraint group_user_to_user foreign key (friend_id) references app_users (user_id),
                          constraint group_user_to_group foreign key (group_id) references user_groups (group_id)
);

create table user_roles(
                           user_id int not null,
                           `role` varchar(50) not null,
                           constraint role_to_user foreign key (user_id) references app_users (user_id)
);

create table user_message(
                            user_message_id int not null primary key auto_increment,
                            user_from_id int not null,
                            user_to_id int not null,
                            content varchar(300) not null,
                            status enum('READ','DELIVERED','SEND') not null,
                            when_send datetime not null,
                            constraint user_from_to_user foreign key (user_from_id) references app_users (user_id),
                            constraint user_to_to_user foreign key (user_to_id) references app_users (user_id)
);

create table group_message(
                              group_message_id int not null primary key auto_increment,
                              user_from_id int not null,
                              group_to_id int not null,
                              content varchar(300) not null,
                              status enum('READ','DELIVERED','SEND') not null,
                              when_send datetime not null,
                              constraint user_from_to_user2 foreign key (user_from_id) references app_users (user_id),
                              constraint group_to_to_user foreign key (group_to_id) references user_groups (group_id)
);
