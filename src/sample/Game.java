package sample;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.animation.Timeline;
import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.*;
import java.util.*;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.*;
import javafx.stage.WindowEvent;
import jdk.internal.dynalink.beans.StaticClass;
import jdk.nashorn.internal.objects.NativeString;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;


abstract class Obsticle extends Group{
    protected double x;
    protected double y;
    protected double r;
    protected double theta;
    protected Player player;
    protected double height;
    protected double width;
    Obsticle(double x,double y,double r,double theta,double height,double width){
        this.x=x;
        this.y=y;
        this.r=r;
        this.theta=theta;
        this.height = height;
        this.width = width;
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public abstract void move();
    public abstract void rotate();
    public abstract boolean collide(Player player);
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public void setY(double y){
        this.y=y;
    }
    public double getR(){
        return this.r;
    }
    public void setR(double r){
        this.r=r;
    }
    public double getTheta(){
        return this.theta;
    }
    public void setTheta(double theta){
        this.theta = theta;
    }
    public double getHeight(){
        return this.height;
    }
    public double getWidth(){
        return this.width;
    }
    public double getDiff(){return 0;}
    public void setDiff(double change){}
    public double getChange(){return 0;}
    public void setChange(double change){}

}

class Rectangle extends Obsticle{
    private Line l1,l2,l3,l4;

