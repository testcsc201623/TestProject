select
	thread_tbl.title_id,
	thread_tbl.message_number,
	user_mst.user_id,
	user_mst.user_name,
	thread_tbl.comment,
	thread_tbl.create_date_time
from
	thread_tbl
inner join
	user_mst
on
	thread_tbl.user_id = user_mst.user_id
where
	thread_tbl.title_id  = /* titleId */'1'
order by
	thread_tbl.message_number