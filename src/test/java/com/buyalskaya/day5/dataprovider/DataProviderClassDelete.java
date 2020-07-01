package com.buyalskaya.day5.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderClassDelete {
    @DataProvider(name = "dataForDeleteNotLetterExceptSpace")
    public static final Object[][] dataForDeleteNotLetterExceptSpace() {
        return new Object[][]{
                {"Shut up, Tara! Hey look, here they come!", "Shut up  Tara  Hey look  here they come "},
                {"1+1=2", "     "},
                {"он вздрогнул: лицо его приняло обычно-спокойное и твердое выражение.",
                        "он вздрогнул  лицо его приняло обычно спокойное и твердое выражение "},
                {"", ""}
        };
    }

    @DataProvider(name = "dataForDeleteWords")
    public static final Object[][] dataForDeleteWords() {
        return new Object[][]{
                {"Then, they heard a loud noise coming from a cupboard in the corner of the room. " +
                        "What’s that?", 4, 't',
                        ",  heard a loud noise coming from a cupboard in the corner of the room. " +
                                "What’s ?"},
                {"— Едет кто-то, — сказал он.\n" +
                        "— Едут двое — офицер и казак.\n" +
                        "Казаки и гусары не все спали: кое-где слышались, " +
                        "вместе с звуком падающих капель и близкого звука " +
                        "жевания лошадей негромкие, как бы шепчущиеся голоса.",
                        6, 'к',
                        "— Едет , — сказал он.\n" +
                                "— Едут двое — офицер и казак.\n" +
                                " и гусары не все спали: кое-где слышались, " +
                                "вместе с звуком падающих  и близкого звука " +
                                "жевания лошадей негромкие, как бы шепчущиеся голоса."},
                {"Yes", 3, 'y', ""},
                {"No, no", 2, 'N', ", "}
        };
    }
}