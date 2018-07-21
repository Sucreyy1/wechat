package wechat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import wechat.app.dao.entity.ItemInfo;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<ItemInfo,String>,JpaRepository<ItemInfo,String> {

}