    Rectangle(double x,double y,double r,double theta,double height,double width){
        super(x,y,r,theta,height,width);
        l1 = new Line(x+r*Math.cos(Math.PI*theta/180),y-r*Math.sin(Math.PI*theta/180),x+r*Math.cos(Math.PI*(theta+90)/180),y-r*Math.sin(Math.PI*(theta+90)/180));
        l2 = new Line(x+r*Math.cos(Math.PI*(theta+90)/180),y-r*Math.sin(Math.PI*(theta+90)/180),x+r*Math.cos(Math.PI*(theta+180)/180),y-r*Math.sin(Math.PI*(theta+180)/180));
        l3 = new Line(x+r*Math.cos(Math.PI*(theta+180)/180),y-r*Math.sin(Math.PI*(theta+180)/180),x+r*Math.cos(Math.PI*(theta+270)/180),y-r*Math.sin(Math.PI*(theta+270)/180));
        l4 = new Line(x+r*Math.cos(Math.PI*(theta+270)/180),y-r*Math.sin(Math.PI*(theta+270)/180),x+r*Math.cos(Math.PI*(theta+360)/180),y-r*Math.sin(Math.PI*(theta+360)/180));
        l1.setStroke(Color.RED);l1.setStrokeWidth(width);
        l2.setStroke(Color.YELLOW);l2.setStrokeWidth(width);
        l3.setStroke(Color.BLUE);l3.setStrokeWidth(width);
        l4.setStroke(Color.GREEN);l4.setStrokeWidth(width);
        getChildren().add(l1);
        getChildren().add(l2);
        getChildren().add(l3);
        getChildren().add(l4);
    }
    public void move(){
        l1.setStartX(x+r*Math.cos(Math.PI*theta/180)); l1.setStartY(y-r*Math.sin(Math.PI*theta/180)); l1.setEndX(x+r*Math.cos(Math.PI*(theta+90)/180)); l1.setEndY(y-r*Math.sin(Math.PI*(theta+90)/180));
        l2.setStartX(x+r*Math.cos(Math.PI*(theta+90)/180)); l2.setStartY(y-r*Math.sin(Math.PI*(theta+90)/180)); l2.setEndX(x+r*Math.cos(Math.PI*(theta+180)/180)); l2.setEndY(y-r*Math.sin(Math.PI*(theta+180)/180));
        l3.setStartX(x+r*Math.cos(Math.PI*(theta+180)/180)); l3.setStartY(y-r*Math.sin(Math.PI*(theta+180)/180)); l3.setEndX(x+r*Math.cos(Math.PI*(theta+270)/180)); l3.setEndY(y-r*Math.sin(Math.PI*(theta+270)/180));
        l4.setStartX(x+r*Math.cos(Math.PI*(theta+270)/180)); l4.setStartY(y-r*Math.sin(Math.PI*(theta+270)/180)); l4.setEndX(x+r*Math.cos(Math.PI*(theta+360)/180)); l4.setEndY(y-r*Math.sin(Math.PI*(theta+360)/180));
    }
    public void rotate(){
        this.theta+=2;
        l1.setStartX(x+r*Math.cos(Math.PI*theta/180)); l1.setStartY(y-r*Math.sin(Math.PI*theta/180)); l1.setEndX(x+r*Math.cos(Math.PI*(theta+90)/180)); l1.setEndY(y-r*Math.sin(Math.PI*(theta+90)/180));
        l2.setStartX(x+r*Math.cos(Math.PI*(theta+90)/180)); l2.setStartY(y-r*Math.sin(Math.PI*(theta+90)/180)); l2.setEndX(x+r*Math.cos(Math.PI*(theta+180)/180)); l2.setEndY(y-r*Math.sin(Math.PI*(theta+180)/180));
        l3.setStartX(x+r*Math.cos(Math.PI*(theta+180)/180)); l3.setStartY(y-r*Math.sin(Math.PI*(theta+180)/180)); l3.setEndX(x+r*Math.cos(Math.PI*(theta+270)/180)); l3.setEndY(y-r*Math.sin(Math.PI*(theta+270)/180));
        l4.setStartX(x+r*Math.cos(Math.PI*(theta+270)/180)); l4.setStartY(y-r*Math.sin(Math.PI*(theta+270)/180)); l4.setEndX(x+r*Math.cos(Math.PI*(theta+360)/180)); l4.setEndY(y-r*Math.sin(Math.PI*(theta+360)/180));
    }
    public boolean collide(Player player){
        Ball b = player.getBall();
        String c = b.getColour();
        double theta1 = theta%360;
        double theta2 = (theta+90)%360;
        double theta3 = (theta+180)%360;
        double theta4 = (theta+270)%360;
        if (b.getY()>this.getY() && b.getY()>this.getY()-this.getHeight()/2 && b.getY()<this.getY()+this.getHeight()/2){
            if (theta1<270 && theta1>180){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l1.getStartX(); double y1 = l1.getStartY(); double x2 = l1.getEndX(); double y2 = l1.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("red")){
                            return true;
                        }
                    }
                }
            }
            else if (theta2<270 && theta2>180){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l2.getStartX(); double y1 = l2.getStartY(); double x2 = l2.getEndX(); double y2 = l2.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("yellow")){
                            return true;
                        }
                    }
                }
            }
            else if (theta3<270 && theta3>180){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l3.getStartX(); double y1 = l3.getStartY(); double x2 = l3.getEndX(); double y2 = l3.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("blue")){
                            return true;
                        }
                    }
                }
            }
            else if (theta4<270 && theta4>180){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l4.getStartX(); double y1 = l4.getStartY(); double x2 = l4.getEndX(); double y2 = l4.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("green")){
                            return true;
                        }
                    }
                }
            }
        }
        else if (b.getY()>this.getY()-this.getHeight()/2 && b.getY()<this.getY()+this.getHeight()/2){
            if (theta1<90 && theta1>0){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l1.getStartX(); double y1 = l1.getStartY(); double x2 = l1.getEndX(); double y2 = l1.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("red")){
                            return true;
                        }
                    }
                }
            }
            else if (theta2<90 && theta2>0){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l2.getStartX(); double y1 = l2.getStartY(); double x2 = l2.getEndX(); double y2 = l2.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("yellow")){
                            return true;
                        }
                    }
                }
            }
            else if (theta3<90 && theta3>0){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l3.getStartX(); double y1 = l3.getStartY(); double x2 = l3.getEndX(); double y2 = l3.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("blue")){
                            return true;
                        }
                    }
                }
            }
            else if (theta4<90 && theta4>0){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l4.getStartX(); double y1 = l4.getStartY(); double x2 = l4.getEndX(); double y2 = l4.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("green")){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

class MyCircle extends Obsticle{
    protected Arc arc1,arc2,arc3,arc4;
    MyCircle(double x,double y,double r,double theta,double height,double width){
        super(x,y,r,theta,height,width);
        arc1 = new Arc(x,y,r,r,0.0d,90);
        arc2 = new Arc(x,y,r,r,90.0d,90);
        arc3 = new Arc(x,y,r,r,180.0d,90);
        arc4 = new Arc(x,y,r,r,270.0d,90);

        arc1.setType(ArcType.OPEN);
        arc2.setType(ArcType.OPEN);
        arc3.setType(ArcType.OPEN);
        arc4.setType(ArcType.OPEN);
        arc1.setStroke(Color.RED);arc1.setStrokeWidth(width);arc1.setFill(Color.TRANSPARENT);
        arc2.setStroke(Color.YELLOW);arc2.setStrokeWidth(width);arc2.setFill(Color.TRANSPARENT);
        arc3.setStroke(Color.BLUE);arc3.setStrokeWidth(width);arc3.setFill(Color.TRANSPARENT);
        arc4.setStroke(Color.GREEN);arc4.setStrokeWidth(width);arc4.setFill(Color.TRANSPARENT);

        getChildren().add(arc1);
        getChildren().add(arc2);
        getChildren().add(arc3);
        getChildren().add(arc4);
    }
    public void move(){
        arc1.setCenterX(x);arc1.setCenterY(y);arc1.setRadiusX(r);arc1.setRadiusY(r);arc1.setStartAngle(theta);arc1.setLength(90);
        arc2.setCenterX(x);arc2.setCenterY(y);arc2.setRadiusX(r);arc2.setRadiusY(r);arc2.setStartAngle(theta+90);arc1.setLength(90);
        arc3.setCenterX(x);arc3.setCenterY(y);arc3.setRadiusX(r);arc3.setRadiusY(r);arc3.setStartAngle(theta+180);arc1.setLength(90);
        arc4.setCenterX(x);arc4.setCenterY(y);arc4.setRadiusX(r);arc4.setRadiusY(r);arc4.setStartAngle(theta+270);arc1.setLength(90);
    }
    public void rotate(){
        theta+=2;
        arc1.setStartAngle(theta);
        arc2.setStartAngle(theta+90);
        arc3.setStartAngle(theta+180);
        arc4.setStartAngle(theta+270);
    }
    public boolean collide(Player player){
        Ball b = player.getBall();
        String c = b.getColour();
        double theta1 = theta%360;
        double theta2 = (theta+90)%360;
        double theta3 = (theta+180)%360;
        double theta4 = (theta+270)%360;
        if (b.getY()>this.getY()){
            if (theta1<270 && theta1>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+this.getWidth()/2 && val>=this.getR()){
                    if (!c.equals("red")){
                        return true;
                    }
                }
            }
            if (theta2<270 && theta2>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+this.getWidth()/2 && val>=this.getR()){
                    if (!c.equals("yellow")){
                        return true;
                    }
                }
            }
            if (theta3<270 && theta3>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+this.getWidth()/2 && val>=this.getR()){
                    if (!c.equals("blue")){
                        return true;
                    }
                }
            }
            if (theta4<270 && theta4>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+this.getWidth()/2 && val>=this.getR()){
                    if (!c.equals("green")){
                        return true;
                    }
                }
            }
        }
        else{
            if (theta1<90 && theta1>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+this.getWidth()/2 && val>=this.getR()){
                    if (!c.equals("red")){
                        return true;
                    }
                }
            }
            if (theta2<90 && theta2>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+this.getWidth()/2 && val>=this.getR()){
                    if (!c.equals("yellow")){
                        return true;
                    }
                }
            }
            if (theta3<90 && theta3>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+this.getWidth()/2 && val>=this.getR()){
                    if (!c.equals("blue")){
                        return true;
                    }
                }
            }
            if (theta4<90 && theta4>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+this.getWidth()/2 && val>=this.getR()){
                    if (!c.equals("green")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class DoubleCircle extends MyCircle{
    protected Arc arc5,arc6,arc7,arc8;
    protected double theta1;
    DoubleCircle(double x,double y,double r,double theta,double theta1,double height,double width){
        super(x,y,r,theta,height,width);
        this.theta1 = theta1;
        arc5 = new Arc(x,y,r+width,r+width,theta1,90);
        arc6 = new Arc(x,y,r+width,r+width,theta1-90,90);
        arc7 = new Arc(x,y,r+width,r+width,theta1-180,90);
        arc8 = new Arc(x,y,r+width,r+width,theta1-270,90);

        arc1.setType(ArcType.OPEN);arc1.setStroke(Color.RED);arc1.setStrokeWidth(width);arc1.setFill(Color.TRANSPARENT);
        arc2.setType(ArcType.OPEN);arc2.setStroke(Color.YELLOW);arc2.setStrokeWidth(width);arc2.setFill(Color.TRANSPARENT);
        arc3.setType(ArcType.OPEN);arc3.setStroke(Color.BLUE);arc3.setStrokeWidth(width);arc3.setFill(Color.TRANSPARENT);
        arc4.setType(ArcType.OPEN);arc4.setStroke(Color.GREEN);arc4.setStrokeWidth(width);arc4.setFill(Color.TRANSPARENT);
        arc5.setType(ArcType.OPEN);arc5.setStroke(Color.RED);arc5.setStrokeWidth(width);arc5.setFill(Color.TRANSPARENT);
        arc6.setType(ArcType.OPEN);arc6.setStroke(Color.YELLOW);arc6.setStrokeWidth(width);arc6.setFill(Color.TRANSPARENT);
        arc7.setType(ArcType.OPEN);arc7.setStroke(Color.BLUE);arc7.setStrokeWidth(width);arc7.setFill(Color.TRANSPARENT);
        arc8.setType(ArcType.OPEN);arc8.setStroke(Color.GREEN);arc8.setStrokeWidth(width);arc8.setFill(Color.TRANSPARENT);
        getChildren().add(arc5);
        getChildren().add(arc6);
        getChildren().add(arc7);
        getChildren().add(arc8);
    }
    public double getTheta1(){
        return this.theta1;
    }
    public void setTheta1(double angle){
        this.theta1 = angle;
    }
    public void move(){
        arc1.setCenterX(x);arc1.setCenterY(y);arc1.setRadiusX(r);arc1.setRadiusY(r);arc1.setStartAngle(theta);arc1.setLength(90);
        arc2.setCenterX(x);arc2.setCenterY(y);arc2.setRadiusX(r);arc2.setRadiusY(r);arc2.setStartAngle(theta+90);arc2.setLength(90);
        arc3.setCenterX(x);arc3.setCenterY(y);arc3.setRadiusX(r);arc3.setRadiusY(r);arc3.setStartAngle(theta+180);arc3.setLength(90);
        arc4.setCenterX(x);arc4.setCenterY(y);arc4.setRadiusX(r);arc4.setRadiusY(r);arc4.setStartAngle(theta+270);arc4.setLength(90);

        arc5.setCenterX(x);arc5.setCenterY(y);arc5.setRadiusX(r+width);arc5.setRadiusY(r+width);arc5.setStartAngle(theta1);arc5.setLength(90);
        arc6.setCenterX(x);arc6.setCenterY(y);arc6.setRadiusX(r+width);arc6.setRadiusY(r+width);arc6.setStartAngle(theta1-90);arc6.setLength(90);
        arc7.setCenterX(x);arc7.setCenterY(y);arc7.setRadiusX(r+width);arc7.setRadiusY(r+width);arc7.setStartAngle(theta1-180);arc7.setLength(90);
        arc8.setCenterX(x);arc8.setCenterY(y);arc8.setRadiusX(r+width);arc8.setRadiusY(r+width);arc8.setStartAngle(theta1-270);arc8.setLength(90);

    }
    public void rotate(){
        this.theta+=2;
        this.theta1-=2;
        arc1.setStartAngle(theta);
        arc2.setStartAngle(theta+90);
        arc3.setStartAngle(theta+180);
        arc4.setStartAngle(theta+270);
        arc5.setStartAngle(theta1);
        arc6.setStartAngle(theta1-90);
        arc7.setStartAngle(theta1-180);
        arc8.setStartAngle(theta1-270);
    }
    public boolean collide(Player player){
        Ball b = player.getBall();
        String c = b.getColour();
        double theta1 = theta%360;
        double theta2 = (theta+90)%360;
        double theta3 = (theta+180)%360;
        double theta4 = (theta+270)%360;
        if (b.getY()>this.getY()){
            if (theta1<270 && theta1>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+2*this.getWidth() && val>=this.getR()){
                    if (!c.equals("red")){
                        return true;
                    }
                }
            }
            if (theta2<270 && theta2>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+2*this.getWidth() && val>=this.getR()){
                    if (!c.equals("yellow")){
                        return true;
                    }
                }
            }
            if (theta3<270 && theta3>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+2*this.getWidth() && val>=this.getR()){
                    if (!c.equals("blue")){
                        return true;
                    }
                }
            }
            if (theta4<270 && theta4>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+2*this.getWidth() && val>=this.getR()){
                    if (!c.equals("green")){
                        return true;
                    }
                }
            }
        }
        else{
            if (theta1<90 && theta1>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+2*this.getWidth() && val>=this.getR()){
                    if (!c.equals("red")){
                        return true;
                    }
                }
            }
            if (theta2<90 && theta2>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+2*this.getWidth() && val>=this.getR()){
                    if (!c.equals("yellow")){
                        return true;
                    }
                }
            }
            if (theta3<90 && theta3>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+2*this.getWidth() && val>=this.getR()){
                    if (!c.equals("blue")){
                        return true;
                    }
                }
            }
            if (theta4<90 && theta4>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+2*this.getWidth() && val>=this.getR()){
                    if (!c.equals("green")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class TripleCircle extends MyCircle{
    protected Arc arc5,arc6,arc7,arc8,arc9,arc10,arc11,arc12;
    protected double theta1;
    TripleCircle(double x,double y,double r,double theta,double theta1,double height,double width){
        super(x,y,r,theta,height,width);
        this.theta1 = theta1;
        arc5 = new Arc(x,y,r+width,r+width,theta1,90);
        arc6 = new Arc(x,y,r+width,r+width,theta1-90,90);
        arc7 = new Arc(x,y,r+width,r+width,theta1-180,90);
        arc8 = new Arc(x,y,r+width,r+width,theta1-270,90);

        arc9 = new Arc(x,y,r+2*width,r+2*width,theta,90);
        arc10 = new Arc(x,y,r+2*width,r+2*width,theta+90,90);
        arc11 = new Arc(x,y,r+2*width,r+2*width,theta+180,90);
        arc12 = new Arc(x,y,r+2*width,r+2*width,theta+270,90);

        arc1.setType(ArcType.OPEN);arc1.setStroke(Color.RED);arc1.setStrokeWidth(width);arc1.setFill(Color.TRANSPARENT);
        arc2.setType(ArcType.OPEN);arc2.setStroke(Color.YELLOW);arc2.setStrokeWidth(width);arc2.setFill(Color.TRANSPARENT);
        arc3.setType(ArcType.OPEN);arc3.setStroke(Color.BLUE);arc3.setStrokeWidth(width);arc3.setFill(Color.TRANSPARENT);
        arc4.setType(ArcType.OPEN);arc4.setStroke(Color.GREEN);arc4.setStrokeWidth(width);arc4.setFill(Color.TRANSPARENT);
        arc5.setType(ArcType.OPEN);arc5.setStroke(Color.RED);arc5.setStrokeWidth(width);arc5.setFill(Color.TRANSPARENT);
        arc6.setType(ArcType.OPEN);arc6.setStroke(Color.YELLOW);arc6.setStrokeWidth(width);arc6.setFill(Color.TRANSPARENT);
        arc7.setType(ArcType.OPEN);arc7.setStroke(Color.BLUE);arc7.setStrokeWidth(width);arc7.setFill(Color.TRANSPARENT);
        arc8.setType(ArcType.OPEN);arc8.setStroke(Color.GREEN);arc8.setStrokeWidth(width);arc8.setFill(Color.TRANSPARENT);
        arc9.setType(ArcType.OPEN);arc9.setStroke(Color.RED);arc9.setStrokeWidth(width);arc9.setFill(Color.TRANSPARENT);
        arc10.setType(ArcType.OPEN);arc10.setStroke(Color.YELLOW);arc10.setStrokeWidth(width);arc10.setFill(Color.TRANSPARENT);
        arc11.setType(ArcType.OPEN);arc11.setStroke(Color.BLUE);arc11.setStrokeWidth(width);arc11.setFill(Color.TRANSPARENT);
        arc12.setType(ArcType.OPEN);arc12.setStroke(Color.GREEN);arc12.setStrokeWidth(width);arc12.setFill(Color.TRANSPARENT);
        getChildren().add(arc5);
        getChildren().add(arc6);
        getChildren().add(arc7);
        getChildren().add(arc8);
        getChildren().add(arc9);
        getChildren().add(arc10);
        getChildren().add(arc11);
        getChildren().add(arc12);
    }
    public double getTheta1(){
        return this.theta1;
    }
    public void setTheta1(double angle){
        this.theta1 = angle;
    }
    public void move(){
        arc1.setCenterX(x);arc1.setCenterY(y);arc1.setRadiusX(r);arc1.setRadiusY(r);arc1.setStartAngle(theta);arc1.setLength(90);
        arc2.setCenterX(x);arc2.setCenterY(y);arc2.setRadiusX(r);arc2.setRadiusY(r);arc2.setStartAngle(theta+90);arc2.setLength(90);
        arc3.setCenterX(x);arc3.setCenterY(y);arc3.setRadiusX(r);arc3.setRadiusY(r);arc3.setStartAngle(theta+180);arc3.setLength(90);
        arc4.setCenterX(x);arc4.setCenterY(y);arc4.setRadiusX(r);arc4.setRadiusY(r);arc4.setStartAngle(theta+270);arc4.setLength(90);

        arc5.setCenterX(x);arc5.setCenterY(y);arc5.setRadiusX(r+width);arc5.setRadiusY(r+width);arc5.setStartAngle(theta1);arc5.setLength(90);
        arc6.setCenterX(x);arc6.setCenterY(y);arc6.setRadiusX(r+width);arc6.setRadiusY(r+width);arc6.setStartAngle(theta1-90);arc6.setLength(90);
        arc7.setCenterX(x);arc7.setCenterY(y);arc7.setRadiusX(r+width);arc7.setRadiusY(r+width);arc7.setStartAngle(theta1-180);arc7.setLength(90);
        arc8.setCenterX(x);arc8.setCenterY(y);arc8.setRadiusX(r+width);arc8.setRadiusY(r+width);arc8.setStartAngle(theta1-270);arc8.setLength(90);

        arc9.setCenterX(x);arc9.setCenterY(y);arc9.setRadiusX(r+2*width);arc9.setRadiusY(r+2*width);arc9.setStartAngle(theta);arc9.setLength(90);
        arc10.setCenterX(x);arc10.setCenterY(y);arc10.setRadiusX(r+2*width);arc10.setRadiusY(r+2*width);arc10.setStartAngle(theta+90);arc10.setLength(90);
        arc11.setCenterX(x);arc11.setCenterY(y);arc11.setRadiusX(r+2*width);arc11.setRadiusY(r+2*width);arc11.setStartAngle(theta+180);arc11.setLength(90);
        arc12.setCenterX(x);arc12.setCenterY(y);arc12.setRadiusX(r+2*width);arc12.setRadiusY(r+2*width);arc12.setStartAngle(theta+270);arc12.setLength(90);
    }
    public void rotate(){
        this.theta+=2;
        this.theta1-=2;
        arc1.setStartAngle(theta);
        arc2.setStartAngle(theta+90);
        arc3.setStartAngle(theta+180);
        arc4.setStartAngle(theta+270);
        arc5.setStartAngle(theta1);
        arc6.setStartAngle(theta1-90);
        arc7.setStartAngle(theta1-180);
        arc8.setStartAngle(theta1-270);
        arc9.setStartAngle(theta);
        arc10.setStartAngle(theta+90);
        arc11.setStartAngle(theta+180);
        arc12.setStartAngle(theta+270);
    }
    public boolean collide(Player player){
        Ball b = player.getBall();
        String c = b.getColour();
        double theta1 = theta%360;
        double theta2 = (theta+90)%360;
        double theta3 = (theta+180)%360;
        double theta4 = (theta+270)%360;
        if (b.getY()>this.getY()){
            if (theta1<270 && theta1>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+3*this.getWidth() && val>=this.getR()){
                    if (!c.equals("red")){
                        return true;
                    }
                }
            }
            if (theta2<270 && theta2>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+3*this.getWidth() && val>=this.getR()){
                    if (!c.equals("yellow")){
                        return true;
                    }
                }
            }
            if (theta3<270 && theta3>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+3*this.getWidth() && val>=this.getR()){
                    if (!c.equals("blue")){
                        return true;
                    }
                }
            }
            if (theta4<270 && theta4>180){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+3*this.getWidth() && val>=this.getR()){
                    if (!c.equals("green")){
                        return true;
                    }
                }
            }
        }
        else{
            if (theta1<90 && theta1>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+3*this.getWidth() && val>=this.getR()){
                    if (!c.equals("red")){
                        return true;
                    }
                }
            }
            if (theta2<90 && theta2>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+3*this.getWidth() && val>=this.getR()){
                    if (!c.equals("yellow")){
                        return true;
                    }
                }
            }
            if (theta3<90 && theta3>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+3*this.getWidth() && val>=this.getR()){
                    if (!c.equals("blue")){
                        return true;
                    }
                }
            }
            if (theta4<90 && theta4>0){
                double val;
                val = Math.abs(b.getY() - this.getY());
                if (val<=this.getR()+b.getR()+3*this.getWidth() && val>=this.getR()){
                    if (!c.equals("green")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class Parallelogram extends Obsticle{
    private Line l1,l2,l3,l4;
    private double change;

    Parallelogram(double x,double y,double r,double theta,double height,double change,double width){
        super(x,y,r,theta,height,width);
        this.change = change;
        l1 = new Line(x+r*Math.cos(Math.PI*theta/180),y-r*Math.sin(Math.PI*theta/180),x+r*Math.cos(Math.PI*(theta+90)/180)/change,y-r*Math.sin(Math.PI*(theta+90)/180)/change);
        l2 = new Line(x+r*Math.cos(Math.PI*(theta+90)/180)/change,y-r*Math.sin(Math.PI*(theta+90)/180)/change,x+r*Math.cos(Math.PI*(theta+180)/180),y-r*Math.sin(Math.PI*(theta+180)/180));
        l3 = new Line(x+r*Math.cos(Math.PI*(theta+180)/180),y-r*Math.sin(Math.PI*(theta+180)/180),x+r*Math.cos(Math.PI*(theta+270)/180)/change,y-r*Math.sin(Math.PI*(theta+270)/180)/change);
        l4 = new Line(x+r*Math.cos(Math.PI*(theta+270)/180)/change,y-r*Math.sin(Math.PI*(theta+270)/180)/change,x+r*Math.cos(Math.PI*(theta+360)/180),y-r*Math.sin(Math.PI*(theta+360)/180));
        l1.setStroke(Color.RED);l1.setStrokeWidth(width);
        l2.setStroke(Color.YELLOW);l2.setStrokeWidth(width);
        l3.setStroke(Color.BLUE);l3.setStrokeWidth(width);
        l4.setStroke(Color.GREEN);l4.setStrokeWidth(width);
        getChildren().add(l1);
        getChildren().add(l2);
        getChildren().add(l3);
        getChildren().add(l4);
    }
    public void move(){
        l1.setStartX(x+r*Math.cos(Math.PI*theta/180)); l1.setStartY(y-r*Math.sin(Math.PI*theta/180)); l1.setEndX(x+r*Math.cos(Math.PI*(theta+90)/180)/change); l1.setEndY(y-r*Math.sin(Math.PI*(theta+90)/180)/change);
        l2.setStartX(x+r*Math.cos(Math.PI*(theta+90)/180)/change); l2.setStartY(y-r*Math.sin(Math.PI*(theta+90)/180)/change); l2.setEndX(x+r*Math.cos(Math.PI*(theta+180)/180)); l2.setEndY(y-r*Math.sin(Math.PI*(theta+180)/180));
        l3.setStartX(x+r*Math.cos(Math.PI*(theta+180)/180)); l3.setStartY(y-r*Math.sin(Math.PI*(theta+180)/180)); l3.setEndX(x+r*Math.cos(Math.PI*(theta+270)/180)/change); l3.setEndY(y-r*Math.sin(Math.PI*(theta+270)/180)/change);
        l4.setStartX(x+r*Math.cos(Math.PI*(theta+270)/180)/change); l4.setStartY(y-r*Math.sin(Math.PI*(theta+270)/180)/change); l4.setEndX(x+r*Math.cos(Math.PI*(theta+360)/180)); l4.setEndY(y-r*Math.sin(Math.PI*(theta+360)/180));
    }
    public void rotate(){
        this.theta+=2;
        l1.setStartX(x+r*Math.cos(Math.PI*theta/180)); l1.setStartY(y-r*Math.sin(Math.PI*theta/180)); l1.setEndX(x+r*Math.cos(Math.PI*(theta+90)/180)/change); l1.setEndY(y-r*Math.sin(Math.PI*(theta+90)/180)/change);
        l2.setStartX(x+r*Math.cos(Math.PI*(theta+90)/180)/change); l2.setStartY(y-r*Math.sin(Math.PI*(theta+90)/180)/change); l2.setEndX(x+r*Math.cos(Math.PI*(theta+180)/180)); l2.setEndY(y-r*Math.sin(Math.PI*(theta+180)/180));
        l3.setStartX(x+r*Math.cos(Math.PI*(theta+180)/180)); l3.setStartY(y-r*Math.sin(Math.PI*(theta+180)/180)); l3.setEndX(x+r*Math.cos(Math.PI*(theta+270)/180)/change); l3.setEndY(y-r*Math.sin(Math.PI*(theta+270)/180)/change);
        l4.setStartX(x+r*Math.cos(Math.PI*(theta+270)/180)/change); l4.setStartY(y-r*Math.sin(Math.PI*(theta+270)/180)/change); l4.setEndX(x+r*Math.cos(Math.PI*(theta+360)/180)); l4.setEndY(y-r*Math.sin(Math.PI*(theta+360)/180));
    }
    public boolean collide(Player player){
        Ball b = player.getBall();
        String c = b.getColour();
        double theta1 = theta%360;
        double theta2 = (theta+90)%360;
        double theta3 = (theta+180)%360;
        double theta4 = (theta+270)%360;
        if (b.getY()>this.getY() && b.getY()>this.getY()-this.getHeight()/2 && b.getY()<this.getY()+this.getHeight()/2){
            if (theta1<270 && theta1>180){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l1.getStartX(); double y1 = l1.getStartY(); double x2 = l1.getEndX(); double y2 = l1.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("red")){
                            return true;
                        }
                    }
                }
            }
            else if (theta2<270 && theta2>180){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l2.getStartX(); double y1 = l2.getStartY(); double x2 = l2.getEndX(); double y2 = l2.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("yellow")){
                            return true;
                        }
                    }
                }
            }
            else if (theta3<270 && theta3>180){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l3.getStartX(); double y1 = l3.getStartY(); double x2 = l3.getEndX(); double y2 = l3.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("blue")){
                            return true;
                        }
                    }
                }
            }
            else if (theta4<270 && theta4>180){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l4.getStartX(); double y1 = l4.getStartY(); double x2 = l4.getEndX(); double y2 = l4.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("green")){
                            return true;
                        }
                    }
                }
            }
        }
        else if (b.getY()>this.getY()-this.getHeight()/2 && b.getY()<this.getY()+this.getHeight()/2){
            if (theta1<90 && theta1>0){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l1.getStartX(); double y1 = l1.getStartY(); double x2 = l1.getEndX(); double y2 = l1.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("red")){
                            return true;
                        }
                    }
                }
            }
            else if (theta2<90 && theta2>0){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l2.getStartX(); double y1 = l2.getStartY(); double x2 = l2.getEndX(); double y2 = l2.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("yellow")){
                            return true;
                        }
                    }
                }
            }
            else if (theta3<90 && theta3>0){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l3.getStartX(); double y1 = l3.getStartY(); double x2 = l3.getEndX(); double y2 = l3.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("blue")){
                            return true;
                        }
                    }
                }
            }
            else if (theta4<90 && theta4>0){
                double a1 = b.getX(); double b1 = b.getY(); double r1 = b.getR();
                double x1 = l4.getStartX(); double y1 = l4.getStartY(); double x2 = l4.getEndX(); double y2 = l4.getEndY();
                if (x1!=x2){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals("green")){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }
}

