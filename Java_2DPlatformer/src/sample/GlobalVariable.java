package sample;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;

import javafx.scene.control.Label;

public class GlobalVariable {
    public static int Resolution_Height;
    public static int Resolution_Width;
    public static int Stage_LayoutX;
    public static int Stage_LayoutY;





    public static void reader()throws Exception {//чтение построчно
        BufferedReader BuffReader = new BufferedReader(new FileReader("Config_IN.txt"));
        BufferedWriter BuffWriter = new BufferedWriter(new FileWriter("Config_OUT.txt"));
        String string_config;
        while((string_config = BuffReader.readLine())!=null){
            if(string_config.equals("Resolution_Height:")){
                Resolution_Height = Integer.parseInt(BuffReader.readLine());
                BuffWriter.append("Resolution_Height:\n");
                BuffWriter.append(String.valueOf(Resolution_Height)).append(String.valueOf('\n')).append(String.valueOf('\n'));
            }
            if(string_config.equals("Resolution_Width:")){
                Resolution_Width = Integer.parseInt(BuffReader.readLine());
                BuffWriter.append("Resolution_Width:\n");
                BuffWriter.append(String.valueOf(Resolution_Width)).append(String.valueOf('\n')).append(String.valueOf('\n'));
            }
            if(string_config.equals("Stage_LayoutX:")){
                Stage_LayoutX = Integer.parseInt(BuffReader.readLine());
                BuffWriter.append("Stage_LayoutX:\n");
                BuffWriter.append(String.valueOf(Stage_LayoutX)).append(String.valueOf('\n')).append(String.valueOf('\n'));
            }
            if(string_config.equals("Stage_LayoutY:")){
                Stage_LayoutY = Integer.parseInt(BuffReader.readLine());
                BuffWriter.append("Stage_LayoutY:\n");
                BuffWriter.append(String.valueOf(Stage_LayoutY)).append(String.valueOf('\n')).append(String.valueOf('\n'));
            }
        }
        BuffWriter.flush();
        BuffReader.close();
        BuffWriter.close();
    }

    public static void writer()throws Exception {//чтение построчно
        BufferedWriter BuffWriter = new BufferedWriter(new FileWriter("Config_IN.txt"));
        BufferedReader BuffReader = new BufferedReader(new FileReader("Config_OUT.txt"));
        String string_rider;

        while((string_rider = BuffReader.readLine()) != null){
            if(string_rider.equals("Resolution_Height:")) {
                BuffWriter.append("Resolution_Height:\n");
                BuffWriter.append(String.valueOf(Resolution_Height)).append(String.valueOf('\n')).append(String.valueOf('\n'));
            }
            if(string_rider.equals("Resolution_Width:")) {
                BuffWriter.append("Resolution_Width:\n");
                BuffWriter.append(String.valueOf(Resolution_Width)).append(String.valueOf('\n')).append(String.valueOf('\n'));
            }
            if(string_rider.equals("Stage_LayoutX:")) {
                BuffWriter.append("Stage_LayoutX:\n");
                BuffWriter.append(String.valueOf(Stage_LayoutX)).append(String.valueOf('\n')).append(String.valueOf('\n'));
            }
            if(string_rider.equals("Stage_LayoutY:")) {
                BuffWriter.append("Stage_LayoutY:\n");
                BuffWriter.append(String.valueOf(Stage_LayoutY)).append(String.valueOf('\n')).append(String.valueOf('\n'));
            }
        }
        BuffReader.close();
        BuffWriter.flush();
        BuffWriter.close();
    }


    public static void Set_Setting(Stage stage){//установка начальных настроек
        stage.setHeight(GlobalVariable.Resolution_Height);
        stage.setWidth(GlobalVariable.Resolution_Width);
        stage.setX(Stage_LayoutX);
        stage.setY(Stage_LayoutY);
    }

    public static void change_resolution(Stage stage, ImageView image_view, int width, int height, int x, int y){//смена разрешения
        Resolution_Height = height;
        Resolution_Width = width;
        Stage_LayoutX = x;
        Stage_LayoutY = y;
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setX(x);
        stage.setY(y);
        image_view.setFitWidth(width);
        image_view.setFitHeight(height);
    }

    public static void change_text_resolution(Label label){//смена разрешения
       label.setLayoutX(Resolution_Width/2 - 85);
    }

}
