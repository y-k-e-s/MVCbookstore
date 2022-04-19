SET FOREIGN_KEY_CHECKS = 0;
delete from author where id > 0;
alter table author auto_increment = 1;

delete from book where id > 0;
alter table book auto_increment = 1;

delete from book_author where book_id > 0;
alter table book_author auto_increment = 1;

delete from book_detail where id > 0;
alter table book_detail auto_increment = 1;

delete from book_genre where id > 0;
alter table book_genre auto_increment = 1;

SET FOREIGN_KEY_CHECKS = 1;
