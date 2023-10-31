select
	user_mst.user_name
from
	title_permission_tbl
inner join
	user_mst
on
	title_permission_tbl.user_id = user_mst.user_id
where
	title_permission_tbl.title_id = /* titleId */'1'
order by
	title_permission_tbl.admin_flg desc