集成springfox

1、查看swagger2-api文档

http://localhost:8080/swagger-ui.html

2、查看api-docs文档
http://localhost:8080/v2/api-docs

3、api文档的注解，还需要补充；

demo

1、controller 方法

@ApiOperation(value = "获取商品信息", httpMethod = "GET",response = GoodsDto.class)

注意点如下：

1、写法不要耦合代码，尽量不要写到一起；

2、RequestMapping中，一定要指定method,否则会列出所有的可能；
