# aop-mysql
* 基于docker开启多个mysql数据源
* 利用aop实现数据源的切换，关键在于sqlSessionFactory

记录下一个小bug：
 因为我使用的是mybatis-plus，因此sqlSessionFactory需要使用mybatisSqlSessionFactory

