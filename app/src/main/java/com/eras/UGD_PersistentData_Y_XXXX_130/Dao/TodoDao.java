package com.eras.UGD_PersistentData_Y_XXXX_130.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eras.UGD_PersistentData_Y_XXXX_130.Model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todos")
    List<Todo> getAll();

    @Insert
    void insertTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

    @Query("SELECT * FROM todos where user_id = :user_id")
    List<Todo> getTodosByUserId(int user_id);
}