create table `t_dpm_outer_account_subset` (
  `account_no` varchar(32) not null comment '账号',
  `balance` decimal(19,4) not null comment '余额',
  `fund_type` varchar(16) not null comment '资金属性  1、dr,借记  2、cr.贷记 3、fr.冻结',
  `balance_type` decimal(1,0) default null comment '余额类型',
  `remark` varchar(256) default null comment '备注',
  `update_time` timestamp not null default current_timestamp comment '更新时间',
  `create_time` timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
  primary key (`account_no`,`fund_type`)
) engine=innodb default charset=utf8 comment='外部户分户账表';


create table `t_dpm_subacc_fundattribute` (
  `account_type` decimal(4,0) not null comment '账户类型',
  `currency_code` varchar(3) not null comment '币种',
  `fund_type` varchar(16) not null comment '资金属性',
  `remark` varchar(200) default null comment '备注',
  `gmt_created` timestamp not null default current_timestamp on update current_timestamp comment '创建时间',
  `gmt_modified` timestamp not null default '0000-00-00 00:00:00' comment '更新时间',
  unique key `uk_tdaf_account_type` (`account_type`,`currency_code`,`fund_type`)
) engine=innodb default charset=utf8 comment='外部户资金属性配置表';



create table `t_dpm_inner_account_detail` (
  `txn_seq_no` bigint(28) not null auto_increment comment '主键(自增处理)',
  `sys_trace_no` varchar(32) default null comment '系统跟踪号',
  `accounting_date` varchar(8) default null comment '会计日',
  `txn_time` timestamp(3) not null default current_timestamp(3) on update current_timestamp(3) comment '入账时间',
  `account_no` varchar(32) default null comment '账户号',
  `txn_type` decimal(1,0) default null comment '0:正常\r\n 1:红字\r\n 2:蓝字\r\n 9:抹帐',
  `txn_dscpt` varchar(256) default null comment '摘要',
  `change_type` decimal(1,0) default null comment '1: 借贷\r\n 2: 冻结解冻',
  `direction` varchar(1) default null comment '1:加(收入)\r\n 2:减(支出)',
  `txn_amt` decimal(19,4) default null comment '交易金额',
  `before_amt` decimal(19,4) default null comment '交易前余额',
  `after_amt` decimal(19,4) default null comment '交易后余额',
  `entry_seq_no` varchar(32) default null,
  `other_account_no` varchar(32) default null,
  `old_txn_seq_no` varchar(32) default null,
  `remark` varchar(256) default null comment '备注',
  `crdr` decimal(1,0) default null comment '借贷标志 1借2贷',
  `product_code` varchar(12) default null comment 'pe产品编码',
  `pay_code` varchar(12) default null comment 'pe支付编码',
  `operation_type` decimal(1,0) default null comment '交易类型',
  `delete_sign` decimal(1,0) default null comment '删除标记(冲正用)',
  `suite_no` varchar(32) default null comment '套号',
  `context_voucher_no` varchar(32) default null comment '关联结算流水号',
  `transaction_no` varchar(32) default null,
  `voucher_no` varchar(50) default null,
  primary key (`txn_seq_no`),
  unique key `uidx_iad_vo` (`voucher_no`),
  key `ak_inner_key_account_no` (`account_no`),
  key `idx_diad_stn` (`sys_trace_no`),
  key `idx_diad_txn_time` (`txn_time`)
) engine=innodb auto_increment=2025782 default charset=utf8 comment='内部账户余额明细';

