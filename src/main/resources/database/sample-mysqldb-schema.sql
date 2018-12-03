--
--    Copyright 2010-2013 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

create table user (
    user_seq int not null AUTO_INCREMENT,
    id varchar(80) null,
    pw varchar(2) not null,
    name varchar(80) null,
    mod_date varchar(80) null,
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    del_yn varchar(80) null,
    constraint pk_user primary key (user_seq)
);

create table board (
    board_seq int not null AUTO_INCREMENT,
    type varchar(100) not null default 'NORMAL',
    title varchar(100) null,
    content varchar(255) not null,
    name varchar(50) null,
    cnt int not null,
    reg_seq int not null,
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    del_yn varchar(5) not null DEFAULT 'F',
    reply_count int not null,
    constraint pk_board primary key (board_seq)
);

create table board_reply (
    board_reply_seq int not null AUTO_INCREMENT,
    board_seq int not null,
    reg_seq int not null,
    reply_content varchar(255) null,
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    board_type varchar(50) not null default 'NORMAL',
    reg_name varchar(50) not null,
    group_seq int null,
    reparent int null,
    redepth int null,
    reorder int null,
    del_yn varchar(5) not null DEFAULT 'F',
    constraint pk_boardReply primary key (board_reply_seq)
);

create table parsing_board (
    parsing_board_seq int not null AUTO_INCREMENT,
    ranking varchar(50) null,
    name varchar(100) null,
    tear varchar(100) null,
    lp varchar(100) null,
    lv varchar(100) null,
    shift varchar(100) null,
    constraint pk_parsingBoard primary key (parsing_board_seq)
);