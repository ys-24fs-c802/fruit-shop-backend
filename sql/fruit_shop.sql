CREATE DATABASE fruits_db
    CHARACTER SET = 'utf8mb4'
    COLLATE = 'utf8mb4_unicode_ci';

use fruits_db;

-- 구매내역 purchases
create table purchases (
                           id int not null auto_increment primary key,
                           item_id int not null comment '품명(FK)',
                           quantity int default 0 comment '수량',
                           spec_id int default 0 comment '규격(FK)',
                           purcahse_at timestamp default current_timestamp() comment '구입일시',
                           supplier_id int default 0 comment '구입처(FK)'
);


-- 테이블 정보 확인
desc purchases;


-- 규격 spec
create table spec (
                      id int not null auto_increment primary key,
                      name varchar(5) unique comment '규격명'
);

-- 구입처 supply
create table supply (
                        id int not null auto_increment primary key,
                        name varchar(100) not null comment '상호',
                        contact1 varchar(15) null comment '대표연락처',
                        contact2 varchar(15) null comment '추가연락처',
                        business_number varchar(10) null unique comment '사업자번호'
);

-- 품목 items
create table items (
                       id int not null auto_increment primary key,
                       name varchar(100) not null unique comment '품명'
);


-- 관계설정(외래키 지정)
alter table purchases
    add foreign key (item_id) references items(id);

alter table purchases
    add foreign key (spec_id) references spec(id);

alter table purchases
    add foreign key (supplier_id) references supply(id);