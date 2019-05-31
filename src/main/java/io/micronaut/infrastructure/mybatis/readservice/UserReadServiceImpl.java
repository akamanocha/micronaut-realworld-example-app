package io.micronaut.infrastructure.mybatis.readservice;

import javax.inject.Singleton;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.micronaut.application.data.UserData;
import io.micronaut.infrastructure.mybatis.mapper.UserMapper;

@Singleton
public class UserReadServiceImpl implements UserReadService {
	
	private final SqlSessionFactory sqlSessionFactory;
	
	public UserReadServiceImpl(SqlSessionFactory sqlSessionFactory) {
		
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public UserData findByUsername(String username) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
            return getUserReadService(sqlSession).findByUsername(username); 
        }
	}

	@Override
	public UserData findById(String id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
            return getUserReadService(sqlSession).findById(id);
        }
	}
	
	private UserReadService getUserReadService(SqlSession sqlSession) {
        return sqlSession.getMapper(UserReadService.class); 
    }

}
