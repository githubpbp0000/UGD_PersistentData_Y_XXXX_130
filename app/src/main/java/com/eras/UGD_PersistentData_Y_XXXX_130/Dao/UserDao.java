package com.eras.UGD_PersistentData_Y_XXXX_130.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.eras.UGD_PersistentData_Y_XXXX_130.Model.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE username=:username AND password=:password")
    User attemptLogin(String username, String password);

    @Insert
    void registerUser(User user);

}
