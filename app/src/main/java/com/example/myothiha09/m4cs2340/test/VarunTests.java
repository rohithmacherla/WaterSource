package com.example.myothiha09.m4cs2340.test;

import org.junit.Before;
import org.junit.Test;

import android.widget.EditText;


import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.controller.UserDetailsActivity;
import com.example.myothiha09.m4cs2340.model.User;
import com.example.myothiha09.m4cs2340.model.UserType;


import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Varun Giridhar's JUnit Tests on 4/10/17. (VERSION 2.0)
 * The JUnit tests for the checkUserName(String username, ArrayList<User> list) method in the
 *  UserDetailsActivity.java class in the controller.
 */
public class VarunTests {
    ArrayList<User> users;
    User user1;
    User user2;
    User user3;
    User user4;


    @Before
    public void setUp() {
        users = new ArrayList<>();

        user1 = new User("a", "123", "gmail", UserType.USER);
        user2 = new User("b", "456", "hmail", UserType.WORKER);
        user3 = new User("c", "789", "imail", UserType.MANAGER);
        user4 = new User("d", "101", "jmail", UserType.ADMIN);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    @Test
    public void test_NullArgs() {
        ArrayList<User> users2 = new ArrayList<>();

        assertFalse(UserDetailsActivity.checkUsername("", users));
        assertFalse(UserDetailsActivity.checkUsername("", users2));
        assertFalse(UserDetailsActivity.checkUsername("shambamdam", users2));
    }

    @Test
    public void test_UserNameValid() {
        assertTrue(UserDetailsActivity.checkUsername(user1.getName(), users));
        assertTrue(UserDetailsActivity.checkUsername(user2.getName(), users));
        assertTrue(UserDetailsActivity.checkUsername(user3.getName(), users));
        assertTrue(UserDetailsActivity.checkUsername(user4.getName(), users));
    }

    @Test
    public void test_UserNameInvalid() {
        assertFalse(UserDetailsActivity.checkUsername("platform", users));
        assertFalse(UserDetailsActivity.checkUsername("and", users));
        assertFalse(UserDetailsActivity.checkUsername("plugin", users));
        assertFalse(UserDetailsActivity.checkUsername("updates", users));
    }
}