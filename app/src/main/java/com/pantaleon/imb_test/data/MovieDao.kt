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

    @Query("SELECT * FROM Movie WHERE id = :id ")
    suspend fun findById(id: Int): Movie

    @Query("SELECT * FROM Movie WHERE releaseDate LIKE :year || '%' ORDER BY CASE :sort " +
            "WHEN 'popularity' THEN popularity " +
            "WHEN 'title' THEN title " +
            "WHEN 'rating' THEN voteAverage " +
            "WHEN 'release date' THEN releaseDate " +
            "END DESC")
    suspend fun getMovies(year: Int, sort: String = "\"popularity"): List<Movie>

    @Insert(onConflict = REPLACE)
    suspend fun insert(movie: Movie)

}