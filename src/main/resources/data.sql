insert into user_mst (user_id, password, user_name, admin_flg, create_date_time, update_date_time) values ('admin', '4a8e16a0feb383b38521808d5a97f07e40b5964d50fc509c0c93ff268b8dcf65', '管理者ちゃん', 1,'2021-09-22 09:00:00', '2021-07-13 09:00:00');
insert into user_mst (user_id, password, user_name, admin_flg, create_date_time, update_date_time) values ('ippan', 'a43f9d68f9068eb7f910040ce4ce6f86b0ec39408660548687cfe9eeeba5fd1a', '一般君', 0,'2021-09-22 09:00:00', '2021-07-13 09:00:00');
insert into title_tbl(title_id, title, create_user_id, create_date_time, update_date_time) values (1,'雑談部屋','admin','2021-08-01 09:00:00', '2021-08-13 12:00:00');
insert into thread_tbl(title_id, message_number, user_id, comment, create_date_time, update_date_time) values (1, 1,'admin', 'testTEST(・Д・)', '2021-09-22 09:00:00', '2021-08-25 12:00:00');
insert into thread_tbl(title_id, message_number, user_id, comment, create_date_time, update_date_time) values (1, 2,'ippan', '一般アカです', '2021-09-22 09:00:00', '2021-08-25 12:00:00');
insert into title_permission_tbl(title_id,user_id,admin_flg) values (1, 'admin', 1);
insert into title_permission_tbl(title_id,user_id,admin_flg) values (1, 'ippan', 0);