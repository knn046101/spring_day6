create table tuser(
	user_id varchar2(20),
	pass varchar2(20) not null,
	primary key(user_id));
	
create table tcomment(
	comment_no number,
	user_id varchar2(20) not null,
	content varchar2(500) not null,
	primary key(comment_no));
	
alter table tcomment add constraint tcomment_tuser_fk
	foreign key (user_id) references tuser(user_id);

CREATE SEQUENCE SEQ_COMMENT;