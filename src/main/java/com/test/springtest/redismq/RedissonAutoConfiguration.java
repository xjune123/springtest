package com.test.springtest.redismq;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自动装配类
 * 单例模式、哨兵模式，集群模式
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/3/19
 */


@Configuration
@ConditionalOnClass(Config.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedissonAutoConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 哨兵模式自动装配
     *
     * @return redissonSentinel 哨兵模式
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redis.sentinel.nodes")
    RedissonClient redissonSentinel() {
        Config config = new Config();
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
        sentinelServersConfig.setMasterName(redisProperties.getSentinel().getMaster());
        //增加redis 前缀
        List<String> nodes = Arrays.asList(redisProperties.getSentinel().getNodes().split(","));
        for (String node : nodes) {
            sentinelServersConfig.addSentinelAddress("redis://" + node);
        }
        //
        // sentinelServersConfig.addSentinelAddress(redisProperties.getSentinel().getNodes().split(","));
        sentinelServersConfig.setDatabase(redisProperties.getDatabase());
        if (redisProperties.getPassword() != null) {
            sentinelServersConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 单机模式自动装配
     *
     * @return redissonSingle 单机实例
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redis.url")
    RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        // format as redis://127.0.0.1:7181 or rediss://127.0.0.1:7181 for SSL
        //String schema = redisProperties.isSsl() ? "rediss://" : "redis://";
        singleServerConfig.setAddress(redisProperties.getUrl());
        singleServerConfig.setDatabase(redisProperties.getDatabase());
        if (redisProperties.getPassword() != null) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 主从模式
     *
     * @return redissonSingle
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redis.cluster.nodes")
    RedissonClient redissonClusters() {
        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
        List<String> clusterNodes = redisProperties.getCluster().getNodes();
        List<String> clusteNewNodes = new ArrayList<>();
        for (int i = 0; i < clusterNodes.size(); i++) {
            clusteNewNodes.add("redis://" + clusterNodes.get(i));
        }
        Config config = new Config();
        //这是用的集群server
        config.useClusterServers()
                //设置集群状态扫描时间
                .setScanInterval(2000)
                .addNodeAddress(clusteNewNodes.toArray(new String[clusteNewNodes.size()]));
        if (!ObjectUtils.isEmpty(redisProperties.getPassword())) {
            config.useClusterServers().setPassword(redisProperties.getPassword());
        }
        //可通过打印redisson.getConfig().toJSON().toString()来检测是否配置成功
        return Redisson.create(config);
    }

}