package org.kainos.ea.services;

import org.kainos.ea.daos.TestDao;

import java.sql.SQLException;
import java.util.List;

public class TestService {
    TestDao testDao;
    public TestService(final TestDao testDao) {
        this.testDao = testDao;
    }
    public List<String> testConnection() throws SQLException {
        return testDao.testConnection();
    }
}
