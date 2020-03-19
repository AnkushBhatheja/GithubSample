package com.pickletech.githubapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pickletech.githubapplication.model.Author

@Database(
    entities = [Author::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun githubDao(): GithubDao
}