# 支付平台

    面向中大型互联网平台的支付平台

# 整体架构

![架构图](https://github.com/remind/doanything-payment/blob/main/doc/img/architecture.png)

# 应用说明

| 应用      | 中文名称 | 简介                             |
|---------|------|--------------------------------|
|trade| 交易系统 | 提供各种交易械，如收单类的即时到账和担保交易，资金类的充转提 
| paycore | 支付核心 | 向上提供各种支付模式，向下集成各种资产            |
| account | 账务系统 | 内外账户、明细、会计分录                   |
| basic   | 公共服务 | 封装如文件存储、短信、邮件等等                |
| member  | 会员系统 | 个人、企业会员，会员信息                   |

# 应用结构

![架构图](https://github.com/remind/doanything-payment/blob/main/doc/img/app-architecture.png)

[技术规范](https://www.yuque.com/fengyu-sfney/uc5srd/qfxlya2nx73cggl8?singleDoc#)
