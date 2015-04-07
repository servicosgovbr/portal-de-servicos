package br.gov.servicos.cartaServicos;

import lombok.Data;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

import java.io.InputStream;
import java.io.Reader;

class ClassPathResolver implements LSResourceResolver {

    @Override
    public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
        return new Input(systemId, getClass().getResourceAsStream(systemId));
    }

    @Data
    private static class Input implements LSInput {

        Reader characterStream;
        InputStream byteStream;
        String stringData;
        String systemId;
        String publicId;
        String baseURI;
        String encoding;
        boolean certifiedText;

        Input(String systemId, InputStream byteStream) {
            this.systemId = systemId;
            this.byteStream = byteStream;
        }

        @Override
        public boolean getCertifiedText() {
            return false;
        }
    }
}
