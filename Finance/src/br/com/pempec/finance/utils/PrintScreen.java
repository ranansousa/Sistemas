/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author Pempec
 */
public final class PrintScreen {

    static String data;

    static {

        data = new SimpleDateFormat("ddMMyyyy").format(new Date());

    }

    public static final File capture() {

        try {

            Toolkit toolkit = Toolkit.getDefaultToolkit();

            Dimension screenSize = toolkit.getScreenSize();

            Rectangle screenRect = new Rectangle(screenSize);

            Robot robot = new Robot();

            BufferedImage screenCapturedImage = robot.createScreenCapture(screenRect);

            String unique = PempecKeyGenerator.generateKey();

            ImageIO.write(screenCapturedImage, "jpg", new File(System.getProperty("user.dir") + "\\temp\\" + "capture_" + data + "_" + unique + ".jpg"));

            return new File(System.getProperty("user.dir") + "\\temp\\" + "capture_" + data + "_" + unique + ".jpg");

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }


    }
}