create table `t_dpm_outer_account_detail` (
  `txn_seq_no` bigint(28) not null auto_increment comment '主键(自增处理)',
  `sys_trace_no` varchar(32) default null comment '系统跟踪号',
  `accounting_date` varchar(8) default null comment '会计日',
  `txn_time` timestamp(3) not null default current_timestamp(3) on update current_timestamp(3) comment '入账时间',
  `account_no` varchar(32) default null comment '账户号',
  `txn_type` decimal(1,0) default null comment '0:正常 1:红字 2:蓝字 9:抹帐',
  `txn_dscpt` varchar(256) default null comment '摘要',
  `change_type` decimal(1,0) default null comment '1: 借贷 2: 冻结解冻',
  `direction` decimal(1,0) default null comment '1:加(收入) 2:减(支出)',
  `frozen_flag` varchar(1) default null comment '1:冻结 2:解冻',
  `txn_amt` decimal(19,4) default null comment '交易金额',
  `before_amt` decimal(19,4) default null comment '交易前余额',
  `after_amt` decimal(19,4) default null comment '交易后余额',
  `entry_seq_no` varchar(32) default null comment '分录号',
  `other_account_no` varchar(32) default null comment '关联账户号',
  `old_txn_seq_no` varchar(32) default null comment '原始明细流水号',
  `remark` varchar(256) default null comment '备注',
  `crdr` decimal(1,0) default null comment '借贷标志 1借2贷',
  `product_code` varchar(12) default null comment 'pe产品编码',
  `pay_code` varchar(12) default null comment 'pe支付编码',
  `operation_type` decimal(1,0) default null comment '操作类型：1 正常交易，2 冻结资金，3 解冻资金',
  `delete_sign` decimal(1,0) default null comment '删除标记(冲正用)',
  `suite_no` varchar(32) default null comment '套号',
  `context_voucher_no` varchar(32) default null comment '关联结算流水号',
  `accounting_rule` varchar(16) default null comment '入账规则 0.先贷后借(默认) 1.借记 2.贷记 3.冻结',
  `transaction_no` varchar(32) default null comment '事务号',
  `voucher_no` varchar(50) default null comment '凭证号',
  primary key (`txn_seq_no`),
  unique key `uidx_oad_vo` (`voucher_no`),
  key `idx_doad_an` (`account_no`),
  key `idx_doad_stn` (`sys_trace_no`),
  key `idx_doa_an_tt` (`account_no`,`txn_time`),
  key `idx_doa_tt` (`txn_time`)
) engine=innodb auto_increment=1678513 default charset=utf8 comment='外部户账户明细表';

create table `t_dpm_outer_account_sub_detail` (
  `account_subset_log_id` bigint(32) unsigned not null auto_increment comment '分户账户余额明细',
  `account_no` varchar(32) not null comment '账户号',
  `fund_type` varchar(16) not null comment '资金属性  1,借记  2.贷记',
  `balance_type` decimal(1,0) not null comment '余额类型  1.普通  2.冻结',
  `sys_trace_no` varchar(32) default null comment '系统跟踪号',
  `voucher_no` varchar(50) not null comment '凭证号',
  `txn_amt` decimal(19,4) default null comment '交易金额',
  `after_amt` decimal(19,4) default null comment '交易后余额',
  `accounting_rule` varchar(16) not null comment '入账规则  0.先贷后借(默认)  1.借记  2.贷记  3.冻结',
  `remark` varchar(256) default null comment '备注',
  `update_time` timestamp not null default current_timestamp comment '更新时间',
  `create_time` timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
  `direction` decimal(1,0) not null comment '1:加(收入)  2:减(支出)',
  primary key (`account_subset_log_id`),
  key `idx_account_sub_dtl_vouch` (`voucher_no`) using btree
) engine=innodb auto_increment=16174778 default charset=utf8 row_format=compact;

create table `t_dpm_outer_account` (
  `account_no` varchar(32) not null comment '账户号',
  `account_title_no` varchar(32) default null comment '科目号',
  `account_name` varchar(64) default null comment '账户名称',
  `member_id` varchar(15) default null comment '会员号',
  `status_map` varchar(6) default null comment '状态，共6位，每位表示一种类型的状态',
  `account_attribute` decimal(1,0) default null comment '账户属性 1:对私，2:对公',
  `account_type` decimal(4,0) default null comment '账户类型',
  `curr_bal_direction` decimal(1,0) default null comment '当前余额方向 1:借，2:贷',
  `bal_direction` decimal(1,0) default null comment '账户余额方向 1:借，2:贷，0:双向',
  `currency_code` varchar(3) default null comment '货币类型',
  `last_update_time` timestamp not null default current_timestamp comment '更新时间',
  `request_no` varchar(32) not null comment '开外部户请求请求号:不可为空 需唯一',
  `gmt_create` timestamp not null default current_timestamp comment '创建时间',
	`gmt_modified` timestamp not null default current_timestamp comment '最后修改时间',
  primary key (`account_no`),
  unique key `uidx_doao_ro` (`request_no`) using btree,
  key `idx_oa_member_id` (`member_id`) using btree,
  key `idx_t_dpm_outer_account` (`open_date`) using btree
) engine=innodb default charset=utf8;