package com.cousin.springboot.xml;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import org.apache.commons.io.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * xml和对象互转的工具类
 * Created by cousin
 * CreatedTime 2017/1/7 0:48
 */
public class XmlUtil {
    /**
     * 描述：将对象直接转换成String类型的 XML输出(无xml头输出)
     *
     * @param obj 要转的对象
     * @return String
     * @author cousin
     * @version 1.0
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //用来取消xml头部
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            //转换所有的适配字符，包括xml实体字符
            marshaller.setProperty(CharacterEscapeHandler.class.getName(),
                    (CharacterEscapeHandler) (ac, i, j, flag, writer) -> writer.write(ac, i, j));
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }


    /**
     * 将对象根据路径转换成xml文件
     *
     * @param obj  要转换的对象
     * @param path 存放的xml文件路径
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            // 创建输出流
            PrintWriter pw = null;
            try {
                File f = new File(path);
                Writer w = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
                pw = new PrintWriter(w);
//                FileWriter fw = null;
//                fw = new FileWriter(path);
                marshaller.marshal(obj, pw);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(pw);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将String类型的xml转换成对象
     */
    @SuppressWarnings("rawtypes")
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    /**
     * 将file类型的xml转换成对象
     */
    @SuppressWarnings("rawtypes")
    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStreamReader isp = null;
            try {
                isp = new InputStreamReader(new FileInputStream(xmlPath), "UTF-8");
                xmlObject = unmarshaller.unmarshal(isp);
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(isp);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

}
