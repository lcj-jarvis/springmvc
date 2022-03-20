package com.mrlu.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-21 20:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
   private String name;
   private Integer age;
   private String gender;
   /**
    * 座右铭
    */
   private String motto;

}
