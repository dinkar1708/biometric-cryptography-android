package cryptography.biometric.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Data Access Object for the users table.
 */
@Dao
interface UserDao {

    @Insert
    fun insertAll(vararg users: User)

    /**
     * Get a phoneNumber by id.

     * @return the phoneNumber from the table with a specific id.
     */
    @Query("SELECT * FROM Users WHERE userid = :id")
    fun getUserById(id: String): Flowable<User>

    /**
     * Insert a phoneNumber in the database. If the phoneNumber already exists, replace it.

     * @param user the phoneNumber to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Completable

    /**
     * Delete all users.
     */
    @Query("DELETE FROM Users")
    fun deleteAllUsers()
}
