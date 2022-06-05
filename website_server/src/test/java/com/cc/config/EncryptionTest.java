package com.cc.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : WZX
 * @create 2022/6/3 14:09
 */
@SpringBootTest
class EncryptionTest {
    // Encryption encryption ;
    @Test
    void test(){
        String str = "加密字符串";
        // 使用的时候可嵌套多层，嵌套越多密文越长
        System.out.println(Encryption.encryption("123"));
        System.out.println(Encryption.decryption("Ya55Mqo69qI6syI6Gi65"));
        // System.out.println(encryption.encryption(encryption(str)));
        // System.out.println(decryption(decryption(encryption(encryption(str)))));
    }

}