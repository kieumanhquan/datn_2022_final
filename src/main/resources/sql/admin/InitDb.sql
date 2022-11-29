create table academic_level
(
    id          number(19, 0) not null,
    code        varchar2(255) not null,
    description varchar2(255) not null,
    is_delete   number(10, 0),
    primary key (id)
);
create table company
(
    id                number(19, 0) not null,
    avatar            varchar2(255) not null,
    backdrop_img      varchar2(255) not null,
    date_incoporation date          not null,
    description       varchar2(255) not null,
    email             varchar2(255) not null,
    head_office       varchar2(255) not null,
    hot_line          varchar2(255) not null,
    is_delete         number(10, 0),
    link_web          varchar2(255) not null,
    name              varchar2(255) not null,
    number_staff      varchar2(255) not null,
    tax_code          varchar2(255) not null,
    tax_date          date          not null,
    tax_place         varchar2(255) not null,
    primary key (id)
);
create table job
(
    id                     number(19, 0) not null,
    address_work           varchar2(255),
    benefits               varchar2(255),
    create_date            date,
    description            varchar2(255),
    due_date               date,
    is_delete              number(10, 0),
    job_requirement        varchar2(255),
    name                   varchar2(255),
    number_experience      number(10, 0),
    qty_person             number(10, 0),
    reason                 varchar2(255),
    salary_max             number(10, 0),
    salary_min             number(10, 0),
    skills                 varchar2(255),
    start_recruitment_date date,
    update_date            date,
    views                  number(10, 0),
    academic_level_id      number(19, 0),
    contact_id             number(19, 0),
    create_id              number(19, 0),
    job_position_id        number(19, 0),
    rank_id                number(19, 0),
    status_id              number(19, 0),
    update_id              number(19, 0),
    working_form_id        number(19, 0),
    primary key (id)
);
create table job_position
(
    id          number(19, 0) not null,
    code        varchar2(255),
    description varchar2(255),
    is_delete   number(10, 0),
    primary key (id)
);
create table job_register
(
    id                number(19, 0) not null,
    address_interview varchar2(255),
    cv_file           varchar2(255),
    date_interview    date,
    date_register     date,
    is_delete         number(10, 0),
    media_type        varchar2(255),
    method_interview  varchar2(255),
    reason            varchar2(255),
    job_id            number(19, 0),
    status_id         number(19, 0),
    user_id           number(19, 0),
    primary key (id)
);
create table notifications
(
    id          number(19, 0) not null,
    content     varchar2(255),
    create_date date,
    is_delete   number(10, 0),
    job_id      number(19, 0),
    receiver_id number(19, 0),
    sender_id   number(19, 0),
    type_id     number(19, 0),
    primary key (id)
);
create table otp
(
    id       number(19, 0) not null,
    code     varchar2(255),
    issue_at number(19, 0),
    user_id  number(19, 0),
    primary key (id)
);
create table permisstion
(
    user_id number(19, 0) not null,
    role_id number(19, 0) not null,
    primary key (user_id, role_id)
);
create table profiles
(
    id                      number(19, 0) not null,
    description             varchar2(255),
    desired_salary          varchar2(255),
    desired_working_address varchar2(255),
    desired_working_form    varchar2(255),
    is_delete               number(10, 0),
    number_years_experience number(10, 0),
    skill                   varchar2(255),
    academic_name_id        number(19, 0),
    user_id                 number(19, 0),
    primary key (id)
);
create table rank
(
    id          number(19, 0) not null,
    code        varchar2(255),
    description varchar2(255),
    is_delete   number(10, 0),
    primary key (id)
);
create table roles
(
    id          number(19, 0) not null,
    code        varchar2(255) not null,
    description varchar2(255),
    is_delete   number(10, 0),
    primary key (id)
);
create table status_job
(
    id          number(19, 0) not null,
    code        varchar2(255),
    description varchar2(255),
    is_delete   number(10, 0),
    primary key (id)
);
create table status_job_register
(
    id          number(19, 0) not null,
    code        varchar2(255) not null,
    description varchar2(255) not null,
    is_delete   number(10, 0) not null,
    primary key (id)
);
create table type
(
    id          number(19, 0) not null,
    code        varchar2(255),
    description varchar2(255),
    is_delete   number(10, 0),
    primary key (id)
);
create table users
(
    id               number(19, 0) not null,
    avatar           varchar2(255),
    birth_day        date,
    email            varchar2(255),
    first_time_login number(10, 0),
    gender           varchar2(255),
    home_town        varchar2(255),
    activate         number(10, 0),
    is_delete        number(10, 0),
    name             varchar2(255),
    password         varchar2(255),
    phone_number     varchar2(255),
    user_name        varchar2(255),
    primary key (id)
);
create table working_form
(
    id          number(19, 0) not null,
    code        varchar2(255),
    description varchar2(255),
    is_delete   number(10, 0),
    primary key (id)
);
alter table profiles
    add constraint UK_4ixsj6aqve5pxrbw2u0oyk8bb unique (user_id);
alter table users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table users
    add constraint UK_9q63snka3mdh91as4io72espi unique (phone_number);
alter table users
    add constraint UK_k8d0f2n7n88w1a16yhua64onx unique (user_name);
alter table job
    add constraint FKmrgx5iva5g1nm9drpsrl3uydl foreign key (academic_level_id) references academic_level;
alter table job
    add constraint FKiwox7t2e8hbgvc4vxeurygub8 foreign key (contact_id) references users;
alter table job
    add constraint FK6rghrmgpwrfg5rdeettpab59b foreign key (create_id) references users;
alter table job
    add constraint FKf0j8298v9w6jcb9kkg4n66ufj foreign key (job_position_id) references job_position;
alter table job
    add constraint FK5n2xsv9pwsq5cgl6of5c2tmqf foreign key (rank_id) references rank;
alter table job
    add constraint FKbp5v98xk04hprbiyt6jh2fks6 foreign key (status_id) references status_job;
alter table job
    add constraint FKikjj77e7k7n499jv0hvss3fqn foreign key (update_id) references users;
alter table job
    add constraint FKsofk77eqos8x199g5nk2ylar0 foreign key (working_form_id) references working_form;
alter table job_register
    add constraint FKk4n5poom9lamka2xsp72beb9h foreign key (job_id) references job;
alter table job_register
    add constraint FK10q8i7n4ybbptiveqgua9mod3 foreign key (status_id) references status_job_register;
alter table job_register
    add constraint FKacqy9y02gjd52dqv1y6lc6svl foreign key (user_id) references users;
alter table notifications
    add constraint FK80ghl3ldtb72ttlx7ckvyj2ll foreign key (job_id) references job;
alter table notifications
    add constraint FK9kxl0whvhifo6gw4tjq36v53k foreign key (receiver_id) references users;
alter table notifications
    add constraint FK13vcnq3ukas06ho1yrbc5lrb5 foreign key (sender_id) references users;
alter table notifications
    add constraint FKnxx1sd80dofb75y9nc4y8xt0q foreign key (type_id) references type;
alter table otp
    add constraint FKs0hlsjury48cekfbfusk11lyr foreign key (user_id) references users;
alter table permisstion
    add constraint FKteegpcc8jlt8r47rl8hiar7q7 foreign key (role_id) references roles;
alter table permisstion
    add constraint FKcpvk8v0t50pafgmhuabyhi6ob foreign key (user_id) references users;
alter table profiles
    add constraint FKiwx5j9ach61i3ydqqt77yynae foreign key (academic_name_id) references academic_level;
alter table profiles
    add constraint FK410q61iev7klncmpqfuo85ivh foreign key (user_id) references users