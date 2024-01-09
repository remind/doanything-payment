### 初始化SQL
```sql
CREATE TABLE `tf_sequence` (
  `name` varchar(50) CHARACTER SET utf8mb3 NOT NULL COMMENT '序列名',
  `current_value` bigint NOT NULL DEFAULT '1' COMMENT '当前值',
  `increment` smallint NOT NULL DEFAULT '1' COMMENT '增长步长',
  `total` smallint NOT NULL DEFAULT '20' COMMENT '单次取值总量，更新总量需重启应用',
  `threshold` smallint NOT NULL DEFAULT '20' COMMENT '刷新阀值，更新阀值需重启应用',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='序列';
```

### 调用

序列接口：`cn.doanything.framework.api.sequence.SequenceService.getNext`

ID生成接口：`cn.doanything.framework.api.id.IdService.nextId`