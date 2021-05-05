package Utils;


import java.util.UUID;

public class CreateIDCode {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");

    }

    public static Integer getUUIDInOrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId;
        System.out.println(orderId);
        return orderId;
    }


}
