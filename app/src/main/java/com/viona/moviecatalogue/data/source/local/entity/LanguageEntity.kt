package com.viona.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*

data class LanguageWithMovie (
    @Embedded
    var mMovie: MovieEntity,

    @Relation(parentColumn = "movieId", entityColumn = "movieId")
    var mLanguage: List<LanguageEntity>
)

@Entity(tableName = "movie",
    primaryKeys = ["languageId", "movieId"],
    foreignKeys = [ForeignKey(entity = MovieEntity::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"])],
    indices = [Index(value = ["movieId"]),
        Index(value = ["movieId"])])
data class LanguageEntity(
    @NonNull
    @ColumnInfo(name = "languageId")
    var languageId: String,

    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: String,

    @NonNull
    @ColumnInfo(name = "englishName")
    var englishName: String,

    )

