package util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonFunctions {
    public static String setDate;
    public static String setTime;

    public static void setNotificationSuccess(String buttonTitle, String functionMessage) {
        String title = buttonTitle;
        String message = functionMessage;
        TrayNotification notification = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        notification.setAnimationType(type);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setNotificationType(NotificationType.SUCCESS);
        notification.showAndDismiss(Duration.millis(8000));
    }

    public static void setNotificationWarning(String buttonTitle, String functionMessage) {
        String title = buttonTitle;
        String message = functionMessage;
        TrayNotification notification = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        notification.setAnimationType(type);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setNotificationType(NotificationType.WARNING);
        notification.showAndDismiss(Duration.millis(8000));
    }

    public static void animatedWheels(ImageView image1, ImageView image2) {
        Timeline rotate = new Timeline();
        Timeline rotate1 = new Timeline();
        DoubleProperty r = image1.rotateProperty();
        DoubleProperty r1 = image2.rotateProperty();

        rotate1.getKeyFrames().addAll(
                new KeyFrame(new Duration(0), new KeyValue(r, 0)),
                new KeyFrame(new Duration(1000), new KeyValue(r, -360))
        );
        rotate.getKeyFrames().addAll(
                new KeyFrame(new Duration(0), new KeyValue(r1, 0)),
                new KeyFrame(new Duration(1000), new KeyValue(r1, 360))
        );
        rotate1.play();
        rotate.play();
    }

    public static void loadDateAndTime(Label lblTime) {
        Date date = new Date();

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        setDate = simpleDateFormat1.format(date);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<javafx.event.ActionEvent>() {
                            @Override
                            public void handle(javafx.event.ActionEvent event) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
                                lblTime.setStyle("-fx-font-size: 15px;");
                                lblTime.setText(simpleDateFormat.format(time.getTime()));
                                setTime = simpleDateFormat.format(time.getTime());
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
