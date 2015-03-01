package br.gov.servicos.frontend;

import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.UUID;

@Component
public class Tickets implements Iterator<UUID> {

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public UUID next() {
        return UUID.randomUUID();
    }
}
