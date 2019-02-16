package zijie.zeng.homework.study.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDao {
    List queryTest();
}
