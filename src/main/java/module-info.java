module uk.co.conoregan.watch2getherapi {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires org.slf4j;
    requires jdk.crypto.cryptoki;

    opens uk.co.conoregan.watch2getherapi.model to com.fasterxml.jackson.databind;

    exports uk.co.conoregan.watch2getherapi;
    exports uk.co.conoregan.watch2getherapi.model;
    exports uk.co.conoregan.watch2getherapi.http;
    exports uk.co.conoregan.watch2getherapi.util;
    exports uk.co.conoregan.watch2getherapi.exception;
}
