package com.agritrade.platform.repository;

import com.agritrade.platform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户数据访问层
 * 
 * @author MikeCOjike
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 根据用户名查找用户
     */
    Optional<User> findByUsername(String username);
    
    /**
     * 根据邮箱查找用户
     */
    Optional<User> findByEmail(String email);
    
    /**
     * 检查用户名是否存在
     */
    boolean existsByUsername(String username);
    
    /**
     * 检查邮箱是否存在
     */
    boolean existsByEmail(String email);
    
    /**
     * 根据用户类型查找用户
     */
    Page<User> findByUserType(User.UserType userType, Pageable pageable);
    
    /**
     * 根据用户状态查找用户
     */
    Page<User> findByStatus(User.UserStatus status, Pageable pageable);
    
    /**
     * 根据用户名或邮箱模糊查询
     */
    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword% OR u.realName LIKE %:keyword%")
    Page<User> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    /**
     * 统计各用户类型的数量
     */
    @Query("SELECT u.userType, COUNT(u) FROM User u GROUP BY u.userType")
    Object[] countByUserType();
}
