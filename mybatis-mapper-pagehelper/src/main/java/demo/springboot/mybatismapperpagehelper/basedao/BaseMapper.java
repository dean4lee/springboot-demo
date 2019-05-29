package demo.springboot.mybatismapperpagehelper.basedao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author dean.lee
 */
//继承tkmapper中的接口，更多可查看官方文档
//Mapper是基础的增删改查，根据Example查询等
//MySqlMapper是对mysql的一些操作
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
