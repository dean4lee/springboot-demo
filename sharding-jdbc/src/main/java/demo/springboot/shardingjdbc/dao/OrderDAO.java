package demo.springboot.shardingjdbc.dao;

import demo.springboot.shardingjdbc.domain.Order;
import demo.springboot.shardingjdbc.domain.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface OrderDAO {

    //使用表名是配置文件中的逻辑表名称
    @Insert("insert into t_order(user_id, product_name) values(#{userId}, #{productName})")
    //返回主键的值
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    int insertOrder(Order order);

    @Insert("insert into t_order_item(order_id, create_time, user_id) values(#{orderId}, #{createTime}, #{userId})")
    int insertOrderItem(OrderItem item);

    //表别名需要加as，不加会报错
    @Select("select * from t_order as o left join t_order_item as item on o.order_id = item.order_id order by o.order_id desc")
    List<Map> selectAll();
}
