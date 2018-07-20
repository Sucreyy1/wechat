package wechat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import wechat.app.dao.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String>,PagingAndSortingRepository<UserInfo,String> {

    UserInfo findByOpenId(String openId);

}
