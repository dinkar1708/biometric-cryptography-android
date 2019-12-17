package cryptography.biometric.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The Room database that contains the tables
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
