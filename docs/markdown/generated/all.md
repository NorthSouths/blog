# 文档


<a name="overview"></a>
## 概览
RESTFUL API文档


### 版本信息
*版本* : 1.0


### URI scheme
*域名* : localhost:8080  
*基础路径* : /


### 标签

* account-controller : Account Controller
* blog-controller : Blog Controller
* check-controller : Check Controller
* problem-controller : Problem Controller
* special-controller : Special Controller
* user-controller : User Controller




<a name="paths"></a>
## 资源

<a name="account-controller_resource"></a>
### Account-controller
Account Controller


<a name="loginusingpost"></a>
#### 用户登录
```
POST /login
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**loginDto**  <br>*必填*|loginDto|[LoginDto](#logindto)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/login
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "password" : "string",
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="logoutusingget"></a>
#### logout
```
GET /logout
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/logout
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="registerusingget"></a>
#### register
```
GET /register
```


##### 参数

|类型|名称|类型|
|---|---|---|
|**Query**|**avatar**  <br>*可选*|string|
|**Query**|**created**  <br>*可选*|string (date-time)|
|**Query**|**email**  <br>*可选*|string|
|**Query**|**id**  <br>*可选*|integer (int64)|
|**Query**|**lastLogin**  <br>*可选*|string (date-time)|
|**Query**|**password**  <br>*可选*|string|
|**Query**|**status**  <br>*可选*|integer (int32)|
|**Query**|**username**  <br>*可选*|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/register
```


###### 请求 query
```
json :
{
  "avatar" : "string",
  "created" : "string",
  "email" : "string",
  "id" : 0,
  "lastLogin" : "string",
  "password" : "string",
  "status" : 0,
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="blog-controller_resource"></a>
### Blog-controller
Blog Controller


<a name="delusingpost"></a>
#### del
```
POST /blog/del
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**blog**  <br>*必填*|blog|[Blog](#blog)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/blog/del
```


###### 请求 body
```
json :
{
  "content" : "string",
  "created" : "string",
  "description" : "string",
  "id" : 0,
  "status" : 0,
  "title" : "string",
  "userId" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="editusingpost"></a>
#### edit
```
POST /blog/edit
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**blog**  <br>*必填*|blog|[Blog](#blog)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/blog/edit
```


###### 请求 body
```
json :
{
  "content" : "string",
  "created" : "string",
  "description" : "string",
  "id" : 0,
  "status" : 0,
  "title" : "string",
  "userId" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="detialusingget"></a>
#### detial
```
GET /blog/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/blog/0
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="listusingget"></a>
#### list
```
GET /blogs
```


##### 参数

|类型|名称|说明|类型|默认值|
|---|---|---|---|---|
|**Query**|**currentPage**  <br>*可选*|currentPage|integer (int32)|`1`|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/blogs
```


###### 请求 query
```
json :
{
  "currentPage" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="myblogusingget"></a>
#### myblog
```
GET /myblog
```


##### 参数

|类型|名称|说明|类型|默认值|
|---|---|---|---|---|
|**Query**|**currentPage**  <br>*可选*|currentPage|integer (int32)|`1`|
|**Query**|**id**  <br>*必填*|id|integer (int32)||


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/myblog
```


###### 请求 query
```
json :
{
  "currentPage" : 0,
  "id" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="check-controller_resource"></a>
### Check-controller
Check Controller


<a name="bestusingget"></a>
#### best
```
GET /best
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/best
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="checkusingpost"></a>
#### check
```
POST /check
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**over**  <br>*必填*|over|[Overp](#overp)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/check
```


###### 请求 body
```
json :
{
  "content" : "string",
  "id" : 0,
  "pid" : 0,
  "status" : 0,
  "title" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="checksusingget"></a>
#### checks
```
GET /checks
```


##### 参数

|类型|名称|说明|类型|默认值|
|---|---|---|---|---|
|**Query**|**currentPage**  <br>*可选*|currentPage|integer (int32)|`1`|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/checks
```


###### 请求 query
```
json :
{
  "currentPage" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="failureusingpost"></a>
#### failure
```
POST /failure
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**overp**  <br>*必填*|overp|[Overp](#overp)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/failure
```


###### 请求 body
```
json :
{
  "content" : "string",
  "id" : 0,
  "pid" : 0,
  "status" : 0,
  "title" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="rankusingget"></a>
#### rank
```
GET /rank/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/rank/0
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="ranksucesssusingget"></a>
#### ranksucesss
```
GET /ranksu/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/ranksu/0
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="rightusingpost"></a>
#### right
```
POST /right
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**overp**  <br>*必填*|overp|[Overp](#overp)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/right
```


###### 请求 body
```
json :
{
  "content" : "string",
  "id" : 0,
  "pid" : 0,
  "status" : 0,
  "title" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="problem-controller_resource"></a>
### Problem-controller
Problem Controller


<a name="problemusingget"></a>
#### problem
```
GET /problem/{pid}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**pid**  <br>*必填*|pid|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/problem/0
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="specialusingget"></a>
#### special
```
GET /special/{sid}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**sid**  <br>*必填*|sid|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/special/0
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="special-controller_resource"></a>
### Special-controller
Special Controller


<a name="specialsusingget"></a>
#### specials
```
GET /specials
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/specials
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="user-controller_resource"></a>
### User-controller
User Controller


<a name="registerusingpost"></a>
#### register
```
POST /user/register
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**user**  <br>*必填*|user|[UserDto](#userdto)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/user/register
```


###### 请求 body
```
json :
{
  "email" : "string",
  "password" : "string",
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="userusingget"></a>
#### user
```
GET /user/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/user/0
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```




<a name="definitions"></a>
## 定义

<a name="blog"></a>
### Blog

|名称|说明|类型|
|---|---|---|
|**content**  <br>*可选*|**样例** : `"string"`|string|
|**created**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**description**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `0`|integer (int64)|
|**status**  <br>*可选*|**样例** : `0`|integer (int32)|
|**title**  <br>*可选*|**样例** : `"string"`|string|
|**userId**  <br>*可选*|**样例** : `0`|integer (int64)|


<a name="logindto"></a>
### LoginDto

|名称|说明|类型|
|---|---|---|
|**password**  <br>*可选*|**样例** : `"string"`|string|
|**username**  <br>*可选*|**样例** : `"string"`|string|


<a name="overp"></a>
### Overp

|名称|说明|类型|
|---|---|---|
|**content**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `0`|integer (int64)|
|**pid**  <br>*可选*|**样例** : `0`|integer (int64)|
|**status**  <br>*可选*|**样例** : `0`|integer (int32)|
|**title**  <br>*可选*|**样例** : `"string"`|string|


<a name="result"></a>
### Result

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"object"`|object|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="userdto"></a>
### UserDto

|名称|说明|类型|
|---|---|---|
|**email**  <br>*可选*|**样例** : `"string"`|string|
|**password**  <br>*可选*|**样例** : `"string"`|string|
|**username**  <br>*可选*|**样例** : `"string"`|string|





