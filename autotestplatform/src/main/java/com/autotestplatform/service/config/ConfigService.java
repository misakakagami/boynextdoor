package com.autotestplatform.service.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autotestplatform.contants.Contants;
import com.autotestplatform.dao.ConfigContentMapper;
import com.autotestplatform.dao.ConfigMapper;
import com.autotestplatform.entity.Config;
import com.autotestplatform.entity.ConfigContent;
import com.autotestplatform.service.base.BaseService;

@Service
public class ConfigService extends BaseService {

    @Autowired
    private ConfigMapper        configDao;

    @Autowired
    private ConfigContentMapper configContentDao;

    /**
     * @Description：储存\更新用户自定义配置至数据库
     * map:
     *  userId:用户id
     *  projectPageSize:项目分页(10)
     *  modelPageSize:模块分页(20)
     *  useCasePageSize:用例分页(20)
     *  planPageSize:计划分页(20)
     *  messagePageSize:消息分页(20)
     *  
     * @return void: 返回值类型
     * @throws
     */
    @Transactional
    public void saveUserSelfConfigToDao(Map<String, String> map) {
        logger.info("储存用户配置");
        //init
        Integer userId = Integer.parseInt(map.get("userId").toString());
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("userId", map.get("userId"));
        List<Config> configList = configDao.selectBySelective(queryMap);
        Config config = null;
        if (configList.size() == 0) {
            config = new Config(null, "用户自定义配置", 0, new Date(), new Date(), userId, userId);
            configDao.insert(config);
        } else {
            config = configList.get(0);
        }
        queryMap.put("configId", config.getConfigId().toString());
        List<ConfigContent> configContentList = configContentDao.selectListBySelective(queryMap);
        ConfigContent configContent = null;
        //save or update
        if (configContentList.size() > 0) {
            for (ConfigContent cc : configContentList) {
                if (Contants.PROJECTPAGESIZE.equals(cc.getConfigName())) {
                    //1 项目分页 默认10
                    cc.setConfigContent(map.get(Contants.PROJECTPAGESIZE));
                    configContentDao.updateByPrimaryKey(cc);
                } else if (Contants.MODELPAGESIZE.equals(cc.getConfigName())) {
                    //2 模块分页 默认20
                    cc.setConfigContent(map.get(Contants.MODELPAGESIZE));
                    configContentDao.updateByPrimaryKey(cc);
                } else if (Contants.USECASEPAGESIZE.equals(cc.getConfigName())) {
                    //3 用例分页 默认20
                    cc.setConfigContent(map.get(Contants.USECASEPAGESIZE));
                    configContentDao.updateByPrimaryKey(cc);
                } else if (Contants.PLANPAGESIZE.equals(cc.getConfigName())) {
                    //4 计划分页 默认20
                    cc.setConfigContent(map.get(Contants.PLANPAGESIZE));
                    configContentDao.updateByPrimaryKey(cc);
                } else if (Contants.MESSAGEPAGESIZE.equals(cc.getConfigName())) {
                    //5 消息分页 默认20
                    cc.setConfigContent(map.get(Contants.MESSAGEPAGESIZE));
                    configContentDao.updateByPrimaryKey(cc);
                }
            }

        } else {
            //1 项目分页 默认10
            configContent = new ConfigContent(null, config.getConfigId(), Contants.PROJECTPAGESIZE,
                    Contants.USERSELFCONFIG, "10", 0, new Date(), new Date(), userId, userId);
            configContentList.add(configContent);
            //2 模块分页 默认20
            configContent = new ConfigContent(null, config.getConfigId(), Contants.MODELPAGESIZE,
                    Contants.USERSELFCONFIG, "20", 0, new Date(), new Date(), userId, userId);
            configContentList.add(configContent);
            //3 用例分页 默认20
            configContent = new ConfigContent(null, config.getConfigId(), Contants.USECASEPAGESIZE,
                    Contants.USERSELFCONFIG, "20", 0, new Date(), new Date(), userId, userId);
            configContentList.add(configContent);
            //4 计划分页 默认20
            configContent = new ConfigContent(null, config.getConfigId(), Contants.PLANPAGESIZE,
                    Contants.USERSELFCONFIG, "20", 0, new Date(), new Date(), userId, userId);
            configContentList.add(configContent);
            //5 消息分页 默认20
            configContent = new ConfigContent(null, config.getConfigId(), Contants.MESSAGEPAGESIZE,
                    Contants.USERSELFCONFIG, "20", 0, new Date(), new Date(), userId, userId);
            configContentList.add(configContent);
            configContentDao.batchInsert(configContentList);
        }
    }

    /**
     * @Description：根据userId获取设置
     * @param userId
     * @return: 返回结果描述
     * @return List<Config>: 返回值类型
     * @throws
     */
    public List<Config> getConfigByUserId(Integer userId) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("userId", userId.toString());
        List<Config> cList = configDao.selectBySelective(queryMap);
        return cList;
    }

    /**
     * @Description：获取设置内容
     * @param map
     * @return: 返回结果描述
     * @return List<ConfigContent>: 返回值类型
     * @throws
     */
    public List<ConfigContent> getConfigContentByCondition(Map<String, String> queryMap) {
        List<ConfigContent> ccList = configContentDao.selectListBySelective(queryMap);
        return ccList;
    }
}
