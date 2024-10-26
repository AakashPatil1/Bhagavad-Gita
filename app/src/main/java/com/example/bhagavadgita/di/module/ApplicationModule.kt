package com.example.bhagavadgita.di.module

import android.content.Context
import androidx.room.Room
import com.example.bhagavadgita.roomdb.BhagavadGitaDao
import com.example.bhagavadgita.roomdb.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideBhagavadGitaDao(myDatabase: MyDatabase): BhagavadGitaDao {
        return myDatabase.getDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MyDatabase {
        return Room.databaseBuilder(
            appContext,
            MyDatabase::class.java,
            "bhagavad_gita"
        ).allowMainThreadQueries().build()

    }
}

