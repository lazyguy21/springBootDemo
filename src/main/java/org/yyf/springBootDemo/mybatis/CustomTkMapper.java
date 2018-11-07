package org.yyf.springBootDemo.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tk.mybatis.mapper.common.Mapper;

/**
 * Created by @author yyf on 2018/5/17.
 */
public interface CustomTkMapper<T> extends Mapper<T> {

  Logger logger = LoggerFactory.getLogger(CustomTkMapper.class);

  /**
   * https://github.com/abel533/Mapper/wiki/2.4-version
   *
   * @param t
   * @return
   */
  default Integer updateByPrimaryKeySelectiveWithVersion(T t) {
    int result = updateByPrimaryKeySelective(t);
    if (result == 0) {
      logger.error("use updateByPrimaryKeySelective update the object :{} failed, "
          + "reason may version or id didn't match", t);
      throw new RuntimeException();
    }
    return result;
  }

  /**
   * https://github.com/abel533/Mapper/wiki/2.4-version
   *
   * @param
   * @return
   */
  default Integer updateByExampleSelectiveWithVersion(T record, Object example) {
    Integer result = updateByExampleSelective(record, example);
    if (result == 0) {
      logger.error("use updateByExampleSelective update failed, record:{},example:{}"
          + "reason may version or id didn't match", record, example);
      throw new RuntimeException();
    }
    return result;
  }
}
