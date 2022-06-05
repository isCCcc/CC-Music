package com.cc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : WZX
 * @create 2022/5/22 22:13
 */
@SpringBootTest
class CollectionControllerTest {

    @Autowired
    CollectionController collectionController;
    @Test
    public void getCollection(){
        collectionController.getCollection();
    }
}