class CrossBar extends Obsticle{
    private Line l1,l2,l3,l4,l5,l6,l7,l8;
    private double diff;

    CrossBar(double x,double y,double r,double theta,double height,double width,double diff){
        super(x,y,r,theta,height,width);
        this.diff = diff;
        l1 = new Line(x-diff,y,x-diff+r*Math.cos(Math.PI*theta/180),y-r*Math.sin(Math.PI*theta/180));
        l2 = new Line(x-diff,y,x-diff+r*Math.cos(Math.PI*(theta+90)/180),y-r*Math.sin(Math.PI*(theta+90)/180));
        l3 = new Line(x-diff,y,x-diff+r*Math.cos(Math.PI*(theta+270)/180),y-r*Math.sin(Math.PI*(theta+270)/180));
        l4 = new Line(x-diff,y,x-diff+r*Math.cos(Math.PI*(theta+270)/180),y-r*Math.sin(Math.PI*(theta+270)/180));

        l5 = new Line(x+diff,y,x+diff+r*Math.cos(-Math.PI*theta/180),y-r*Math.sin(-Math.PI*theta/180));
        l6 = new Line(x+diff,y,x+diff+r*Math.cos(-Math.PI*(theta+90)/180),y-r*Math.sin(-Math.PI*(theta+90)/180));
        l7 = new Line(x+diff,y,x+diff+r*Math.cos(-Math.PI*(theta+270)/180),y-r*Math.sin(-Math.PI*(theta+270)/180));
        l8 = new Line(x+diff,y,x+diff+r*Math.cos(-Math.PI*(theta+270)/180),y-r*Math.sin(-Math.PI*(theta+270)/180));

        l1.setStroke(Color.RED);l1.setStrokeWidth(width);
        l2.setStroke(Color.YELLOW);l2.setStrokeWidth(width);
        l3.setStroke(Color.BLUE);l3.setStrokeWidth(width);
        l4.setStroke(Color.GREEN);l4.setStrokeWidth(width);

        l5.setStroke(Color.BLUE);l5.setStrokeWidth(width);
        l6.setStroke(Color.GREEN);l6.setStrokeWidth(width);
        l7.setStroke(Color.RED);l7.setStrokeWidth(width);
        l8.setStroke(Color.YELLOW);l8.setStrokeWidth(width);

        getChildren().add(l1);
        getChildren().add(l2);
        getChildren().add(l3);
        getChildren().add(l4);

        getChildren().add(l5);
        getChildren().add(l6);
        getChildren().add(l7);
        getChildren().add(l8);
    }
    @Override
    public double getDiff(){
        return this.diff;
    }
    public void move(){
        l1.setStartX(x-diff); l1.setStartY(y); l1.setEndX(x-diff+r*Math.cos(Math.PI*theta/180)); l1.setEndY(y-r*Math.sin(Math.PI*theta/180));
        l2.setStartX(x-diff); l2.setStartY(y); l2.setEndX(x-diff+r*Math.cos(Math.PI*(theta+90)/180)); l2.setEndY(y-r*Math.sin(Math.PI*(theta+90)/180));
        l3.setStartX(x-diff); l3.setStartY(y); l3.setEndX(x-diff+r*Math.cos(Math.PI*(theta+180)/180)); l3.setEndY(y-r*Math.sin(Math.PI*(theta+180)/180));
        l4.setStartX(x-diff); l4.setStartY(y); l4.setEndX(x-diff+r*Math.cos(Math.PI*(theta+270)/180)); l4.setEndY(y-r*Math.sin(Math.PI*(theta+270)/180));

        l5.setStartX(x+diff); l5.setStartY(y); l5.setEndX(x+diff+r*Math.cos(-Math.PI*theta/180)); l5.setEndY(y-r*Math.sin(-Math.PI*theta/180));
        l6.setStartX(x+diff); l6.setStartY(y); l6.setEndX(x+diff+r*Math.cos(-Math.PI*(theta+90)/180)); l6.setEndY(y-r*Math.sin(-Math.PI*(theta+90)/180));
        l7.setStartX(x+diff); l7.setStartY(y); l7.setEndX(x+diff+r*Math.cos(-Math.PI*(theta+180)/180)); l7.setEndY(y-r*Math.sin(-Math.PI*(theta+180)/180));
        l8.setStartX(x+diff); l8.setStartY(y); l8.setEndX(x+diff+r*Math.cos(-Math.PI*(theta+270)/180)); l8.setEndY(y-r*Math.sin(-Math.PI*(theta+270)/180));
    }
    public void rotate(){
        this.theta+=2;
        l1.setStartX(x-diff); l1.setStartY(y); l1.setEndX(x-diff+r*Math.cos(Math.PI*theta/180)); l1.setEndY(y-r*Math.sin(Math.PI*theta/180));
        l2.setStartX(x-diff); l2.setStartY(y); l2.setEndX(x-diff+r*Math.cos(Math.PI*(theta+90)/180)); l2.setEndY(y-r*Math.sin(Math.PI*(theta+90)/180));
        l3.setStartX(x-diff); l3.setStartY(y); l3.setEndX(x-diff+r*Math.cos(Math.PI*(theta+180)/180)); l3.setEndY(y-r*Math.sin(Math.PI*(theta+180)/180));
        l4.setStartX(x-diff); l4.setStartY(y); l4.setEndX(x-diff+r*Math.cos(Math.PI*(theta+270)/180)); l4.setEndY(y-r*Math.sin(Math.PI*(theta+270)/180));

        l5.setStartX(x+diff); l5.setStartY(y); l5.setEndX(x+diff+r*Math.cos(-Math.PI*theta/180)); l5.setEndY(y-r*Math.sin(-Math.PI*theta/180));
        l6.setStartX(x+diff); l6.setStartY(y); l6.setEndX(x+diff+r*Math.cos(-Math.PI*(theta+90)/180)); l6.setEndY(y-r*Math.sin(-Math.PI*(theta+90)/180));
        l7.setStartX(x+diff); l7.setStartY(y); l7.setEndX(x+diff+r*Math.cos(-Math.PI*(theta+180)/180)); l7.setEndY(y-r*Math.sin(-Math.PI*(theta+180)/180));
        l8.setStartX(x+diff); l8.setStartY(y); l8.setEndX(x+diff+r*Math.cos(-Math.PI*(theta+270)/180)); l8.setEndY(y-r*Math.sin(-Math.PI*(theta+270)/180));
    }
    public boolean collide(Player p){
        Ball b = p.getBall();
        String c = b.getColour();
        if (Math.abs(b.getY() - this.getY())<=this.getR()){
            Line[] arr = new Line[4];
            arr[0] = l1; arr[1] = l2; arr[2] = l3; arr[3] = l4;
            double a1 = b.getX(); double b1 = b.getY();
            double angle = this.getTheta();
            for (int i=0;i<4;i++){
                Line l = arr[i];
                String lcolor = "red";
                if (l==l1){
                    angle = this.getTheta()%360;
                    lcolor = "red";
                }
                if (l==l2){
                    angle = (this.getTheta()+90)%360;
                    lcolor = "yellow";
                }
                if (l==l3){
                    angle = (this.getTheta()+180)%360;
                    lcolor = "blue";
                }
                if (l==l4){
                    angle = (this.getTheta()+270)%360;
                    lcolor = "green";
                }
                double x1 = l.getStartX(); double y1 = l.getStartY(); double x2 = l.getEndX(); double y2 = l.getEndY();
                if (x1!=x2 && (angle<90 || angle>270)){
                    double m = (y1-y2)/(x1-x2);
                    double val = Math.abs((a1*m - b1 + y1 - m*x1)/(Math.sqrt(1 + m*m)));
                    if (val<this.getWidth()){
                        if (!c.equals(lcolor)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

class Line_Obsticle extends Obsticle{
    private double x1;
    private double diff;
    private double turner;
    private Line l1,l2,l3,l4,l5,l6,l7,l8;

    Line_Obsticle(double x,double x1,double y,double height,double diff,double width){
        super(x,y,0,0,height,width);
        this.turner = 1;
        this.diff = diff;
        this.x1 = x1;
        l1 = new Line(x-3*diff/2,y-25,x-3*diff/2,y+25);
        l2 = new Line(x-diff/2,y-25,x-diff/2,y+25);
        l3 = new Line(x+diff/2,y-25,x+diff/2,y+25);
        l4 = new Line(x+3*diff/2,y-25,x+3*diff/2,y+25);
        l5 = new Line(x1-3*diff/2,y-50,x1-3*diff/2,y+50);
        l6 = new Line(x1-diff/2,y-50,x1-diff/2,y+50);
        l7 = new Line(x1+diff/2,y-50,x1+diff/2,y+50);
        l8 = new Line(x1+3*diff/2,y-50,x1+3*diff/2,y+50);

        l1.setStroke(Color.RED);l1.setStrokeWidth(width);
        l2.setStroke(Color.YELLOW);l2.setStrokeWidth(width);
        l3.setStroke(Color.BLUE);l3.setStrokeWidth(width);
        l4.setStroke(Color.GREEN);l4.setStrokeWidth(width);
        l5.setStroke(Color.GREEN);l5.setStrokeWidth(width);
        l6.setStroke(Color.BLUE);l6.setStrokeWidth(width);
        l7.setStroke(Color.YELLOW);l7.setStrokeWidth(width);
        l8.setStroke(Color.RED);l8.setStrokeWidth(width);
        getChildren().add(l1);
        getChildren().add(l2);
        getChildren().add(l3);
        getChildren().add(l4);
        getChildren().add(l5);
        getChildren().add(l6);
        getChildren().add(l7);
        getChildren().add(l8);
    }
    public void move(){
        l1.setStartX(x-3*diff/2);l1.setStartY(y-25);l1.setEndX(x-3*diff/2);l1.setEndY(y + 25);
        l2.setStartX(x-diff/2);l2.setStartY(y-25);l2.setEndX(x-diff/2);l2.setEndY(y + 25);
        l3.setStartX(x+diff/2);l3.setStartY(y-25);l3.setEndX(x+diff/2);l3.setEndY(y + 25);
        l4.setStartX(x+3*diff/2);l4.setStartY(y-25);l4.setEndX(x+3*diff/2);l4.setEndY(y + 25);
        l5.setStartX(x1-3*diff/2);l5.setStartY(y - 50);l5.setEndX(x1-3*diff/2);l5.setEndY(y + 50);
        l6.setStartX(x1-diff/2);l6.setStartY(y - 50);l6.setEndX(x1-diff/2);l6.setEndY(y + 50);
        l7.setStartX(x1+diff/2);l7.setStartY(y - 50);l7.setEndX(x1+diff/2);l7.setEndY(y + 50);
        l8.setStartX(x1+3*diff/2);l8.setStartY(y - 50);l8.setEndX(x1+3*diff/2);l8.setEndY(y + 50);

    }
    public void rotate(){
        if (x-3*diff/2<0){
            turner = 1;
        }
        if (x+3*diff/2>500){
            turner = -1;
        }
        x = x + turner;
        x1 = x1 - turner;
        l1.setStartX(x-3*diff/2);l1.setStartY(y-25);l1.setEndX(x-3*diff/2);l1.setEndY(y + 25);
        l2.setStartX(x-diff/2);l2.setStartY(y-25);l2.setEndX(x-diff/2);l2.setEndY(y + 25);
        l3.setStartX(x+diff/2);l3.setStartY(y-25);l3.setEndX(x+diff/2);l3.setEndY(y + 25);
        l4.setStartX(x+3*diff/2);l4.setStartY(y-25);l4.setEndX(x+3*diff/2);l4.setEndY(y + 25);
        l5.setStartX(x1-3*diff/2);l5.setStartY(y - 50);l5.setEndX(x1-3*diff/2);l5.setEndY(y + 50);
        l6.setStartX(x1-diff/2);l6.setStartY(y - 50);l6.setEndX(x1-diff/2);l6.setEndY(y + 50);
        l7.setStartX(x1+diff/2);l7.setStartY(y - 50);l7.setEndX(x1+diff/2);l7.setEndY(y + 50);
        l8.setStartX(x1+3*diff/2);l8.setStartY(y - 50);l8.setEndX(x1+3*diff/2);l8.setEndY(y + 50);
    }

    public double getDiff() {
        return diff;
    }
    public void setDiff(double diff) {
        this.diff = diff;
    }
    public double getX1() {
        return x1;
    }

    public boolean collide(Player player){
        Ball b = player.getBall();
        String c = b.getColour();
        if (Math.abs(l1.getStartX()-l8.getStartX())<=2*b.getR()){
            if (b.getY()<=l8.getEndY() && b.getY()>=l8.getStartY()){
                if (!c.equals("red")){
                    return true;
                }
            }
        }
        if (Math.abs(l2.getStartX()-l7.getStartX())<=2*b.getR()){
            if (b.getY()<=l7.getEndY() && b.getY()>=l7.getStartY()){
                if (!c.equals("yellow")){
                    return true;
                }
            }
        }
        if (Math.abs(l3.getStartX()-l6.getStartX())<=2*b.getR()){
            if (b.getY()<=l6.getEndY() && b.getY()>=l6.getStartY()){
                if (!c.equals("blue")){
                    return true;
                }
            }
        }
        if (Math.abs(l4.getStartX()-l5.getStartX())<=2*b.getR()){
            if (b.getY()<=l5.getEndY() && b.getY()>=l5.getStartY()){
                if (!c.equals("green")){
                    return true;
                }
            }
        }

        return false;
    }
}

class Star extends Group{
    private double x;
    private double y;
    private double r;
    private double r2;
    private Player player;
    private double height;
    private Polygon polygon;
    private boolean inaction;
    Star(double x,double y,double r){
        this.x=x;
        this.y=y;
        this.r=r;
        this.height = r*(1+Math.cos(Math.PI*36/180));
        this.r2 = r*(Math.sin(Math.PI*18/180))/(Math.cos(Math.PI*36/180));
        this.polygon = new Polygon();
        this.polygon.setFill(Color.WHITE);
        this.polygon.getPoints().addAll(new Double[]{
                this.x,this.y-this.r,
                this.x+this.r2*Math.sin(Math.PI*36/180),this.y-this.r2*Math.cos(Math.PI*36/180),
                this.x+this.r*Math.sin(Math.PI*72/180),this.y-this.r*Math.cos(Math.PI*72/180),
                this.x+this.r2*Math.sin(Math.PI*72/180),this.y+this.r2*Math.cos(Math.PI*72/180),
                this.x+this.r*Math.sin(Math.PI*36/180),this.y+this.r*Math.cos(Math.PI*36/180),
                this.x,this.y+this.r2,
                this.x-this.r*Math.sin(Math.PI*36/180),this.y+this.r*Math.cos(Math.PI*36/180),
                this.x-this.r2*Math.sin(Math.PI*72/180),this.y+this.r2*Math.cos(Math.PI*72/180),
                this.x-this.r*Math.sin(Math.PI*72/180),this.y-this.r*Math.cos(Math.PI*72/180),
                this.x-this.r2*Math.sin(Math.PI*36/180),this.y-this.r2*Math.cos(Math.PI*36/180)
        });
        getChildren().add(this.polygon);

    }
    public double getHeight(){
        return this.height;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public void setY(double y){
        this.y=y;
    }
    public double getR(){
        return this.r;
    }
    public void setPlayer(Player p){
        this.player = p;
    }
    public boolean getInAction(){
        return this.inaction;
    }
    public void setInAction(boolean val){
        this.inaction = val;
    }
    public void move(){
        this.polygon.getPoints().clear();
        this.polygon.getPoints().addAll(new Double[]{
                this.x,this.y-this.r,
                this.x+this.r2*Math.sin(Math.PI*36/180),this.y-this.r2*Math.cos(Math.PI*36/180),
                this.x+this.r*Math.sin(Math.PI*72/180),this.y-this.r*Math.cos(Math.PI*72/180),
                this.x+this.r2*Math.sin(Math.PI*72/180),this.y+this.r2*Math.cos(Math.PI*72/180),
                this.x+this.r*Math.sin(Math.PI*36/180),this.y+this.r*Math.cos(Math.PI*36/180),
                this.x,this.y+this.r2,
                this.x-this.r*Math.sin(Math.PI*36/180),this.y+this.r*Math.cos(Math.PI*36/180),
                this.x-this.r2*Math.sin(Math.PI*72/180),this.y+this.r2*Math.cos(Math.PI*72/180),
                this.x-this.r*Math.sin(Math.PI*72/180),this.y-this.r*Math.cos(Math.PI*72/180),
                this.x-this.r2*Math.sin(Math.PI*36/180),this.y-this.r2*Math.cos(Math.PI*36/180)
        });
        this.polygon.setFill(Color.WHITE);
    }
    public boolean collide(Player p){
        Ball b = p.getBall();
        if (Math.abs(b.getY() - this.getY())<=this.getR()){
            return true;
        }
        return false;
    }

}

class ColorSwitch extends Group{
    private double x;
    private double y;
    private double r;
    private Arc arc1,arc2,arc3,arc4;
    private Player player;
    private boolean inaction;
    ColorSwitch(double x,double y,double r){
        this.x=x;
        this.y=y;
        this.r=r;
        this.arc1 = new Arc(this.x,this.y,this.r,this.r,0.0d,90);
        this.arc2 = new Arc(this.x,this.y,this.r,this.r,90.0d,90);
        this.arc3 = new Arc(this.x,this.y,this.r,this.r,180.0d,90);
        this.arc4 = new Arc(this.x,this.y,this.r,this.r,270.0d,90);
        this.arc1.setStroke(Color.RED); this.arc1.setType(ArcType.ROUND);
        this.arc2.setStroke(Color.YELLOW); this.arc2.setType(ArcType.ROUND);
        this.arc3.setStroke(Color.BLUE); this.arc3.setType(ArcType.ROUND);
        this.arc4.setStroke(Color.GREEN); this.arc4.setType(ArcType.ROUND);
        this.arc1.setFill(Color.RED);
        this.arc2.setFill(Color.YELLOW);
        this.arc3.setFill(Color.BLUE);
        this.arc4.setFill(Color.GREEN);
        getChildren().add(this.arc1);
        getChildren().add(this.arc2);
        getChildren().add(this.arc3);
        getChildren().add(this.arc4);
        this.inaction = false;
    }
    public double getHeight(){
        return 2*this.r;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public void setY(double y){
        this.y=y;
    }
    public double getR(){
        return this.r;
    }
    public boolean getInAction(){
        return this.inaction;
    }
    public void setInAction(boolean val){
        this.inaction = val;
    }
    public void setPlayer(Player p){
        this.player = p;
    }
    public void move(){
        arc1.setCenterX(this.x);arc1.setCenterY(this.y);arc1.setRadiusX(this.r);arc1.setRadiusY(this.r);arc1.setStartAngle(0.0d);arc1.setLength(90);
        arc2.setCenterX(this.x);arc2.setCenterY(this.y);arc2.setRadiusX(this.r);arc2.setRadiusY(this.r);arc2.setStartAngle(90.0d);arc2.setLength(90);
        arc3.setCenterX(this.x);arc3.setCenterY(this.y);arc3.setRadiusX(this.r);arc3.setRadiusY(this.r);arc3.setStartAngle(180.0d);arc3.setLength(90);
        arc4.setCenterX(this.x);arc4.setCenterY(this.y);arc4.setRadiusX(this.r);arc4.setRadiusY(this.r);arc4.setStartAngle(270.0d);arc4.setLength(90);
    }
    public boolean collide(Player p){
        Ball b = p.getBall();
        if (Math.abs(b.getY() - this.getY())<=this.getR()+b.getR()){
            return true;
        }
        return false;
    }
}

class Ball extends Group{
    private double v;
    private double g;
    private double x;
    private double y;
    private double r;
    private Circle c;
    private String colour;
    private int id ;

    Ball(double x,double y,double r,String colour){
        this.v = 5;
        this.g = 0.3;
        this.x = x;
        this.y = y;
        this.r = r;
        this.c = new Circle();
        this.id = -1;
        c.setCenterX(x);
        c.setCenterY(y);
        c.setRadius(r);
        this.setColour(colour);
        getChildren().add(c);
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public void setY(double y){
        this.y = y;
        this.c.setCenterY(y);
    }
    public double getR(){
        return this.r;
    }
    public double getG(){
        return this.g;
    }
    public String getColour(){
        return this.colour;
    }
    public void setColour(String col){
        this.colour = col;
        if (colour.equals("red")){
            c.setFill(Color.RED);
        }
        if (colour.equals("yellow")){
            c.setFill(Color.YELLOW);
        }
        if (colour.equals("blue")){
            c.setFill(Color.BLUE);
        }
        if (colour.equals("green")){
            c.setFill(Color.GREEN);
        }
    }
    public double getV(){
        return this.v;
    }
    public void setV(double v){
        this.v = v;
    }
    public int get_Id() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}

class Difficulty extends Group{
    private int level;
    private double theta;
    private double velocity;
    private Text text;
    Difficulty(){
        this.level = 1;
        this.theta = 2.0d;
        this.velocity = 5.0d;
        this.text = new Text();
        this.text.setText("Level 1");
        this.text.setFont(Font.font ("Verdana", 20));
        this.text.setFill(Color.RED);
        text.setX(10);
        text.setY(20);
        getChildren().add(text);
    }
    public int getLevel(){
        return this.level;
    }
    public void setLevel(int l){
        this.level = l;
        String s = "Level "+this.level;
        this.text.setText(s);
    }
    public double getVelocity(){
        return this.velocity;
    }
    public void setVelocity(double v){
        this.velocity = v;
    }
    public double getTheta(){
        return this.theta;
    }
    public void setTheta(double d){
        this.velocity = d;
    }

}

class Player{
    private int stars;
    private Ball b;
    private Obsticle obsticle1;
    private Obsticle obsticle2;
    private Obsticle[] arr;
    private Star star;
    private ColorSwitch colorswitch;
    private Random random;
    private Difficulty l;
    private double distance;
    private Text score;
    private Pane root;
    private Scene mainscene;
    private Scene gamescene;
    private Stage primaryStage;
    private MediaPlayer sound;
    private MediaPlayer background;
    private Game game;
    private boolean movable;
    Player(Game NewGame){
        this.stars = 0;
        this.random = new Random();
        this.distance = 0.0d;
        String path = System.getProperty("user.dir")+"/sound.mp3";
        Media media = new Media(new File(path).toURI().toString());
        this.sound = new MediaPlayer(media);
        this.movable = true;
        game = NewGame;
    }
    public boolean getMovable(){
        return this.movable;
    }
    public void setMovable(boolean val){
        this.movable = val;
    }
    public void setGameScene(Scene scene){
        this.gamescene = scene;
    }
    public Scene getGameScene(){
        return this.gamescene;
    }
    public void setGame(Game game){
        this.game = game;
    }
    public Game getGame(){
        return this.game;
    }
    public void setBackground(MediaPlayer media){
        this.background = media;
    }
    public MediaPlayer getBackground(){
        return this.background;
    }
    public void setDifficulty(Difficulty l){
        this.l = l;
    }
    public void setStage(Stage stage){
        this.primaryStage = stage;
    }
    public void setScene(Scene scene){
        this.mainscene = scene;
    }
    public void setPane(Pane root){
        this.root = root;
        this.score = new Text();
        this.score.setText("Score 0");
        this.score.setFont(Font.font ("Verdana", 20));
        this.score.setFill(Color.YELLOW);
        this.score.setX(400);
        this.score.setY(20);
        this.root.getChildren().add(score);
    }
    public void setStars(int stars){
        this.stars = stars;
        String s = "Score "+this.stars;
        this.score.setText(s);
    }
    public int getStars(){
        return this.stars;
    }
    public Ball getBall(){
        return this.b;
    }
    public void setBall(Ball b){
        this.b = b;
    }
    public void setObsticle1(Obsticle o){
        this.obsticle1 = o;
    }
    public void setObsticle2(Obsticle o){
        this.obsticle2 = o;
    }
    public void setObsticles(Obsticle[] arr){
        this.arr = arr;
    }
    public void setStar(Star o){
        this.star = o;
    }
    public void setColorSwitch(ColorSwitch o){
        this.colorswitch = o;
    }
    public void continueGame(Stage primaryStage,Scene mainscene,Pane root,double ballx,double bally){
        if (this.getStars()>=5){
            this.setStars(this.getStars()-5);
            Node paint = new TextField("balbal");
            for (Node node: root.getChildren()){
                String name = node.getClass().getName();
                if (name.contains("ImageView")){
                    paint = node;
                }
            }
            root.getChildren().remove(paint);
            primaryStage.setScene(this.getGameScene());
            if (this.getL()==1){
                if (bally>=this.getObsticle1().getY()){
                    this.getBall().setY(this.getObsticle1().getY() + 150);
                    this.getBall().setV(0);
                    System.out.println("Obsticle1 position is :- "+this.getObsticle1().getY()+" and ball at :- "+this.getBall().getY());
                }
                else{
                    this.getBall().setY(this.getObsticle1().getY() - 150);
                    this.getBall().setV(3);
                    System.out.println("Obsticle1 position is :- "+this.getObsticle1().getY()+" and ball at :- "+this.getBall().getY());
                }
            }
            else{
                if (this.getObsticle2()==null){
                    if (bally>=this.getObsticle1().getY()){
                        this.getBall().setY(this.getObsticle1().getY() + 150);
                        this.getBall().setV(0);
                    }
                    else{
                        this.getBall().setY(this.getObsticle1().getY() - 150);
                        this.getBall().setV(3);
                    }
                }
                else{
                    if (bally>=this.getObsticle1().getY() && bally>=this.getObsticle2().getY()){
                        this.getBall().setY(this.getObsticle1().getY() + 150);
                        this.getBall().setV(0);
                    }
                    else if (bally<this.getObsticle1().getY() && bally>=this.getObsticle2().getY()){
                        this.getBall().setY(this.getObsticle1().getY() - 150);
                        this.getBall().setV(3);
                    }
                    else if (bally<this.getObsticle2().getY()){
                        this.getBall().setY(this.getObsticle2().getY() - 150);
                        this.getBall().setV(3);
                    }
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Cannot continue!");
            alert.setContentText("Hello our dear user,\nYou dont have enough stars.");

            alert.showAndWait();
        }
    }
    public void collideAnimation(Stage primaryStage,Scene mainscene,Pane root,double ballx,double bally){
        ImageView smash;
        smash = null;
        if (this.getBall().getColour().equals("red")){
            System.out.println("red");
            smash = game.redsmash;
            System.out.println("smash loaded");
        }
        else if (this.getBall().getColour().equals("blue")){
            System.out.println("blue");
            smash = game.bluesmash;
            System.out.println("smash loaded");
        }
        else if (this.getBall().getColour().equals("yellow")){
            System.out.println("yellow");
            smash = game.yellowsmash;
            System.out.println("smash loaded");
        }
        else{
            System.out.println("green");
            smash = game.greensmash;
            System.out.println("smash loaded");
        }
        smash.setLayoutX(this.getBall().getX()-47);
        smash.setLayoutY(this.getBall().getY()-39);
        root.getChildren().add(smash);
        System.out.println("smash loaded in root");

        this.b.setY(1000);
        System.out.println(this.b.getY());
        Player p = this;
        double starttime = System.nanoTime();
        //int presentscore = this.getScore();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now){
                boolean fileexist = false;
                try {
                    File myObj = new File("top_score.txt");
                    if (myObj.createNewFile()) {
                        //System.out.println("File created: " + myObj.getName());
                    } else {
                        //System.out.println("File already exists.");
                        fileexist = true;
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                int topscore = 0;
                if (!fileexist){
                    try{
                        PrintWriter writer = new PrintWriter(new File("top_score.txt"));
                        writer.append(String.valueOf(getScore()));
                        writer.flush();
                        writer.close();
                        topscore = getScore();
                    }
                    catch(FileNotFoundException e){

                    }
                }
                else{
                    try{
                        Scanner top_score_scanner = new Scanner(new FileReader("top_score.txt"));
                        int top_scor = Integer.parseInt(top_score_scanner.next());
                        top_score_scanner.close();
                        if (getScore()>top_scor){
                            topscore = getScore();
                            PrintWriter writer = new PrintWriter(new File("top_score.txt"));
                            writer.append(String.valueOf(getScore()));
                            writer.flush();
                            writer.close();
                        }
                        else{
                            topscore = top_scor;
                        }
                    }
                    catch(FileNotFoundException e){

                    }
                }
                double currtime = System.nanoTime();
                if (((currtime - starttime)/1000000000) > 3){
                    stop();
                    Pane p2 = new Pane();
                    Scene s2 = new Scene(p2,500,600,Color.BLACK);
                    primaryStage.setScene(s2);p2.setBackground(Background.EMPTY);
//                    ImageView cartoon = null;ImageView continue_im = null;ImageView gameover = null;ImageView exit = null;ImageView restart = null;ImageView five = null;ImageView star_im = null;ImageView top_score = null;
                    TextField text1 = new TextField("Your score for this game is :- "+getScore());text1.setLayoutX(20);text1.setLayoutY(200);text1.setFont(new Font(20));text1.setPrefSize(370,30);text1.setBackground(Background.EMPTY);text1.setStyle("-fx-text-fill: rgba(130, 130, 130, 1);-fx-highlight-fill: rgba(0, 0, 0, 1); -fx-highlight-text-fill: rgba(130, 130, 130, 1);");
                    TextField text2 = new TextField("Top score                            :- "+topscore);text2.setLayoutX(20);text2.setLayoutY(230);text2.setFont(new Font(20));text2.setPrefSize(370,30);text2.setBackground(Background.EMPTY);text2.setStyle("-fx-text-fill: rgba(130, 130, 130, 1);-fx-highlight-fill: rgba(0, 0, 0, 1); -fx-highlight-text-fill: rgba(130, 130, 130, 1);");
                    TextField exit_txt = new TextField("Main menu");exit_txt.setLayoutX(140);exit_txt.setLayoutY(450);exit_txt.setFont(new Font(18));exit_txt.setPrefSize(370,30);exit_txt.setBackground(Background.EMPTY);exit_txt.setStyle("-fx-text-fill: rgba(130, 130, 130, 1);-fx-highlight-fill: rgba(0, 0, 0, 1); -fx-highlight-text-fill: rgba(130, 130, 130, 1);");
                    TextField continue_txt = new TextField("Cost");game.five.setLayoutX(325);game.five.setLayoutY(460);game.star_im.setLayoutX(340);game.star_im.setLayoutY(460);continue_txt.setLayoutX(270);continue_txt.setLayoutY(450);continue_txt.setFont(new Font(18));continue_txt.setPrefSize(370,30);continue_txt.setBackground(Background.EMPTY);continue_txt.setStyle("-fx-text-fill: rgba(130, 130, 130, 1);-fx-highlight-fill: rgba(0, 0, 0, 1); -fx-highlight-text-fill: rgba(130, 130, 130, 1);");
                    TextField restart_txt = new TextField("Restart");restart_txt.setLayoutX(385);restart_txt.setLayoutY(450);restart_txt.setFont(new Font(18));restart_txt.setPrefSize(370,30);restart_txt.setBackground(Background.EMPTY);restart_txt.setStyle("-fx-text-fill: rgba(130, 130, 130, 1);-fx-highlight-fill: rgba(0, 0, 0, 1); -fx-highlight-text-fill: rgba(130, 130, 130, 1);");
                    text1.setEditable(false);text2.setEditable(false);continue_txt.setEditable(false);exit_txt.setEditable(false);restart_txt.setEditable(false);

                    /////////////////////////////////////
                    if (getScore()>=topscore){
                        System.out.println("Top score made !");
                        FadeTransition ft = new FadeTransition(Duration.millis(2000),game.top_score);
                        ft.setFromValue(1);
                        ft.setToValue(0);
                        ft.setCycleCount(Timeline.INDEFINITE);
                        ft.setAutoReverse(true);
                        ft.play();
                        p2.getChildren().add(game.top_score);
                    }
                    /////////////////////////////////////

                    p2.getChildren().add(game.exit);game.exit.setEffect(new Reflection());
                    p2.getChildren().add(game.cartoon);game.cartoon.setEffect(new Reflection());
                    p2.getChildren().add(game.continue_im);game.continue_im.setEffect(new Reflection(-38,0.75,0.5,0));
                    p2.getChildren().add(game.gameover);p2.getChildren().add(game.five);p2.getChildren().add(game.star_im);//p2.getChildren().add(top_score); //////////////////
                    p2.getChildren().add(game.restart);game.restart.setEffect(new Reflection());
                    p2.getChildren().add(text1);p2.getChildren().add(continue_txt);p2.getChildren().add(restart_txt);p2.getChildren().add(exit_txt);p2.getChildren().add(text2);
                    game.continue_im.setOnMouseClicked(e -> {continueGame(primaryStage,mainscene,root,ballx,bally);});
                    game.restart.setOnMouseClicked(e -> p.getGame().StartnewGame(primaryStage,mainscene));
                    game.exit.setOnMouseClicked(e -> {p.getGame().MainMenu(primaryStage);});

                }
            }
        };
        animationTimer.start();
    }
    public void jump(){
        Ball ball = this.b;
        if (obsticle1.collide(this)){
            collideAnimation(this.primaryStage,this.mainscene,this.root,this.getBall().getX(),this.getBall().getY());
        }
        else if (this.obsticle2!=null){
            if (obsticle2.collide(this)){
                collideAnimation(this.primaryStage,this.mainscene,this.root,this.getBall().getX(),this.getBall().getY());
            }
        }
        if (this.star.collide(this)){
            this.stars++;
            String s = "Score "+this.stars;
            this.score.setText(s);
            this.star.setY(-200);
            this.star.setInAction(false);
        }
        if (this.colorswitch.collide(this)){
            int rand = random.nextInt(3);
            String[] colours = {"red","yellow","blue","green"};
            String[] randcolor = new String[3];
            int count = 0;
            for (int i=0;i<4;i++){
                String col = colours[i];
                if (!col.equals(this.getBall().getColour())){
                    randcolor[count] = col;
                    count++;
                }
            }
            String col = randcolor[rand];
            this.getBall().setColour(col);
            this.colorswitch.setY(-200);
            this.colorswitch.setInAction(false);
        }
        this.sound.stop();
        ball.setV(5);
        this.sound.play();
        if (ball.getY() - ball.getV()<=350){
            double diff = 350 - (ball.getY() - ball.getV());
            ball.setY(350);
            if (this.star.getInAction()==true){
                this.star.setY(this.star.getY()+diff);
            }
            if (this.star.getY() - this.star.getHeight()>600){
                this.star.setInAction(false);
            }
            if (this.colorswitch.getInAction()==true){
                this.colorswitch.setY(this.colorswitch.getY()+diff);
            }
            if (this.colorswitch.getY() - this.colorswitch.getHeight()>600){
                this.colorswitch.setInAction(false);
            }
            if (this.l.getLevel()==1){
                obsticle1.setY(obsticle1.getY()+diff);
                if ((obsticle1.getY() - obsticle1.getHeight()/2)>620){
                    Obsticle temp = arr[random.nextInt(arr.length)];
                    this.obsticle1 = temp;
                    this.obsticle1.setY(-this.obsticle1.getHeight()/2);

                    if (this.star.getInAction()==false){
                        if (this.colorswitch.getInAction()==false){
                            int val = random.nextInt(3);
                            if (val==0){
                                this.star.setY(this.obsticle1.getY() - this.obsticle1.getHeight()/2 - 100);
                                this.star.setInAction(true);
                            }
                            else if (val==1){
                                this.colorswitch.setY(this.obsticle1.getY() - this.obsticle1.getHeight()/2 - 100);
                                this.colorswitch.setInAction(true);
                            }
                        }
                    }
                }
            }
            else if (this.l.getLevel()>=2){
                obsticle1.setY(obsticle1.getY()+diff);
                if (this.obsticle2==null){
                    if (obsticle1.getY() - obsticle1.getHeight()/2>620){
                        Obsticle temp1 = arr[random.nextInt(arr.length)];
                        Obsticle[] arr2 = new Obsticle[arr.length - 1];
                        int count = 0;
                        for (Obsticle o: arr){
                            if (o!=temp1){
                                arr2[count] = o;
                                count++;
                            }
                        }
                        Obsticle temp2 = arr2[random.nextInt(arr2.length)];
                        this.obsticle1 = temp1;
                        this.obsticle2 = temp2;
                        this.obsticle1.setY(-this.obsticle1.getHeight()/2);
                        this.obsticle2.setY(this.obsticle1.getY() - 300 -this.obsticle1.getHeight()/2 - this.obsticle2.getHeight()/2);
                    }
                }
                else{
                    this.obsticle2.setY(this.obsticle2.getY()+diff);
                    if (this.obsticle2.getY() - this.obsticle2.getHeight()/2>300){
                        Obsticle temp1 = this.obsticle2;
                        Obsticle[] arr2 = new Obsticle[arr.length - 1];
                        int count = 0;
                        for (Obsticle o: arr){
                            if (o!=temp1){
                                arr2[count] = o;
                                count++;
                            }
                        }
                        Obsticle temp2 = arr2[random.nextInt(arr2.length)];
                        this.obsticle1 = temp1;
                        this.obsticle2 = temp2;
                        this.obsticle2.setY(-this.obsticle2.getHeight()/2);

                        if (this.star.getInAction()==false){
                            if (this.colorswitch.getInAction()==false){
                                int val = random.nextInt(3);
                                if (val==0){
                                    this.star.setY(this.obsticle2.getY() - this.obsticle2.getHeight()/2 - 100);
                                    this.star.setInAction(true);
                                }
                                else if (val==1){
                                    this.colorswitch.setY(this.obsticle2.getY() - this.obsticle2.getHeight()/2 - 100);
                                    this.colorswitch.setInAction(true);
                                }
                            }
                        }
                    }
                }
            }
            this.distance += diff;
            if (this.distance>=6000){
                this.l.setLevel(this.l.getLevel()+1);
                this.distance -= 6000;
            }
        }
        else{
            ball.setY(ball.getY() -ball.getV());
        }
        blit();
    }
    public void fall(){
        Ball ball = this.b;
        if (obsticle1.collide(this)){
            collideAnimation(this.primaryStage,this.mainscene,this.root,this.getBall().getX(),this.getBall().getY());
        }
        else if (this.obsticle2!=null){
            if (obsticle2.collide(this)){
                collideAnimation(this.primaryStage,this.mainscene,this.root,this.getBall().getX(),this.getBall().getY());
            }
        }
        if (this.star.collide(this)){
            this.stars++;
            String s = "Score "+this.stars;
            this.score.setText(s);
            this.star.setY(-200);
            this.star.setInAction(false);
        }
        if (this.colorswitch.collide(this)){
            int rand = random.nextInt(3);
            String[] colours = {"red","yellow","blue","green"};
            String[] randcolor = new String[3];
            int count = 0;
            for (int i=0;i<4;i++){
                String col = colours[i];
                if (!col.equals(this.getBall().getColour())){
                    randcolor[count] = col;
                    count++;
                }
            }
            String col = randcolor[rand];
            this.getBall().setColour(col);
            this.colorswitch.setY(-200);
            this.colorswitch.setInAction(false);
        }
        ball.setV(ball.getV() - ball.getG());
        if (ball.getY() - ball.getV()<=350){
            double diff = 350 - (ball.getY() - ball.getV());
            ball.setY(350);
            if (this.star.getInAction()==true){
                this.star.setY(this.star.getY()+diff);
            }
            if (this.star.getY() - this.star.getHeight()>600){
                this.star.setInAction(false);
            }
            if (this.colorswitch.getInAction()==true){
                this.colorswitch.setY(this.colorswitch.getY()+diff);
            }
            if (this.colorswitch.getY() - this.colorswitch.getHeight()>600){
                this.colorswitch.setInAction(false);
            }
            if (this.l.getLevel()==1){
                obsticle1.setY(obsticle1.getY()+diff);
                if ((obsticle1.getY() - obsticle1.getHeight()/2)>620){
                    Obsticle temp = arr[random.nextInt(arr.length)];
                    this.obsticle1 = temp;
                    this.obsticle1.setY(-this.obsticle1.getHeight()/2);

                    if (this.star.getInAction()==false){
                        if (this.colorswitch.getInAction()==false){
                            int val = random.nextInt(3);
                            if (val==0){
                                this.star.setY(this.obsticle1.getY() - this.obsticle1.getHeight()/2 - 100);
                                this.star.setInAction(true);
                            }
                            else if (val==1){
                                this.colorswitch.setY(this.obsticle1.getY() - this.obsticle1.getHeight()/2 - 100);
                                this.colorswitch.setInAction(true);
                            }
                        }
                    }
                }
            }
            else if (this.l.getLevel()>=2){
                obsticle1.setY(obsticle1.getY()+diff);
                if (this.obsticle2==null){
                    if (obsticle1.getY() - obsticle1.getHeight()>620){
                        Obsticle temp1 = arr[random.nextInt(arr.length)];
                        Obsticle[] arr2 = new Obsticle[arr.length - 1];
                        int count = 0;
                        for (Obsticle o: arr){
                            if (o!=temp1){
                                arr2[count] = o;
                                count++;
                            }
                        }
                        Obsticle temp2 = arr2[random.nextInt(arr2.length)];
                        this.obsticle1 = temp1;
                        this.obsticle2 = temp2;
                        this.obsticle1.setY(-this.obsticle1.getHeight()/2);
                        this.obsticle2.setY(this.obsticle1.getY() - 300 -this.obsticle1.getHeight()/2 - this.obsticle2.getHeight()/2);
                    }
                }
                else{
                    this.obsticle2.setY(this.obsticle2.getY()+diff);
                    if (this.obsticle2.getY() - this.obsticle2.getHeight()/2>300){
                        Obsticle temp1 = this.obsticle2;
                        Obsticle[] arr2 = new Obsticle[arr.length - 1];
                        int count = 0;
                        for (Obsticle o: arr){
                            if (o!=temp1){
                                arr2[count] = o;
                                count++;
                            }
                        }
                        Obsticle temp2 = arr2[random.nextInt(arr2.length)];
                        this.obsticle1 = temp1;
                        this.obsticle2 = temp2;
                        this.obsticle2.setY(-this.obsticle2.getHeight()/2);
                        if (this.star.getInAction()==false){
                            if (this.colorswitch.getInAction()==false){
                                int val = random.nextInt(3);
                                if (val==0){
                                    this.star.setY(this.obsticle2.getY() - this.obsticle2.getHeight()/2 - 100);
                                    this.star.setInAction(true);
                                }
                                else if (val==1){
                                    this.colorswitch.setY(this.obsticle2.getY() - this.obsticle2.getHeight()/2 - 100);
                                    this.colorswitch.setInAction(true);
                                }
                            }
                        }
                    }
                }
            }
            this.distance += diff;
            if (this.distance>=6000){
                this.l.setLevel(this.l.getLevel()+1);
                this.distance -= 6000;
            }
        }
        else{
            ball.setY(ball.getY() -ball.getV());
        }
        blit();
    }
    public void blit(){
        Ball ball = this.b;
        ball.setY(b.getY() - b.getV());
        obsticle1.move();
        obsticle1.rotate();
        if (obsticle2!=null){
            obsticle2.move();
            obsticle2.rotate();
        }
        this.star.move();
        this.colorswitch.move();
    }

    public int getL() {
        return l.getLevel();
    }
    public double getDistance() {
        return distance;
    }
    public int getScore() {
        return this.stars;
    }

    public Obsticle getObsticle1() {
        return obsticle1;
    }

    public Obsticle getObsticle2() {
        return obsticle2;
    }
}

class MyTimer extends AnimationTimer{
    private Player p;
    public MyTimer(Player p){
        this.p = p;
    }
    @Override
    public void handle(long now){
        fall();
    }
    public void fall(){
        p.fall();
    }
}
public class Game extends Application{
    private MediaPlayer background;

    public Game() throws FileNotFoundException {
        try {
            save_im = initialize_save();cartoon = initialize_cartoon();exit = initialize_home();gameover = initialize_gameover();restart = initialize_restart();continue_im = initialize_continue();five = initialize_five();star_im = initialize_star();top_score = initialize_topscore();greensmash = initialize_greensmash();redsmash = initialize_redsmash();yellowsmash = initialize_yellowsmash();bluesmash = initialize_bluesmash();home_im = initialize_home();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Keyreleased(Button but2) {
        but2.setStyle("-fx-background-color: rgba(50, 50, 50, 0.7);-fx-text-fill: rgba(255, 255, 255, 1);");
    }
    public void Keypress(Button but) {
        but.setStyle("-fx-background-color: rgba(50, 50, 50, 0.7);-fx-text-fill: rgba(0, 0, 255, 1);");
    }
    public MediaPlayer getBackground(){
        return this.background;
    }

    public void MainMenu(Stage primaryStage){
        this.background = new MediaPlayer(new Media(new File(System.getProperty("user.dir")+"/background.mp3").toURI().toString()));
        this.background.play();
        Pane p2 = new Pane();
        Scene s2 = new Scene(p2,500,600,Color.BLACK);
        primaryStage.setScene(s2);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(5000));
        fade.setFromValue(10);
        fade.setToValue(0.1);
        Button but = new Button("Start New Game");but.setLayoutX(175);but.setLayoutY(300);but.setFont(new Font(16));but.setPrefSize(150,30);
        Button but2 = new Button("Load Game");but2.setLayoutX(175);but2.setLayoutY(340);but2.setFont(new Font(16));but2.setPrefSize(150,30);
        Button but3 = new Button("Exit");but3.setLayoutX(175);but3.setLayoutY(380);but3.setFont(new Font(16));but3.setPrefSize(150,30);
        but.setOnMouseEntered(e-> Keypress(but));
        but.setOnMouseExited(e -> Keyreleased(but));
        but2.setOnMouseEntered(e-> Keypress(but2));
        but2.setOnMouseExited(e -> Keyreleased(but2));
        but3.setOnMouseEntered(e-> Keypress(but3));
        but3.setOnMouseExited(e -> Keyreleased(but3));

        but.setStyle("-fx-background-color: rgba(50, 50, 50, 0.7);-fx-text-fill: rgba(255, 255, 255, 1);");
        but2.setStyle("-fx-background-color: rgba(50, 50, 50, 0.7);-fx-text-fill: rgba(255, 255, 255, 1);");
        but3.setStyle("-fx-background-color: rgba(50, 50, 50, 0.7);-fx-text-fill: rgba(255, 255, 255, 1);");
        TextField textField1 = new TextField("C    L    R");textField1.setLayoutX(140);textField1.setLayoutY(75);textField1.setFont(new Font(40));textField1.setPrefSize(350,60);textField1.setBackground(Background.EMPTY);textField1.setStyle("-fx-text-fill: rgba(90, 90, 90, 1);");
        TextField textField2 = new TextField("S W I T C H");textField2.setLayoutX(120);textField2.setLayoutY(120);textField2.setFont(new Font(40));textField2.setPrefSize(350,60);textField2.setBackground(Background.EMPTY);textField2.setStyle("-fx-text-fill: rgba(90, 90, 90, 1);");

        textField1.setEditable(false);
        textField2.setEditable(false);
        Obsticle m1 = new Rectangle(250,355,130,90,260,12);
        Obsticle m11 = new Rectangle(250,355,130,135,260,12);
        Obsticle m3 = new MyCircle(250,355,145,0,290,15);
        Circle m2 = new Circle(0,0,10,Color.RED);
        Circle m4 = new Circle(0,0,10,Color.YELLOW);
        Circle m5 = new Circle(0,0,10,Color.GREEN);
        Circle m6 = new Circle(0,0,10,Color.BLUE);
        Obsticle c1 = new MyCircle(210,120,14,0,28,4);
        Obsticle c2 = new MyCircle(275,120,14,0,28,4);
        m1.setEffect(new BoxBlur(7, 7, 3));
        m3.setEffect(new BoxBlur(7, 7, 3));
        m11.setEffect(new BoxBlur(10, 10, 3));

        FadeTransition ft = new FadeTransition(Duration.millis(2000), m1);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
        FadeTransition ft1 = new FadeTransition(Duration.millis(2000), m11);
        ft1.setFromValue(1);
        ft1.setToValue(0);
        ft1.setCycleCount(Timeline.INDEFINITE);
        ft1.setAutoReverse(true);
        ft1.play();

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                m1.rotate();
                m11.rotate();
                c1.rotate();
                c1.rotate();
                c2.rotate();
                c2.rotate();
            }
        };
        animationTimer.start();

        Path path = new Path();
        path.getElements().add(new MoveTo(20,20));path.getElements().add(new LineTo(480,20));path.getElements().add(new LineTo(480,580));
        path.getElements().add(new LineTo(20,580));path.getElements().add(new LineTo(20,20));
        PathTransition pathTransition = new PathTransition();pathTransition.setDuration(Duration.millis(4000));pathTransition.setPath(path);
        pathTransition.setNode(m4);pathTransition.setCycleCount(Timeline.INDEFINITE);pathTransition.play();

        Path path2 = new Path();
        path2.getElements().add(new MoveTo(20,580));path2.getElements().add(new LineTo(20,20));path2.getElements().add(new LineTo(480,20));
        path2.getElements().add(new LineTo(480,580));path2.getElements().add(new LineTo(20,580));
        PathTransition pathTransition2 = new PathTransition();pathTransition2.setDuration(Duration.millis(4000));pathTransition2.setPath(path2);
        pathTransition2.setNode(m6);pathTransition2.setCycleCount(Timeline.INDEFINITE);pathTransition2.play();

        Path path3 = new Path();
        path3.getElements().add(new MoveTo(480,20));path3.getElements().add(new LineTo(480,580));path3.getElements().add(new LineTo(20,580));
        path3.getElements().add(new LineTo(20,20));path3.getElements().add(new LineTo(480,20));
        PathTransition pathTransition3 = new PathTransition();pathTransition3.setDuration(Duration.millis(4000));pathTransition3.setPath(path3);
        pathTransition3.setNode(m2);pathTransition3.setCycleCount(Timeline.INDEFINITE);pathTransition3.play();

        Path path4 = new Path();
        path4.getElements().add(new MoveTo(480,580));path4.getElements().add(new LineTo(20,580));path4.getElements().add(new LineTo(20,20));
        path4.getElements().add(new LineTo(480,20));path4.getElements().add(new LineTo(480,580));
        PathTransition pathTransition4 = new PathTransition();pathTransition4.setDuration(Duration.millis(4000));
        pathTransition4.setPath(path4);pathTransition4.setNode(m5);pathTransition4.setCycleCount(Timeline.INDEFINITE);pathTransition4.play();

        p2.getChildren().add(but);but.setShape(new Circle(20,20,20));
        p2.getChildren().add(but2);but2.setShape(new Circle(20,20,20));
        p2.getChildren().add(but3);but3.setShape(new Circle(20,20,20));
        p2.getChildren().add(m1);p2.getChildren().add(m3);p2.getChildren().add(m2);p2.getChildren().add(m4);p2.getChildren().add(m5);p2.getChildren().add(m6);
        p2.setBackground(Background.EMPTY);p2.getChildren().add(textField1);p2.getChildren().add(m11);p2.getChildren().add(c1);p2.getChildren().add(c2);p2.getChildren().add(textField2);

        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = a.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                } else {
                    event.consume();
                }
            }
        });
        but2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    LoadGame(primaryStage,s2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        but.setOnMouseClicked(e -> setpane(primaryStage,s2));
        but3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Platform.exit();
            }
        });
        primaryStage.show();
    }

    public void setpane(Stage primaryStage,Scene mainscene) {
        this.background.stop();
        StartnewGame(primaryStage,mainscene);
    }
    public void StartnewGame(Stage primaryStage,Scene mainscene) {
        primaryStage.setTitle("COLOR SWITCH");
        Pane root = new Pane();
        root.setBackground(Background.EMPTY);
        Scene scene = new Scene(root,500,600,Color.BLACK);
        primaryStage.setScene(scene);
        Ball b = new Ball(250,450,10,"red");
        Random nowrandom = new Random();
        String[] nowreally = {"red","blue","green","yellow"};
        int choice = nowrandom.nextInt(4);
        b.setColour(nowreally[choice]);
        Obsticle[] arr = new Obsticle[7];
        Obsticle obs1 = new Rectangle(250,-200,100,0,200,12);
        Obsticle obs2 = new MyCircle(250,-200,100,0,200,15);
        Obsticle obs3 = new Parallelogram(250,-200,130,0,260,1.2,12);
        Obsticle obs4 = new Line_Obsticle(250,250,-200,100,60,8);
        Obsticle obs5 = new CrossBar(250,-200,100,0,200,15,90);
        Obsticle obs6 = new DoubleCircle(250,-200,85,45,45,200,15);
        Obsticle obs7 = new TripleCircle(250,-215,85,45,45,215,15);

        Difficulty l = new Difficulty();
        arr[0] = obs1; arr[1] = obs2; arr[2] = obs3; arr[3] = obs4; arr[4] = obs5; arr[5] = obs6; arr[6] = obs7;
        Player p = new Player(NewGame);
        p.setGame(this);
        p.setBall(b);
        p.setDifficulty(l);

        p.setPane(root);
        p.setScene(mainscene);
        p.setGameScene(scene);
        p.setStage(primaryStage);

        p.setObsticles(arr);
        for (Obsticle o: arr){
            o.setPlayer(p);
            root.getChildren().add(o);
        }
        Random random = new Random();
        Obsticle obsticle1 = arr[random.nextInt(7)];
        p.setObsticle1(obsticle1);
        Star star = new Star(250,-400,15);
        star.setInAction(true);
        p.setStar(star);
        star.setPlayer(p);
        star.setY(obsticle1.getY() - obsticle1.getHeight()/2 - 100);

        ColorSwitch colorSwitch = new ColorSwitch(250,-200,10);
        colorSwitch.setInAction(false);
        colorSwitch.setPlayer(p);
        p.setColorSwitch(colorSwitch);

        p.setBackground(this.background);

        root.getChildren().add(b);
        root.getChildren().add(l);
        root.getChildren().add(star);
        root.getChildren().add(colorSwitch);

        AnimationTimer timer = new MyTimer(p);
        timer.start();

        Button but = new Button("| |");
        but.setLayoutX(220);but.setLayoutY(20);but.setFont(new Font(16));but.setPrefSize(50,50);but.setStyle("-fx-background-color: rgba(50, 50, 50, 0.7);-fx-text-fill: rgba(255, 255, 255, 1);");
        root.getChildren().add(but);
        but.setDisable(true);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    keyPress(p,root);
                    keyEvent.consume();
                }
                else if (keyEvent.getCode() == KeyCode.P) {
                    but.setDisable(false);
                    Keypress(but);
                    keyEvent.consume();
                    timer.stop();
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.P) {
                    pauseOption(primaryStage,arr,colorSwitch,star,b,scene,timer,p);
                    but.setDisable(true);
                    Keyreleased(but);
                    keyEvent.consume();
                }
            }
        });
        primaryStage.setScene(scene);
        timer.start();
        primaryStage.show();
    }
    public void pauseOption(Stage stage,Obsticle[] arr,ColorSwitch colorswitch,Star star,Ball ball,Scene game,AnimationTimer timer,Player player){
        Pane root = new Pane();
        root.setBackground(Background.EMPTY);
        Scene scene = new Scene(root,500,600,Color.BLACK);
        stage.setScene(scene);
        TextField textField1 = new TextField("P A U S E   M E N U");textField1.setLayoutX(50);textField1.setLayoutY(75);textField1.setFont(new Font(40));textField1.setPrefSize(420,60);textField1.setBackground(Background.EMPTY);textField1.setStyle("-fx-text-fill: rgba(150, 150, 150, 1);-fx-font-weight: bold italic;");textField1.setEditable(false);
        NewGame.continue_im.setLayoutX(200);NewGame.continue_im.setLayoutY(220);
        NewGame.save_im.setLayoutX(220);NewGame.save_im.setLayoutY(330);
        NewGame.home_im.setLayoutX(220);NewGame.home_im.setLayoutY(425);
        root.getChildren().add(NewGame.continue_im);
        root.getChildren().add(NewGame.save_im);
        root.getChildren().add(NewGame.home_im);
        root.getChildren().add(textField1);
        NewGame.continue_im.setOnMouseClicked(e -> {stage.setScene(game);timer.start();});
        NewGame.save_im.setOnMouseClicked(e -> {
            try {
                SavingGame(stage,arr,colorswitch,star,ball,player);}
            catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();}
        });
        NewGame.home_im.setOnMouseClicked(e -> MainMenu(stage));

    }

