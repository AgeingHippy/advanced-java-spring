package com.codingnomads.springdata.example.mybatis.oneandmany.mappers;

import com.codingnomads.springdata.example.mybatis.oneandmany.models.Album;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface AlbumMapper {

    @Insert("INSERT INTO albums (artist_id, name, year) VALUES (#{artist.id}, #{name}, #{year})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertNewAlbum(Album album);

    @Select("SELECT * FROM albums WHERE id = #{param1}")
    @Results(id = "albumResultMap",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "songs",
                            column = "id",
                            javaType = List.class,
                            many = @Many(select =
                                    "com.codingnomads.springdata.example.mybatis.oneandmany.mappers.SongMapper.getSongsByAlbumId",
                                    fetchType = FetchType.LAZY)
                    )
            })
    Album getAlbumById(long id);

    @Select("SELECT * FROM albums WHERE artist_id = #{param1}")
    @ResultMap("albumResultMap")
    List<Album> getAlbumsByArtistId(long artistId);

    @Select("SELECT * FROM albums WHERE id = #{param1}")
    Album getAlbumWithoutSongs(long id);

}
