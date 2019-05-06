package com.pier.mapper;

import com.pier.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 登录
     */
    User login(@Param("un") String username, @Param("pw") String password);
    /**
     * 新增用戶
     */
    /*@Options(useGeneratedKeys=true,keyProperty="user.id")*/
    int insertUser(@Param("user") User user) throws Exception;


    /**
     * 修改用戶
     */
    @Update(" update t_user set username=#{u.username},password=#{u.password},account=#{u.account} where id=#{id}")
    int updateUser(@Param("u") User user, @Param("id") int id) throws Exception;

     /**
      * 刪除用戶
      */
    @Delete(" delete from t_user where id=#{id}  ")
    int deleteUser(int id) throws Exception;


    /**
     * 根据id查询用户信息
     */
    @Results(id="userMap2", value = {

            @Result(id=true,property="id",column="id",javaType=Integer.class),
            @Result(property="username",column="username",javaType=String.class),
            @Result(property="password",column="password",javaType=String.class),
            @Result(property="account",column="account",javaType=Double.class)
    })
    @Select(" select * from t_user where id=#{id}")
    User selectUserById(int id) throws Exception;
     /**
      * 查询所有的用户信息
      */

    @Select(" select * from t_user")
    List<User> selectAllUser() throws Exception;


    /**
     * 批量增加
     */
   int batchInsertUser(@Param("users") List<User> user) throws Exception;

   /**
    * 批量删除
    */
   int batchDeleteUser(@Param("list") List<Integer> list) throws Exception;
   
   
   /**
    * 分页查询数据
    */
   List<User> pagerUser(Map<String, Object> parmas) throws Exception;
   
   /**
    * 分页统计数据
    */
   int countUser(Map<String, Object> parmas) throws Exception;
}