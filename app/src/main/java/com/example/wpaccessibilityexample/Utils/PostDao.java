package com.example.wpaccessibilityexample.Utils;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wpaccessibilityexample.Activity.HomeActivity;
import com.example.wpaccessibilityexample.data.ModelItem;

import java.util.List;

@Dao
public interface PostDao {

    @Query("select * from post" )
//    @Query("select * from " + HomeActivity.TABLE_NAME)
     List<Post> getAllPosts() ;

    @Insert
    void savePost(Post post);

    @Query("select * from post where id = :postId")
//    @Query("select * from "+ HomeActivity.TABLE_NAME +" where "+HomeActivity.Id+" = :postId")
    Post getPost(int postId );


    @Query(" SELECT exists (select * from post where id = :postId AND userId= :userId ) ")
//    @Query(" SELECT exists (select * from "+ HomeActivity.TABLE_NAME +" where "+HomeActivity.Id + " = :postId AND "+ HomeActivity.USERID+"= :userId ) ")
    Boolean isAlreadySaved(int postId , int userId);
}
