package id.deval.tebu.db.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.deval.tebu.db.Database
import id.deval.tebu.db.Session

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext
        appContext : Context
    ): Database{
        return Room.databaseBuilder(
            appContext,
            Database::class.java,
            "db-tebu.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideSharedPreferences(
        @ApplicationContext
        context: Context
    ):Session{
        return Session(context)
    }
}