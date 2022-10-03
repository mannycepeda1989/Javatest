package com.baeldung.digitalsignature.level2;

import com.baeldung.digitalsignature.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.security.Signature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigitalSignatureWithSignatureVerifying {

    static final Logger log = LoggerFactory.getLogger(DigitalSignatureWithSignatureVerifying.class);

    public static void main(String[] args) throws Exception {

        PublicKey publicKey = Utils.getPublicKey();

        byte[] sig = Files.readAllBytes(Paths.get("target/digital_signature_2"));

        Signature signature = Signature.getInstance(Utils.SIGNING_ALGORITHM);
        signature.initVerify(publicKey);

        byte[] messageBytes = Files.readAllBytes(Paths.get("src/test/resources/digitalsignature/message.txt"));

        signature.update(messageBytes);

        boolean isCorrect = signature.verify(sig);
        log.info("Signature " + (isCorrect ? "correct" : "incorrect"));
    }
}
