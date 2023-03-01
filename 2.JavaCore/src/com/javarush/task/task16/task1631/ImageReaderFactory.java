package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {

    public static ImageReader getImageReader (ImageTypes imageType) throws IllegalArgumentException {
        if (imageType == ImageTypes.JPG) {return new JpgReader();}
        else if (imageType == ImageTypes.BMP) {return new BmpReader();}
        else if (imageType == ImageTypes.PNG) {return new PngReader();}
        else throw new IllegalArgumentException();

    }
}
