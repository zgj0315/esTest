概述
---
采用Springboot框架，Java语言编写的操作ElasticSearch的Demo工程。

版本
---
jdk: 1.8

ElasticSearch: 5.2.1

ElasticSearch
---
```shell
cd ~
cd work/software
tar -xvf elasticsearch-5.2.1.tar.gz
mv elasticsearch-5.2.1 ../
cd ../elasticsearch-5.2.1
#启动es
bin/elasticsearch -Ecluster.name=qzt360-es -Enode.name=node_test
```