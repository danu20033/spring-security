package com.danushka.test.springsecurityclient.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class VerificationToken {


    private static final int EXP_TIME = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String token;
    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN"))
    private User user;

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationTime = calculateExpirationDate();
    }

    public VerificationToken(String token) {
        this.token = token;
        this.expirationTime = calculateExpirationDate();
    }

    private Date calculateExpirationDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXP_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
