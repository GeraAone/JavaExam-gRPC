package com.geralab.JavaExam.mappers;

import com.geralab.JavaExam.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class ProtoMapper {
    public com.geralab.JavaExam.Country mapToProtoCountry(Country protoCountry) {
        switch (protoCountry) {
            case RUSSIA:
                return com.geralab.JavaExam.Country.RUSSIA;
            case AUSTRALIA:
                return com.geralab.JavaExam.Country.AUSTRALIA;
            case CANADA:
                return com.geralab.JavaExam.Country.CANADA;
            case INDIA:
                return com.geralab.JavaExam.Country.INDIA;
            case JAPAN:
                return com.geralab.JavaExam.Country.JAPAN;
            default:
                throw new IllegalArgumentException("Unknown country: " + protoCountry);
        }
    }

    public Country mapFromProtoCountry(com.geralab.JavaExam.Country protoCountry) {
        switch (protoCountry) {
            case com.geralab.JavaExam.Country.RUSSIA:
                return Country.RUSSIA;
            case com.geralab.JavaExam.Country.AUSTRALIA:
                return Country.AUSTRALIA;
            case com.geralab.JavaExam.Country.CANADA:
                return Country.CANADA;
            case com.geralab.JavaExam.Country.INDIA:
                return Country.INDIA;
            case com.geralab.JavaExam.Country.JAPAN:
                return Country.JAPAN;
            default:
                throw new IllegalArgumentException("Unknown country: " + protoCountry);
        }
    }
}
