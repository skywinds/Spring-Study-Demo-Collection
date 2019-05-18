package com.sas;

import com.google.protobuf.InvalidProtocolBufferException;
import com.sas.protoBuf.AddressBookPB;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: jinzhijie
 * @date: 2019/4/25 17:58
 * @description: To change this template use File | Settings | File Templates.
 */
public class testPBNew {

    private AddressBookPB.AddressBook addressBook;
    private AddressBookPB.AddressBook.Builder addressBook1;

    @Before
    public void init() {
        inti_old();
        inti_new();

        System.out.println(addressBook.toString());
        System.out.println(addressBook1.toString());
    }

    /**
     * 简化操作，不需要挨个的属性对象
     */
    private void inti_new() {
        addressBook1 = AddressBookPB.AddressBook.newBuilder();
        // addressBook1.getPeopleBuilder().setAge(18);
        addressBook1.getPeopleBuilder().setId(1000001);
        addressBook1.getPeopleBuilder().setEmail("93229090@qq.com");
        addressBook1.getPeopleBuilder().setName("张三");

        // 不要直接对PhoneList进行操作，不支持
        // addressBook1.getPeopleBuilder().addPhonesBuilder().setNumber(111111111).setType(AddressBookPB.Person.PhoneType.HOME);
        // addressBook1.getPeopleBuilder().addPhonesBuilder().setNumber(222222222).setType(AddressBookPB.Person.PhoneType.WORK);

        addressBook1.getPeopleBuilder().setTest(AddressBookPB.Person.Test.newBuilder());
        addressBook1.setHomeAddress("东一区");
    }

    private void inti_old() {
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
        System.out.println(addressBook1);

        /*assert addressBook1.hasPeople();

        System.out.println(addressBook1.getPeople().getTest() == null);

        for (AddressBookPB.Person.PhoneNumber phoneNumber : addressBook1.getPeople().getPhonesList()) {
            System.out.println(phoneNumber == null);
        }

        */
        AddressBookPB.AddressBook.Builder builder = addressBook1.toBuilder();

        AddressBookPB.Person.Builder person = addressBook1.getPeople().toBuilder();
        person.setAge(123);
        builder.setPeople(person);
        System.out.println(builder);
    }

    @Test
    public void generateTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();

        // c
        long c = System.currentTimeMillis();
        System.out.println("c=" + c);

        // x (2001-09-09 09:46:40)
        long x = 1000000000000L;
        ca.setTimeInMillis(x);
        System.out.println("x=" + sf.format(ca.getTime()));

        // y (最大值 2115081722504,2037-01-09 10:42:02)
        long y = c * 2 - x;
        System.out.println("y=" + y);
        ca.setTimeInMillis(y);
        System.out.println(sf.format(ca.getTime()));


        // y取2035-01-01
        ca.set(2035, Calendar.JANUARY, 1);
        y = ca.getTimeInMillis();
        System.out.println("2035-01-01=" + y);


        // y-c+x<c
        System.out.println(y - c + x);
        ca.setTimeInMillis(1493691380924L);
        System.out.println(sf.format(ca.getTime()));
        System.out.println((y - c + x) < c);
        System.out.println((y - c + x) - c);


    }

    @Test
    public void testTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        long x = 1000000000000L;
        ca.setTimeInMillis(x);
        System.out.println("x=" + sf.format(ca.getTime()));

        // y取2035-01-01
        ca.set(2035, Calendar.JANUARY, 1, 0, 0, 0);
        long y = ca.getTimeInMillis();
        System.out.println("y=" + sf.format(ca.getTime()));

        // c=2019-05-06 00:00:00
        ca.set(2019, Calendar.MAY, 11, 0, 0, 0);
        long c = ca.getTimeInMillis();
        System.out.println("c=" + sf.format(ca.getTime()));

        long vc = y - c + x;
        ca.setTimeInMillis(vc);
        System.out.println("vc=" + sf.format(ca.getTime()));


        ca.clear();
        ca.set(2017, Calendar.MAY, 2, 9, 46, 40);

        c = y - vc + x;
        ca.setTimeInMillis(c);
        System.out.println("c="+sf.format(ca.getTime()));
    }
}
