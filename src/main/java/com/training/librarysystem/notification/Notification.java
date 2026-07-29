package com.training.librarysystem.notification;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Notification {

    private long id;

    private long userId;

    private NotificationType type;

    private String message;

    private Date creationDate;

    private boolean read;
}
