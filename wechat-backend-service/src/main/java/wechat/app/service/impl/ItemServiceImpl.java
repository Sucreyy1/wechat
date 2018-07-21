package wechat.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat.app.dao.entity.ItemInfo;
import wechat.app.repository.ItemRepository;
import wechat.app.service.IItemService;

import java.util.Date;

@Service
public class ItemServiceImpl implements IItemService {

    private static Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public int addToCar(JSONObject test) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setItemId(test.getString("itemId"));
        itemInfo.setItemAmount(test.getString("itemAmount"));
        itemInfo.setItemName(test.getString("itemName"));
        itemInfo.setItemPrice(test.getBigDecimal("itemPrice"));
        itemInfo.setThreshold(test.getInteger("threshold"));
        itemInfo.setCreateTime(new Date());
        itemInfo.setUpdateTime(new Date());
        itemRepository.save(itemInfo);
        return 0;
    }

    @Override
    public int purchase(JSONObject jsonObject) {
        return 0;
    }

    @Override
    public int cancelOrder(JSONObject jsonObject) {
        return 0;
    }

}
