package com.eras.UGD_PersistentData_Y_XXXX_130.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.eras.UGD_PersistentData_Y_XXXX_130.Dao.TodoDao;
import com.eras.UGD_PersistentData_Y_XXXX_130.Dao.UserDao;
import com.eras.UGD_PersistentData_Y_XXXX_130.Model.Todo;
import com.eras.UGD_PersistentData_Y_XXXX_130.Model.User;

@Database(entities = {Todo.class, User.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
    public abstract UserDao userDao();

}
