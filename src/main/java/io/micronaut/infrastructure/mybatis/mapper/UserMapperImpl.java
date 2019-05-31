package io.micronaut.infrastructure.mybatis.mapper;

import javax.inject.Singleton;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.micronaut.core.user.FollowRelation;
import io.micronaut.core.user.User;

@Singleton
public class UserMapperImpl implements UserMapper {

	private final SqlSessionFactory sqlSessionFactory;
	
	public UserMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory; 
    }
	
	@Override
	public void insert(User user) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getUserMapper(sqlSession).insert(user);
            sqlSession.commit(); 
        }

	}

	@Override
	public User findByUsername(String username) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
            return getUserMapper(sqlSession).findByUsername(username); 
        }
	}

	@Override
	public User findByEmail(String email) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
            return getUserMapper(sqlSession).findByEmail(email); 
        }
	}

	@Override
	public User findById(String id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
            return getUserMapper(sqlSession).findById(id); 
        }
	}
	
	

	@Override
	public void update(User user) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getUserMapper(sqlSession).update(user);
            sqlSession.commit();
        }

	}

	@Override
	public FollowRelation findRelation(String userId, String targetId) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			return getUserMapper(sqlSession).findRelation(userId, targetId);
		}
	}

	@Override
	public void saveRelation(FollowRelation followRelation) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			getUserMapper(sqlSession).saveRelation(followRelation);
		}
	}

	/*
	@Override
	public void deleteRelation(FollowRelation followRelation) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			getUserMapper(sqlSession).deleteRelation(followRelation);
		}

	}
	*/
	
	private UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class); 
    }

}
