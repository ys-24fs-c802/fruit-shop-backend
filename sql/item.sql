ALTER TABLE items ADD user_id INT NOT NULL;
-- 외래키 지정을 위해 기존 데이터에 키값을 추가한다.
UPDATE items SET user_id=10;
-- 외래키 지정
ALTER TABLE items ADD CONSTRAINT items_users_fk FOREIGN KEY (user_id) REFERENCES users (id) on delete cascade;