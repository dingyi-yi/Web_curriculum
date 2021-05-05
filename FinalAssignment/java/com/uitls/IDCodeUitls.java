package com.uitls;

import java.util.UUID;
/**
 * @author dingxinlong
 * @date 2021年55月30日  20:55
 */
public class IDCodeUitls {



    public static String getUUIDInOrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId;
        String id=orderId.toString().substring(0,5);
        return id;
    }
}
