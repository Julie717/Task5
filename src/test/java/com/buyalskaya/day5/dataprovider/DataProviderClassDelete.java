package com.buyalskaya.day5.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderClassDelete {
    @DataProvider(name = "dataForDeleteNotLetterExceptSpace")
    public static Object[][] dataForDeleteNotLetterExceptSpace() {
        return new Object[][]{
                {"Shut up, Tara! Hey look, here they come!", "Shut up  Tara  Hey look  here they come "},
                {"1+1=2", "     "},
                {"он вздрогнул: лицо его приняло обычно-спокойное и твердое выражение.",
                        "он вздрогнул  лицо его приняло обычно спокойное и твердое выражение "},
                {"", ""}
        };
    }

    @DataProvider(name = "dataForDeleteWordsStartedConsonant")
    public static Object[][] dataForDeleteWordsStartedConsonant() {
        return new Object[][]{
                {"Then, they heard a loud noise coming from a cupboard in the corner of the room. " +
                        "What's that?", 4,
                        ",  heard a  noise coming  a cupboard in the corner of the . " +
                                "'s ?"},
                {"— Едет кто-то, — сказал он.\n" +
                        "— Едут двое — офицер и казак.\n" +
                        "Казаки и гусары не все спали: кое-где слышались, " +
                        "вместе с звуком падающих капель и близкого звука " +
                        "жевания лошадей негромкие, как бы шепчущиеся голоса.", 6,
                        "— Едет , —  он.\n" +
                                "— Едут двое — офицер и казак.\n" +
                                " и  не все спали: кое-где слышались, " +
                                " с  падающих  и близкого звука " +
                                "жевания лошадей негромкие, как бы шепчущиеся ."},
                {"Yes", 3, "Yes"},
                {"No, no", 2, ", "},
                {"World", 5, ""}
        };
    }
}