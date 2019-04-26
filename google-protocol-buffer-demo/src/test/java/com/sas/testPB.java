package com.sas;

import com.google.protobuf.InvalidProtocolBufferException;
import com.sas.protoBuf.AddressBookPB;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: jinzhijie
 * @date: 2019/4/25 17:58
 * @description: To change this template use File | Settings | File Templates.
 */
public class testPB {

    private AddressBookPB.AddressBook addressBook;

    @Before
    public void init() {
        //addressBook
        AddressBookPB.AddressBook.Builder addressBookBuilder = AddressBookPB.AddressBook.newBuilder();
        //Person
        AddressBookPB.Person.Builder personBuilder = AddressBookPB.Person.newBuilder();
        //phoneNumber
        AddressBookPB.Person.PhoneNumber.Builder phoneNumBuilder = AddressBookPB.Person.PhoneNumber.newBuilder();
        //Test
        AddressBookPB.Person.Test.Builder testBuilder = AddressBookPB.Person.Test.newBuilder();

        //设置多个PhoneNumber
        phoneNumBuilder.setNumber(111111111);
        phoneNumBuilder.setType(AddressBookPB.Person.PhoneType.HOME);
        personBuilder.addPhones(phoneNumBuilder);

        phoneNumBuilder.clear();
        phoneNumBuilder.setNumber(222222222);
        phoneNumBuilder.setType(AddressBookPB.Person.PhoneType.WORK);
        personBuilder.addPhones(phoneNumBuilder);

        personBuilder.setId(1000001);
        personBuilder.setName("张三");
        //这样会报null异常；
//        personBuilder.setEmail(null);
        personBuilder.setEmail("93229090@qq.com");
        personBuilder.setAge(18);

        //不添加时；
        //只有这个判断有效用
        //false addressBook1.getPeople().hasTest()

        //false addressBook1.getPeople().getTest() == ""
        //false addressBook1.getPeople().getTest() == null
        //false addressBook1.getPeople().getTest().equals("")
        //false Objects.isNull(addressBook1.getPeople().getTest())
        //true  Objects.nonNull(addressBook1.getPeople().getTest())

        //添加空的build：
        //true addressBook1.getPeople().hasTest()
        //false addressBook1.getPeople().getTest() == ""
        //false addressBook1.getPeople().getTest() == null
        //false addressBook1.getPeople().getTest().equals("")
        //false Objects.isNull(addressBook1.getPeople().getTest())
        //true  Objects.nonNull(addressBook1.getPeople().getTest())
        personBuilder.setTest(testBuilder);

        addressBookBuilder.setPeople(personBuilder);
        addressBookBuilder.setHomeAddress("东一区");

        addressBook = addressBookBuilder.build();

    }

    /**
     * 如果某个属性没有设置，那么，hasXXX时会报false;
     * 如果要获取这个未设置的值，那么会返回相关类型的默认值；
     * ex：string类型返回""     int32类型返回0
     */
    @Test
    public void testHasMathod() throws InvalidProtocolBufferException {
        byte[] param = addressBook.toByteArray();

        //反序列化
        AddressBookPB.AddressBook addressBook1 = AddressBookPB.AddressBook.parseFrom(param);

        assert addressBook1.hasPeople();

        System.out.println(addressBook1.getPeople().getTest() == null);

        for (AddressBookPB.Person.PhoneNumber phoneNumber : addressBook1.getPeople().getPhonesList()) {
            System.out.println(phoneNumber == null);
        }
    }
}
