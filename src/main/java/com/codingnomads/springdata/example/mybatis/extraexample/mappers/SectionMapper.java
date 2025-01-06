/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.mybatis.extraexample.mappers;

import com.codingnomads.springdata.example.mybatis.extraexample.models.Section;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface SectionMapper {

    @Insert("INSERT INTO mybatis.sections (name) VALUES (#{name});")
    void insertNewSection(String name);

    @Insert("INSERT INTO mybatis.sections (name) VALUES (#{name});")
    @Options(useGeneratedKeys = true,keyColumn = "id", keyProperty = "id")
    void insertNewSectionObject(Section section);

    @Select("SELECT id, name FROM mybatis.sections WHERE id = #{param1};")
    @Results(id = "sectionResultMap",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(
                            property = "chapters",
                            column = "id",
                            javaType = List.class,
                            many =
                            @Many(
                                    select =
                                            "com.codingnomads.springdata.example.mybatis.extraexample.mappers.ChapterMapper.getChaptersBySectionId",
                                    fetchType = FetchType.LAZY))
            })
    Section getSectionById(Long sectionId);

    @Select("SELECT * FROM mybatis.sections WHERE name = #{param1};")
    @ResultMap("sectionResultMap")
    //although not enforced by the dB, we assume section names are unique
    Section getSectionByName(String sectionName);

    @Select("SELECT * FROM mybatis.sections")
    @ResultMap("sectionResultMap")
        //although not enforced by the dB, we assume section names are unique
    List<Section> getAllSections();


    @Delete("DELETE FROM mybatis.sections WHERE id = #{id};")
    int deleteSectionById(Long id);
}
