## 一、Args任务拆解
### 1. 需要开发一个解析器，功能是
1. 输入是：命令行参数
2. 输出是：可以传递给main函数的参数 logging=true;port=8080;directory=/usr/logs;



### 2.验证输入参数
1. 要验证是否符合命令行参数的规则 -l -p 8080 -d /usr/logs 的结构
> 2. 输入参数需要有一个schema描述，schema的描述包括：应该有几个标记，标记类型，标记缺省值;schema 该如何描述和确认：例如：ps -aux|grep 8080,ps可以理解为一个schema ，预先定义好本案的schema为：printlog l:logging:boolean:true p:port:int:8080 d:directory:string:/usr/logs； 


标记简称 | 标记全称 | 标记类型 | 标记默认值
---|---|---|---
l | logging | boolean | True
p | port | int | 8080
d | directory | String | /usr/log


3. 如果参数结构不匹配，给出良好的错误信息，介绍出错的原因


```
* "l:boolean" => ArgSpec("l", "boolean")
* "l:boolean p:integer d:string" => Schema
 
```
