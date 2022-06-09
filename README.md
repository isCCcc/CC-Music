# CC-Music
一个基于springboot+vue的音乐网站（后续会不断更新提交）

前端使用技术：
vue、vuex、vue-router、elementui、bootstrap；
后端使用技术：
springboot、mybatisx；


#### 1、导入数据库数据
  于sql文件夹中(mysql8)
  
#### 2、后台项目
  将website_server于idea中打开，修改`application.yml`文件中的数据库密码：
  ![image](https://user-images.githubusercontent.com/76574442/172813318-b67f9af6-0a7a-4076-ad2e-53ccd242c784.png)
  
  运行主类 `WebsiteServerApplication`,当看见控制台输出字符串：`okokook!`说明主类运行成功。
  ![image](https://user-images.githubusercontent.com/76574442/172812800-184906d1-8e00-45ca-b604-f0dec862a806.png)


#### 3、前端项目
  打开website_client，
  新建终端并运行 `yarn init` 进行初始化，
  终端运行yarn serve进入前端登录页面，（这里由于我在后台对用户登录的密码进行了加密操作，所以可以注册一个账号后再登录，也可以使用默认登录账号：admin，密码：123）：
  若忘记密码，可以进入后端项目，找到测试类 `EncryptionTest`,将user表里的密码copy到decryption()函数里进行解密
  ![image](https://user-images.githubusercontent.com/76574442/172816666-5dc78b89-4519-4db9-a99c-c0a1b8ab5b54.png)
  
##### 登录和注册页面：
![image](https://user-images.githubusercontent.com/76574442/172817136-d915e67d-570d-4c7e-96e3-b40887161c2f.png)

##### 首页：
![image](https://user-images.githubusercontent.com/76574442/172818009-ffcebb68-36c0-4e6d-a5be-ee0bdadffd23.png)

##### 歌单页：
![image](https://user-images.githubusercontent.com/76574442/172817442-900f1d9d-fb58-4064-8045-aa13649f0171.png)

##### 歌手页：
![image](https://user-images.githubusercontent.com/76574442/172817537-a80c46bf-b298-4809-9416-41c22b66e77c.png)

##### 收藏页：
![image](https://user-images.githubusercontent.com/76574442/172817695-ffb25092-2583-4a2d-a86c-e8b5a448a11d.png)

##### 个人信息页：
![image](https://user-images.githubusercontent.com/76574442/172817804-52e2eba6-6e32-43f9-8619-bbe07c0155df.png)


  
# 本项目仅用于练手，数据和音乐资源均来源于网络，侵权联系即删。
