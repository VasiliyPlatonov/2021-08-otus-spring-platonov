package ru.vasiliyplatonov.homework1.service;

import org.apache.commons.csv.CSVFormat;

public class CSVFormatFactory {

    private final CSVFormat initialCSVFormat;

    public CSVFormatFactory(CSVFormat initialCSVFormat) {
        this.initialCSVFormat = initialCSVFormat;
    }

    public CSVFormat create(String[] headers) {
        return initialCSVFormat
                .withFirstRecordAsHeader()
                .withHeader(headers);
    }

}
