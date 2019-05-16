package com.test;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * tk基础mapper 不能放在mapper下
 *
 * @author junqiang.xiao
 * @date 2019/3/20 下午6:06
 */
public interface BaseMapper<T>  extends Mapper<T>, MySqlMapper<T> {
}