    public void SavingGame(Stage stage, Obsticle[] arr, ColorSwitch colorSwitch, Star star, Ball ball,Player player) throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);
        boolean fileexist = false;
        try {
            File myObj = new File("total_id.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
                fileexist = true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int person_id = 0;
        if(ball.get_Id()==-1){
            if(fileexist) {
                Scanner tot_id = new Scanner(new FileReader("total_id.txt"));
                person_id = Integer.parseInt(tot_id.next());
                tot_id.close();
                PrintWriter writer = new PrintWriter(new File("total_id.txt"));
                ball.setId(person_id+1);
                writer.append(String.valueOf(person_id + 1));
                writer.flush();
                writer.close();
            }
            else{
                PrintWriter writer = new PrintWriter(new File("total_id.txt"));
                ball.setId(1);
                writer.append((1)+"");
                writer.flush();
                writer.close();
            }
        }
        File file = new File(currentDirectory+"\\save");
        //Creating the directory
        boolean bool = file.mkdir();
        if(bool){
            System.out.println("Directory created successfully");
        }else{
            System.out.println("Sorry couldn't create specified directory");
        }
        try {
            File myObj = new File(currentDirectory+"\\"+"save\\"+ball.get_Id()+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = new PrintWriter(new File(currentDirectory+"\\"+"save\\"+ball.get_Id()+".txt"));
        for (Obsticle ob : arr) {
            String str_ob = ob+"";
            if(str_ob.charAt(0) == 'R'){
                writer.append("R ").append(String.valueOf(ob.getX())).append(" ").append(String.valueOf(ob.getY())).append(" ").append(String.valueOf(ob.getR())).append(" ").append(String.valueOf(ob.getTheta())).append(" ").append(String.valueOf(ob.getHeight())).append(" ").append(String.valueOf(ob.getWidth())).append("\n");
            }
            else if(str_ob.charAt(0) == 'M'){
                writer.append("M ").append(String.valueOf(ob.getX())).append(" ").append(String.valueOf(ob.getY())).append(" ").append(String.valueOf(ob.getR())).append(" ").append(String.valueOf(ob.getTheta())).append(" ").append(String.valueOf(ob.getHeight())).append(" ").append(String.valueOf(ob.getWidth())).append("\n");
            }
            else if(str_ob.charAt(0) == 'P'){
                writer.append("P ").append(String.valueOf(ob.getX())).append(" ").append(String.valueOf(ob.getY())).append(" ").append(String.valueOf(ob.getR())).append(" ").append(String.valueOf(ob.getTheta())).append(" ").append(String.valueOf(ob.getHeight())).append(" ").append(String.valueOf(ob.getChange())).append(" ").append(String.valueOf(ob.getWidth())).append("\n");
            }
            else if(str_ob.charAt(0) == 'L'){
                Line_Obsticle objx1 = (Line_Obsticle)ob;
                writer.append("L ").append(String.valueOf(ob.getX())).append(" ").append(String.valueOf(objx1.getX1())).append(" ").append(String.valueOf(ob.getY())).append(" ").append(String.valueOf(ob.getHeight())).append(" ").append(String.valueOf(ob.getDiff())).append(" ").append(String.valueOf(ob.getWidth())).append("\n");
            }
            else if (str_ob.charAt(0) == 'C'){
                CrossBar obj = (CrossBar)ob;
                writer.append("O ").append(String.valueOf(obj.getX())).append(" ").append(String.valueOf(obj.getY())).append(" ").append(String.valueOf(obj.getR())).append(" ").append(String.valueOf(obj.getTheta())).append(" ").append(String.valueOf(obj.getHeight())).append(" ").append(String.valueOf(obj.getWidth())).append(" ").append(String.valueOf(obj.getDiff())).append("\n");
            }
            else if (str_ob.charAt(0)=='D'){
                DoubleCircle obj = (DoubleCircle)ob;
                writer.append("D ").append(String.valueOf(obj.getX())).append(" ").append(String.valueOf(obj.getY())).append(" ").append(String.valueOf(obj.getR())).append(" ").append(String.valueOf(obj.getTheta())).append(" ").append(String.valueOf(obj.getTheta1())).append(" ").append(String.valueOf(obj.getHeight())).append(" ").append(String.valueOf(obj.getWidth())).append("\n");
            }
            else if (str_ob.charAt(0)=='T'){
                TripleCircle obj = (TripleCircle)ob;
                writer.append("T ").append(String.valueOf(obj.getX())).append(" ").append(String.valueOf(obj.getY())).append(" ").append(String.valueOf(obj.getR())).append(" ").append(String.valueOf(obj.getTheta())).append(" ").append(String.valueOf(obj.getTheta1())).append(" ").append(String.valueOf(obj.getHeight())).append(" ").append(String.valueOf(obj.getWidth())).append("\n");
            }
        }
        writer.append("S ").append(String.valueOf(star.getX())).append(" ").append(String.valueOf(star.getY())).append(" ").append(String.valueOf(star.getR())).append(" ").append(star.getInAction()+"").append("\n");
        writer.append("B ").append(String.valueOf(ball.getX())).append(" ").append(String.valueOf(ball.getY())).append(" ").append(String.valueOf(ball.getR())).append(" ").append(ball.getColour()).append("\n");
        String obstic1 = player.getObsticle1()+"";
        String obstic2 = player.getObsticle2()+"";
        writer.append("X ").append(String.valueOf(player.getStars())).append(" ").append(String.valueOf(player.getL())).append(" ").append(" ").append(obstic1).append(" ").append(obstic2).append(" ").append("\n");
        System.out.println(obstic1);
        System.out.println(obstic2);
        writer.append("C ").append(String.valueOf(colorSwitch.getX())).append(" ").append(String.valueOf(colorSwitch.getY())).append(" ").append(String.valueOf(colorSwitch.getR())).append(" ").append(colorSwitch.getInAction()+"").append("\n");
        writer.flush();
        MainMenu(stage);
    }

    public void LoadGame(Stage stage,Scene mainmenu) throws IOException {
        this.background.stop();
        String currentDirectory = System.getProperty("user.dir");
        List<String> textFiles = new ArrayList<String>();
        File dir = new File(currentDirectory+"\\save");
        System.out.println(Arrays.toString(dir.listFiles()));

        if(dir.listFiles()==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error Occured");
            alert.setContentText("Hello our dear user,\nThere are no files saved.");

            alert.showAndWait();
        }
        else {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith((".txt"))) {
                    textFiles.add(file.getName());
                }
            }
            Pane root = new Pane();
            root.setBackground(Background.EMPTY);
            Scene scene = new Scene(root,500,600,Color.BLACK);
            stage.setScene(scene);
            TextField textField = new TextField("All the saved Games are: ");textField.setLayoutX(60);textField.setLayoutY(40);textField.setFont(new Font(24));textField.setPrefSize(650,60);textField.setBackground(Background.EMPTY);textField.setStyle("-fx-text-fill: rgba(255, 255, 255, 1);");textField.setEditable(false);
            root.getChildren().add(textField);

            TextField textFields = new TextField();
            ///////////// saves the text file names in an array list ///////////////
            ArrayList<TextField> textarr = new ArrayList<TextField>();
            int i = 0;
            for (String s : textFiles) {
                i++;
                textFields = new TextField(i+"   " +i+".txt");
                textFields.setLayoutX(40);textFields.setLayoutY(100+30*i);textFields.setBackground(Background.EMPTY);textFields.setStyle("-fx-text-fill: rgba(100, 200, 100, 1);");textFields.setEditable(false);textFields.setFont(new Font(16));
                root.getChildren().add(textFields);
                textarr.add(textFields);
                ///////////////// added the TextField object of the .txt files in the arraylist
            }

            ////////// code added to enable scrolling ////////////
            root.setOnScroll((ScrollEvent e) -> {
                double delta = e.getDeltaY();
                if (delta<0){
                    //System.out.println("negative");
                    for (int j=0;j<textarr.size();j++){
                        TextField text = textarr.get(j);
                        text.setLayoutY(text.getLayoutY()-5);
                    }
                }
                else if (delta>0){
                    //System.out.println("positive");
                    for (int j=0;j<textarr.size();j++){
                        TextField text = textarr.get(j);
                        text.setLayoutY(text.getLayoutY()+5);
                    }
                }
            });
            ////////// code finished to enable scrolling //////////

            TextField Enter_File_Number = new TextField("Please enter the file number you want to load ");Enter_File_Number.setLayoutX(20);Enter_File_Number.setLayoutY(450);Enter_File_Number.setFont(new Font(15));Enter_File_Number.setPrefSize(350,40);Enter_File_Number.setBackground(Background.EMPTY);Enter_File_Number.setStyle("-fx-text-fill: rgba(200, 200, 200, 1);");Enter_File_Number.setEditable(false);
            root.getChildren().add(Enter_File_Number);
            TextField text_input_number = new TextField("");text_input_number.setLayoutX(350);text_input_number.setLayoutY(450);text_input_number.setFont(new Font(16));text_input_number.setPrefSize(50,20);
            root.getChildren().add(text_input_number);
            Button submit = new Button("Submit the Entered File");submit.setLayoutX(80);submit.setLayoutY(520);submit.setFont(new Font(20));submit.setStyle("-fx-text-fill: rgba(100, 55, 155, 1);");

            root.getChildren().add(submit);
            root.getChildren().add(NewGame.home_im);NewGame.home_im.setLayoutX(350);NewGame.home_im.setLayoutY(500);
            NewGame.home_im.setOnMouseClicked(e -> MainMenu(stage));
            submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int size = textFiles.size();
                    if(text_input_number.getText().equals("")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("No String Found");
                        alert.setContentText("Hello our dear user,\nPlease enter a Number before submitting");
                        alert.showAndWait();
                    }
                    else if(Integer.parseInt(text_input_number.getText())>size || Integer.parseInt(text_input_number.getText())<0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error Found");
                        alert.setContentText("Hello our dear user,\nPlease enter a valid number from "+0+" to "+ size);
                        alert.showAndWait();
                    }
                    else {
                        int filenumber = Integer.parseInt(text_input_number.getText())-1;
                        ////////////////////////////////////////
                        String filename = /*textFiles.get(filenumber);*/ (filenumber+1)+".txt";
                        System.out.println(filename);
                        File ansfile = new File(currentDirectory+"\\save\\"+filename);
                        try {
                            loadObjects(stage,ansfile,mainmenu);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
    public void loadObjects(Stage stage, File ansfile,Scene mainmenu) throws FileNotFoundException {
        Scanner text = new Scanner(ansfile);
        int lineNumber = 1;
        Pane root = new Pane();
        root.setBackground(Background.EMPTY);
        Scene scene = new Scene(root,500,600,Color.BLACK);
        stage.setScene(scene);
        Ball b = null;Obsticle[] obsticles = new Obsticle[7];
        Star star = null;ColorSwitch colorSwitch = null;
        Difficulty difficulty = null;Player p = new Player(NewGame);
        Obsticle rec = null;Obsticle mycircle = null;Obsticle parr = null;Obsticle line_obs = null;Obsticle crossbar = null;Obsticle doublecircle = null;Obsticle triplecircle = null;
        String Obsticle1String = "";
        String Obsticle2String = "";

        while(text.hasNext()){
            String line = text.next();
            System.out.println("line " + lineNumber + " :" + line);
            stage.setTitle("COLOR SWITCH");
            if(line.charAt(0) == 'B'){
                System.out.println("Its a ball");
                b = new Ball(text.nextDouble(),text.nextDouble(),text.nextDouble(),text.next());
                root.getChildren().add(b);
            }
            else if(line.charAt(0) == 'R'){
                System.out.println("Its a rectangle");
                rec = new Rectangle(text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble());
                obsticles[0] = rec;
            }
            else if(line.charAt(0) == 'M'){
                System.out.println("Its my Circle");
                mycircle = new MyCircle(text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble());
                obsticles[1] = mycircle;
            }
            else if(line.charAt(0) == 'P'){
                System.out.println("Parallelogram");
                parr = new Parallelogram(text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble());
                obsticles[2] = parr;
            }
            else if(line.charAt(0) == 'L'){
                System.out.println("Line_Obsticle");
                line_obs = new Line_Obsticle(text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble());
                obsticles[3] = line_obs;
            }
            else if (line.charAt(0) == 'O'){
                System.out.println("CrossBar");
                crossbar = new CrossBar(text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble());
                obsticles[4] = crossbar;
            }
            else if (line.charAt(0) == 'D'){
                System.out.println("DoubleCircle");
                doublecircle = new DoubleCircle(text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble());
                obsticles[5] = doublecircle;
            }
            else if (line.charAt(0) == 'T'){
                System.out.println("TripleCircle");
                triplecircle = new TripleCircle(text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble(),text.nextDouble());
                obsticles[6] = triplecircle;
            }
            else if(line.charAt(0) == 'S'){
                System.out.println("Its a star");
                star = new Star(text.nextDouble(),text.nextDouble(),text.nextDouble());
                String bool = text.next();
                if(bool.charAt(0) == 'f'){star.setInAction(false);}
                else{star.setInAction(true);}

                root.getChildren().add(star);
            }
            else if(line.charAt(0) == 'C'){
                System.out.println("Its a color Switch");
                colorSwitch = new ColorSwitch(text.nextDouble(),text.nextDouble(),text.nextDouble());
                String bool = text.next();
                if(bool.charAt(0) == 'f'){colorSwitch.setInAction(false);}
                else{colorSwitch.setInAction(true);}

                root.getChildren().add(colorSwitch);
            }
            else if(line.charAt(0) == 'X'){
                System.out.println("Its a Player");
                int starCount = text.nextInt();
                System.out.println(starCount);
                difficulty = new Difficulty();
                difficulty.setLevel(text.nextInt());
                p.setPane(root);
                p.setStars(starCount);
                p.setDifficulty(difficulty);
                root.getChildren().add(difficulty);
                Obsticle1String = text.next();
                Obsticle2String = text.next();
            }
            lineNumber++;
        }

        Button but = new Button("| |");
        but.setLayoutX(220);but.setLayoutY(20);but.setFont(new Font(16));but.setPrefSize(50,50);but.setStyle("-fx-background-color: rgba(50, 50, 50, 0.7);-fx-text-fill: rgba(255, 255, 255, 1);");
        root.getChildren().add(but);
        but.setDisable(true);

        p.setGame(this);
        p.setBall(b);
        p.setColorSwitch(colorSwitch);
        p.setStar(star);
        p.setObsticles(obsticles);
        p.setStage(stage);
        p.setScene(mainmenu);
        p.setGameScene(scene);
        p.setBackground(this.background);

        root.getChildren().add(rec);
        root.getChildren().add(mycircle);
        root.getChildren().add(parr);
        root.getChildren().add(line_obs);
        root.getChildren().add(crossbar);
        root.getChildren().add(doublecircle);
        root.getChildren().add(triplecircle);

        if(Obsticle1String.charAt(0) == 'R'){p.setObsticle1(rec);}
        else if(Obsticle1String.charAt(0) == 'M'){p.setObsticle1(mycircle);}
        else if(Obsticle1String.charAt(0) == 'P'){p.setObsticle1(parr);}
        else if(Obsticle1String.charAt(0) == 'L'){p.setObsticle1(line_obs);}
        else if (Obsticle1String.charAt(0) == 'C'){p.setObsticle1(crossbar);}
        else if (Obsticle1String.charAt(0) == 'D'){p.setObsticle1(doublecircle);}
        else if (Obsticle1String.charAt(0) == 'T'){p.setObsticle1(triplecircle);}

        if(Obsticle2String.charAt(0) == 'n'){p.setObsticle2(null);}
        else if(Obsticle2String.charAt(0) == 'R'){p.setObsticle2(rec);}
        else if(Obsticle2String.charAt(0) == 'M'){p.setObsticle2(mycircle);}
        else if(Obsticle2String.charAt(0) == 'P'){p.setObsticle2(parr);}
        else if(Obsticle2String.charAt(0) == 'L'){p.setObsticle2(line_obs);}
        else if (Obsticle2String.charAt(0) == 'C'){p.setObsticle2(crossbar);}
        else if (Obsticle2String.charAt(0) == 'D'){p.setObsticle2(doublecircle);}
        else if (Obsticle2String.charAt(0) == 'T'){p.setObsticle2(triplecircle);}

        System.out.println(p.getObsticle1());
        System.out.println(p.getObsticle2());


        AnimationTimer timer = new MyTimer(p);
        timer.start();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    keyPress(p,root);
                    keyEvent.consume();
                }
                else if (keyEvent.getCode() == KeyCode.P) {
                    but.setDisable(false);
                    Keypress(but);
                    keyEvent.consume();
                    timer.stop();
                }
            }
        });
        ColorSwitch finalColorSwitch = colorSwitch;
        Star finalStar = star;
        Ball finalB = b;
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.P) {
                    pauseOption(stage, obsticles, finalColorSwitch, finalStar, finalB, scene, timer, p);
                    but.setDisable(true);
                    Keyreleased(but);
                    keyEvent.consume();
                }
            }
        });
        stage.setScene(scene);
        timer.start();
        stage.show();

    }
    public ImageView home_im;
    public ImageView save_im;
    public ImageView cartoon;
    public ImageView exit;
    public ImageView gameover;
    public ImageView restart;
    public ImageView continue_im;
    public ImageView five;
    public ImageView star_im;
    public ImageView top_score;
    public ImageView greensmash;
    public ImageView bluesmash;
    public ImageView yellowsmash;
    public ImageView redsmash;
    public static Game NewGame;
    public ImageView initialize_home() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(495);imageView.setLayoutX(170);imageView.setFitHeight(65);imageView.setFitWidth(65);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/home.png");Image image=new Image(input);
        imageView.setOnMouseEntered(e -> imageView.setOpacity(0.5));
        imageView.setOnMouseExited(e -> imageView.setOpacity(1));
        imageView.setImage(image);imageView.setEffect(new Reflection(0,0.5,0.5,0));
        return imageView;
    }
    private ImageView initialize_save() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(50);imageView.setLayoutX(50);imageView.setFitHeight(65);imageView.setFitWidth(65);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/save.png");Image image=new Image(input);
        imageView.setOnMouseEntered(e -> imageView.setOpacity(0.5));
        imageView.setOnMouseExited(e -> imageView.setOpacity(1));
        imageView.setImage(image);imageView.setEffect(new Reflection(0,0.5,0.5,0));
        return imageView;
    }
    private ImageView initialize_continue() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(480);imageView.setLayoutX(270);imageView.setFitHeight(100);imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/continue.png");Image image=new Image(input);
        imageView.setImage(image);imageView.setEffect(new Reflection(-38,0.5,0.5,0));
        imageView.setOnMouseEntered(e -> imageView.setOpacity(0.5));
        imageView.setOnMouseExited(e -> imageView.setOpacity(1));
        return imageView;
    }

