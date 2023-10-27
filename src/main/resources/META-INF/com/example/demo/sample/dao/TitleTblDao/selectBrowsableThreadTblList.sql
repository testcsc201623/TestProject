select
	title.title_id,
	title.title,
	title.create_user_id,
	title.create_date_time,
	title.update_date_time
from
	(
	select
		a.title_id,
		a.title,
		a.create_user_id,
		b.user_id as browsable_user_id,
		a.create_date_time,
		a.update_date_time
	from
		title_tbl as a
	inner join
		title_permission_tbl as b
	on
		a.title_id = b.title_id
	order by
		a.title_id
	) as title
where
	title.browsable_user_id = /* userId */'testuser'