package com.pickletech.githubapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pickletech.githubapplication.model.Author

@Dao
interface GithubDao {
    @Query("select * from Author where name LIKE:query")
    fun fetchAuthors(query: String): MutableList<Author>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthors(author: List<Author>)
}