//    private ImageView initialize_home() throws FileNotFoundException {
//        String currentDirectory = System.getProperty("user.dir");
//        ImageView imageView=new ImageView();imageView.setLayoutY(495);imageView.setLayoutX(170);imageView.setFitHeight(65);imageView.setFitWidth(65);
//        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
//        FileInputStream input = new FileInputStream(currentDirectory+"/images/home.png");Image image=new Image(input);
//        imageView.setImage(image);
//        imageView.setOnMouseEntered(e -> imageView.setOpacity(0.5));
//        imageView.setOnMouseExited(e -> imageView.setOpacity(1));
//        return imageView;
//    }
    public ImageView initialize_cartoon() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(400);imageView.setLayoutX(0);imageView.setFitHeight(200);imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/cartoon.png");Image image=new Image(input);
        imageView.setImage(image);
        return imageView;
    }
    public ImageView initialize_topscore() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(140);imageView.setLayoutX(150);imageView.setFitHeight(100);imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/topscore.png");Image image=new Image(input);
        imageView.setImage(image);
        return imageView;
    }
    public ImageView initialize_gameover() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(30);imageView.setLayoutX(160);imageView.setFitHeight(100);imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/gameover.png");Image image=new Image(input);
        imageView.setImage(image);
        return imageView;
    }
    public ImageView initialize_five() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(230);imageView.setLayoutX(400);imageView.setFitHeight(20);imageView.setFitWidth(20);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/five.png");Image image=new Image(input);
        imageView.setImage(image);
        return imageView;
    }
    public ImageView initialize_star() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(230);imageView.setLayoutX(400);imageView.setFitHeight(20);imageView.setFitWidth(20);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/star.png");Image image=new Image(input);
        imageView.setImage(image);
        return imageView;
    }
