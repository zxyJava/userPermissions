# 场景

目前的后台管理系统中，一般都会有用户管理、权限管理、角色管理等基础功能模块。为提高开发效率，现把这些共通基础模块封装成组件，形成了ocam-maintenance。
注意：此组件需配合前端代码一起使用

# 使用方式

1. maven

    ```xml
    <dependency>
        <groupId>com.kws-info</groupId>
        <artifactId>ocam-maintenance</artifactId>
        <version>0.0.1-RELEASE</version>
    </dependency>
    ```

2. 前端使用

    目前只适配了VUE-ELEMENT-UI前端框架。
    git代码地址：
    
3. 注意事项
    此组件持久层是基于JPA实现，为了兼容不同类型的数据库。如不适用于开发框架，可重写持久层。

# 用户-角色-权限菜单关系

    一个用户对应多个角色
    一个角色对应多个菜单
    一个用户也可以直接对应多个菜单
    
    原型地址：https://org.modao.cc/app/abd099101156ba0ee36b2a564847271d1f4c9156
    
