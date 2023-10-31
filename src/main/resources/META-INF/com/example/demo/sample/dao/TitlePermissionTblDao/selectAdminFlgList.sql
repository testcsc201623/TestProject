select
	title_permission_tbl.admin_flg
from
	title_permission_tbl
where
	title_permission_tbl.title_id = /* titleId */'1'
and
	title_permission_tbl.user_id = /* userId */'admin'