//    private ImageView initialize_continue() throws FileNotFoundException {
//        String currentDirectory = System.getProperty("user.dir");
//        ImageView imageView=new ImageView();imageView.setLayoutY(480);imageView.setLayoutX(270);imageView.setFitHeight(100);imageView.setFitWidth(100);
//        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
//        FileInputStream input = new FileInputStream(currentDirectory+"/images/continue.png");Image image=new Image(input);
//        imageView.setImage(image);
//        imageView.setOnMouseEntered(e -> imageView.setOpacity(0.5));
//        imageView.setOnMouseExited(e -> imageView.setOpacity(1));
//        return imageView;
//    }
    public ImageView initialize_restart() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(500);imageView.setLayoutX(400);imageView.setFitHeight(60);imageView.setFitWidth(60);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/restart.png");Image image=new Image(input);
        imageView.setImage(image);
        imageView.setOnMouseEntered(e -> imageView.setOpacity(0.5));
        imageView.setOnMouseExited(e -> imageView.setOpacity(1));
        return imageView;
    }
    public ImageView initialize_bluesmash() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(100);imageView.setLayoutX(100);imageView.setFitHeight(79);imageView.setFitWidth(95);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/bluesmash.png");Image image=new Image(input);
        imageView.setImage(image);
        return imageView;
    }
    public ImageView initialize_yellowsmash() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(150);imageView.setLayoutX(150);imageView.setFitHeight(79);imageView.setFitWidth(95);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/yellowsmash.png");Image image=new Image(input);
        imageView.setImage(image);
        return imageView;
    }
    public ImageView initialize_greensmash() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(200);imageView.setLayoutX(200);imageView.setFitHeight(79);imageView.setFitWidth(95);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/greensmash.png");Image image=new Image(input);
        imageView.setImage(image);
        return imageView;
    }
    public ImageView initialize_redsmash() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        ImageView imageView=new ImageView();imageView.setLayoutY(250);imageView.setLayoutX(250);imageView.setFitHeight(79);imageView.setFitWidth(95);
        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
        FileInputStream input = new FileInputStream(currentDirectory+"/images/redsmash.png");Image image=new Image(input);
        imageView.setImage(image);
        return imageView;
    }
