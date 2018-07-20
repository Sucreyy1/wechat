# wechat
> 此程序为微信小程序后台,包括eureka,backend后台,zuul网关。

* eureka：注册中心，配合zuul网关使用。
* backend：采用Spring Boot、JPA处理微信端的请求。
* zuul网关：处理请求，过滤掉不是微信端的请求，需要做token校验。


