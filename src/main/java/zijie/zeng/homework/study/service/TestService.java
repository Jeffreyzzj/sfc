package zijie.zeng.homework.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zijie.zeng.homework.study.dao.TestDao;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestDao testDao;
    public List test1() {
        List list = testDao.queryTest();
        return list;
    }
}
