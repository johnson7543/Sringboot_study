## 基本要求:
1. 用spring boot 2.5.4, spring web MVC, 串一個API, fetch DB 拿10個SKU data 出來
2. 用RestTemplate, backend 串另一個API, call API 
https://www.hktvmall.com/hktv/zh/ajax/getTop100?code=personalcarenhealth&pageSize=11&currentPage=0
把 products[].code
products[].description 顯示出來
3. 用mysql 8, ORM用spring data JPA/ JDBC
4. docker deploy這個java app
5. 有unit test
6. 前端UI沒限制 有簡單就可以
7. 其他有學到 想試試的都可加入
8. commit到 http://it-catch.hktv.com.hk:10080/, 起一個repo project名字 叫 {英文名_姓}_springboot_study就好了


## Run mysql 8
```
docker exec -it mysql8 bash -l
```
```
mysql -uroot -p12345
```
```
CREATE TABLE `mydb`.`products` (
  `code` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(10000) DEFAULT NULL,
  PRIMARY KEY (`code`));
```
```
INSERT INTO `mydb`.`products` (`code`, `name`, `description`) VALUES ('test1', '商品名稱', '商品介紹');
```
```
quit
```
```
exit
```

## Check docker bridge
```
docker network inspect bridge
```
```
+-----------------------------------------------+
| host                                          |
|  +------------------+   +------------------+  |
|  | spring-boot-demo |   |      mysql8      |  |
|  |    172.17.0.3    |   |    172.17.0.2    |  |
|  +------------------+   +------------------+  |
|            ^                      ^           |
|            |                      |           |
|            v                      v           |
|       +--------------------------------+      |
|       |     Docker bridge network      |      |
|       |           (docker0)            |      |
|       +--------------------------------+      |
|                       ^                       |
+-----------------------|-----------------------+
                        v
               +------------------+
               | external network |
               +------------------+
```


## Build java app image and run it
```
mvn compile jib:dockerBuild
```
```
docker run -p 8080:8080 -it --name demo spring-boot-demo:latest
```

## Build with docker compose
```
docker-compose up --build
```
- [GET] http://localhost:8080/products_page_demo/products/all

- [POST] http://localhost:8080/products_page_demo/api/products/restTemplate/hktv

- Index(Products Page) http://localhost:8080/products_page_demo/api/products/index


## Reference
- spring boot with mysql deploy in docker
  - https://matthung0807.blogspot.com/2020/12/docker-run-spring-boot-and-mysql-containers.html
  - https://matthung0807.blogspot.com/2020/08/docker-mysql.html

- unit test
  - https://stackoverflow.com/questions/23435937/how-to-test-spring-data-repositories

- restTemplate
  - https://kucw.github.io/blog/2020/6/java-jackson/
  - https://stackoverflow.com/questions/5245840/how-to-convert-jsonstring-to-jsonobject-in-java
  - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html#getForObject-java.lang.String-java.lang.Class-java.util.Map-

- remove HTML tag
  - https://www.cjavapy.com/article/721/
  - http://tools.jb51.net/regex/create_reg

- frontend
  - https://www.796t.com/article.php?id=292437
  - https://www.cnblogs.com/msi-chen/p/10974009.html
  - https://matthung0807.blogspot.com/2018/03/spring-mvc-restcontrollercontroller.html

- docker compose
  - https://tariqul-islam-rony.medium.com/spring-boot-and-multi-stage-dockerized-image-with-mysql-in-docker-compose-part-3-2999b2bdf6aa
  - https://stackoverflow.com/a/51437525


Command line instructions

Git global setup
```
git config --global user.name "TW - IT - Johnson Wang"
git config --global user.email "johnson.wang@shoalter.com"
```
Create a new repository
```
git clone http://it-catch.hktv.com.hk:10080/johnson7543/Johnson_Wang_sringboot_study.git
cd Johnson_Wang_sringboot_study
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master
```
Existing folder
```
cd existing_folder
git init
git remote add origin http://it-catch.hktv.com.hk:10080/johnson7543/Johnson_Wang_sringboot_study.git
git add .
git commit -m "Initial commit"
git push -u origin master
```
Existing Git repository
```
cd existing_repo
git remote rename origin old-origin
git remote add origin http://it-catch.hktv.com.hk:10080/johnson7543/Johnson_Wang_sringboot_study.git
git push -u origin --all
git push -u origin --tags
```