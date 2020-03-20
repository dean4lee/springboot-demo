package demo.springboot.shardingjdbc.service;

import demo.springboot.shardingjdbc.dao.DictDAO;
import demo.springboot.shardingjdbc.dao.OrderDAO;
import demo.springboot.shardingjdbc.domain.Dict;
import demo.springboot.shardingjdbc.domain.Order;
import demo.springboot.shardingjdbc.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private DictDAO dictDAO;

    @Transactional
    public void addOrder() {
        for (int i = 0; i < 10; i++) {
            //模拟分库策略，根据用户id分库
            Long userId = 1L;
            if (i % 2 == 0) {
                userId = 2L;
            }
            //添加订单
            Order order = new Order();
            order.setProductName("test");
            order.setUserId(userId);
            orderDAO.insertOrder(order);

            //添加订单详情
            OrderItem item = new OrderItem();
            item.setUserId(userId);
            item.setOrderId(order.getOrderId());
            item.setCreateTime(new Date());
            orderDAO.insertOrderItem(item);
        }
    }

    public List<Map> selectAll(){
        List<Map> maps = orderDAO.selectAll();
        return maps;
    }

    @Transactional
    public void addDict(){
        Dict dict = new Dict();
        dict.setName("haha");
        dictDAO.insert(dict);
    }
}
