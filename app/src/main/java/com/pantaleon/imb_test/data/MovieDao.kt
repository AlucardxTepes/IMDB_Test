package com.pantaleon.imb_test.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.pantaleon.imb_test.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie LIMIT 1")
    suspend fun dbCheck(): Movie

    @Query("SELECT * FROM Movie WHERE releaseDate LIKE :year || '%' ORDER BY :sort DESC")
    suspend fun getMovies(year: Int, sort: String = "popularity"): List<Movie>

    @Insert(onConflict = REPLACE)
    suspend fun insert(movie: Movie)

}