package com.codingnomads.springdata.example.mybatis.resultsandresult;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaintingMapper {

    @Insert("""
            INSERT INTO mybatis.paintings
              (name, artist_name, medium, year_created)
            VALUES
              (#{name},#{artistName},#{medium},#{yearCreated})""")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(Painting painting);

    @Update("""
            UPDATE  mybatis.paintings
              SET   name = #{name}
                  , artist_name = #{artistName}
                  , medium = #{medium}
                  , year_created = #{yearCreated}""")
    void update(Painting painting);

    @Select("SELECT * FROM mybatis.paintings WHERE id = #{id}")
    @Results(id = "paintingResultMap",
            value = {
                    @Result(property = "artistName", column = "artist_name"),
                    @Result(property = "yearCreated", column = "year_created")
            })
    Painting getById(Long id);

    @Select("SELECT * FROM mybatis.paintings")
    @ResultMap("paintingResultMap")
    List<Painting> getAll();

    @Select("SELECT * FROM mybatis.paintings WHERE artist_name = #{param1}")
    @ResultMap("paintingResultMap")
    List<Painting> getAllByArtist(String artistName);

    @Delete("DELETE FROM mybatis.paintings WHERE id = #{param1}")
    int deleteById(Long id);

    @Delete("DELETE FROM mybatis.paintings")
    int deleteAll();

    @Select("SELECT Count(*) FROM mybatis.paintings")
    int getCount();
}
