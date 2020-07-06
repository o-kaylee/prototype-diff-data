package dev.sunghyun.prototypediffdata.common;

public class TranslateException extends RuntimeException{
    TranslateExceptionCode translateExceptionCode;

    public TranslateException(TranslateExceptionCode translateExceptionCode) {
        super(translateExceptionCode.name());
        this.translateExceptionCode = translateExceptionCode;
    }
    
    public TranslateException(TranslateExceptionCode translateExceptionCode, Throwable ex) {
        super(translateExceptionCode.name(), ex);
        this.translateExceptionCode = translateExceptionCode;
    }

    public TranslateException(TranslateExceptionCode translateExceptionCode, String message) {
        super(message);
        this.translateExceptionCode = translateExceptionCode;
    }

    public TranslateExceptionCode getTranslateExceptionCode() {
        return translateExceptionCode;
    }

    public static TranslateException of(TranslateExceptionCode translateExceptionCode) {
        return new TranslateException(translateExceptionCode);
    }
}
