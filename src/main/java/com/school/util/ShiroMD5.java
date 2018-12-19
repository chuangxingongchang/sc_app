package com.school.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroMD5 {
    private final String HASH_NAME = "MD5";
    private Object credentials = null;
    private Object salt = null;
    private int hashIterations = 1024;


    public ShiroMD5(){};
    public ShiroMD5(String username){
         salt = new ByteSource.Util().bytes(username);
    }
    public ByteSource saltMD5(String username){
      return new ByteSource.Util().bytes(username);
    }

    public ShiroMD5(String username, String password){
        this.credentials = password;
        this.salt = saltMD5(username);
    }
    public Object  ShiroMD5(){
        return  new SimpleHash(HASH_NAME, credentials, salt, hashIterations);
    }

}
