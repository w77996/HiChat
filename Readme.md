## HiChat
简易的聊天系统,使用netty支持前端的websocket操作

-----

### v1.0功能
    
* 连接
* 用户登录
* 发送消息

### 使用文档        
1.连接地址
    
    ws://localhost:7777/ws

2.登录

    用户1：{"action":1,"chatMsg":{"senderId":"user1"}}
    用户1：{"action":1,"chatMsg":{"senderId":"user2"}}
    
3.发送消息

     用户1：{"action":2,"chatMsg":{"senderId":"user1","receiverId":"user2","msg":"这是user1发给user2的消息"}}
     用户1：{"action":2,"chatMsg":{"senderId":"user2","receiverId":"user1","msg":"user2收到user1发的消息"}}
     
### v1.1功能开发
    
  * 数据库保存消息
    