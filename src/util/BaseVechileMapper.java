package util;

import GBOMDemo.entity.BaseVechile;

import java.util.List;

/**
 * Created by wuyiming on 2017/9/18.
 */
public interface BaseVechileMapper {

    BaseVechile selectByPrimaryKey(String id);

    List<BaseVechile> findAll();

    int deleteByPrimaryKey(String id);

    int deleteAll();

    int insert(BaseVechile record);

    int insertSelective(BaseVechile record);

    int updateByPrimaryKeySelective(BaseVechile record);

    int selectByPrimaryKey(BaseVechile record);
}