//    private ImageView initialize_exit() throws FileNotFoundException {
//        String currentDirectory = System.getProperty("user.dir");
//        ImageView imageView=new ImageView();imageView.setLayoutY(500);imageView.setLayoutX(170);imageView.setFitHeight(65);imageView.setFitWidth(65);
//        imageView.setPreserveRatio(true);imageView.setPickOnBounds(true);
//        FileInputStream input = new FileInputStream(currentDirectory+"/images/exit.png");Image image=new Image(input);
//        imageView.setImage(image);imageView.setEffect(new Reflection(0,0.5,0.5,0));
//        imageView.setOnMouseEntered(e -> imageView.setOpacity(0.5));
//        imageView.setOnMouseExited(e -> imageView.setOpacity(1));
//        return imageView;
//    }
    @Override
    public void start(Stage stage) throws Exception{
        MainMenu(stage);
        stage.show();
    }

    public void keyPress(Player p,Pane root){
        p.jump();
    }

    public static void main(String[] args) throws FileNotFoundException {
        NewGame =  new Game();
        System.out.println(NewGame.cartoon);
        System.out.println(NewGame.continue_im);
        System.out.println(NewGame.exit);
        System.out.println(NewGame.five);
        System.out.println(NewGame.home_im);
        System.out.println(NewGame.greensmash);
        System.out.println(NewGame.bluesmash);
        System.out.println(NewGame.redsmash);


        launch(args);
    }
}
