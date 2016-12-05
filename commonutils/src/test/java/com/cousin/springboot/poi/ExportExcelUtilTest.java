package com.cousin.springboot.poi;

import com.cousin.springboot.model.pojo.User;
import com.cousin.springboot.poi.export.ExportExcelUtil;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 测试Excel导出类
 * Created by cousin
 * CreatedTime 2016/12/5 22:16
 */
public class ExportExcelUtilTest {

    @Test
    public void test_export() throws IOException, IllegalAccessException {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setName("cousin" + i);
            user.setCreateTime(new Date());
            user.setPhone(i + "");
            userList.add(user);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<String[]> headNames = new ArrayList<>();
        headNames.add(new String[]{"用户名", "创建时间", "电话"});
        List<String[]> fieldNames = new ArrayList<>();
        fieldNames.add(new String[]{"name", "createTime", "phone"});


        LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
        map.put("demo", userList);

        ExportExcelUtil.ExportSetInfo exportSetInfo = new ExportExcelUtil.ExportSetInfo();
        exportSetInfo.setFieldNames(fieldNames);
        exportSetInfo.setObjsMap(map);
//        exportSetInfo.setTitles(new String[]{"测试"});
        exportSetInfo.setHeadNames(headNames);
//        exportSetInfo.setOut(byteArrayOutputStream);

        byteArrayOutputStream = ExportExcelUtil.export2Stream(exportSetInfo);

        FileOutputStream file = new FileOutputStream(new File("deom.xls"));


//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.flush();
//        objectOutputStream.close();
        byteArrayOutputStream.writeTo(file);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
    }


    @Test
    public void test_import() {

    }

}
