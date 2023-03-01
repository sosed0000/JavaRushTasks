package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);
        String[] xmlStrings = writer.toString().split("\n");
        writer = new StringWriter();
        for (int i = 0; i < xmlStrings.length; i++) {
            if (xmlStrings[i].trim().startsWith("<" + tagName) && !xmlStrings[i].contains("&lt;![CDATA")) {
                writer.write("<!--" + comment + "-->\n");
            }
            writer.write(xmlStrings[i] + "\n");
        }
        return writer.toString();
    }

    public static void main(String[] args) throws JAXBException {
        Test test = new Test();

        System.out.println(toXmlWithComment(test, "string", "222"));
    }
    @XmlRootElement
    public static class Test {
        public int number = 5;
        public String string = "string<![CDATA";
    }
}
