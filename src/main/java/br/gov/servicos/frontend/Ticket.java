package br.gov.servicos.frontend;

import java.util.UUID;

public class Ticket {

    private static final ThreadLocal<UUID> TICKET = new ThreadLocal<>();

    public static UUID atual() {
        if(TICKET.get() == null) {
            TICKET.set(UUID.randomUUID());
        }
        return TICKET.get();
    }

}
