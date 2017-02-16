概述
---
采用Springboot框架，Java语言编写的操作ElasticSearch的Demo工程。

版本
---
jdk: 1.8

ElasticSearch: 2.3.3

ElasticSearch
---
从官网下载es，版本为2.3.3（由于es的版本变化时，api也会有相应变化，本工程中的测试只针对2.3.3版本）
```shell
cd ~
cd work/software
tar -xvf elasticsearch-2.3.3.tar.gz
mv elasticsearch-2.3.3 ../
cd ../elasticsearch-2.3.3
#启动es
bin/elasticsearch --cluster.name qzt360-es --node.name node_test
```