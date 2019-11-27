package com.itheima.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.ContactDao;
import com.itheima.domain.ContactInfo;
import com.itheima.utils.JredisUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedisDaaoImpl implements ContactDao {


    /**
     *
     * @return
     */
    @Override
    public List<ContactInfo> queryAll() {

//        List<ContactInfo> list=new ArrayList<>();
//
//        Jedis jedis = JredisUtils.getJedis();
//        List<String> contacts = jedis.lrange("day06contactInfosData", 0, -1);
//
//        for (String str :
//                contacts) {
//            ObjectMapper mapper = new ObjectMapper();
//            try {
//                ContactInfo contact = mapper.readValue(str, ContactInfo.class);
//                list.add(contact);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        jedis.close();
//        return list;
return null;
    }

    @Override
    public int delContact(String id) {
        return 0;
    }

    @Override
    public List<ContactInfo> queryById(String id) {
        return null;
    }

    @Override
    public int update(ContactInfo contact) {
        return 0;
    }

    /**
     *
     * @param contact
     * @return
     */
    @Override
    public int addContact(ContactInfo contact) {
        Jedis jedis = JredisUtils.getJedis();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String contact_Json = mapper.writeValueAsString(contact);
            jedis.rpush("day06contactInfosData",contact_Json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jedis.close();
        return 1;
    }

    @Override
    public List<ContactInfo> queryByPage(int offSet, int pageSize) {
        return null;
    }

    @Override
    public int queryCounts() {
        return 0;
    }

    @Override
    public int checkEmail(String email) {
        return 0;
    }

    @Override
    public List<ContactInfo> queryByPageRedis(int offSet, int pageSize) {
        List<ContactInfo> list=new ArrayList<>();

        Jedis jedis = JredisUtils.getJedis();
        List<String> contacts = jedis.lrange("day06contactInfosData", offSet,(offSet+pageSize-1));

        for (String str :
                contacts) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                ContactInfo contact = mapper.readValue(str, ContactInfo.class);
                list.add(contact);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        jedis.close();
        return list;
    }

}
