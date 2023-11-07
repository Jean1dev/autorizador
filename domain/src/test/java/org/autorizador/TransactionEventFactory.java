package org.autorizador;

import com.github.javafaker.Faker;
import org.autorizador.account.AccountEvent;
import org.autorizador.transaction.TransactionEvent;

import java.time.LocalDateTime;

public final class TransactionEventFactory {

    public static TransactionEvent fromNow() {
        return new TransactionEvent(
                new Faker().name().name(),
                2.00,
                LocalDateTime.now()
        );
    }

    public static TransactionEvent fromTime(LocalDateTime time) {
        return new TransactionEvent(
                new Faker().name().name(),
                2.00,
                time
        );
    }

    public static TransactionEvent fromAmmout(double ammount) {
        return new TransactionEvent(
                new Faker().name().name(),
                ammount,
                LocalDateTime.now()
        );
    }

    public static AccountEvent accoutEvent() {
        return new AccountEvent(true, 250.0);
    }

    public static AccountEvent accoutCardNotActive() {
        return new AccountEvent(false, 250.0);
    }

}
