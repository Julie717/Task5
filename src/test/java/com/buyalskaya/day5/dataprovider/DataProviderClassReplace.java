package com.buyalskaya.day5.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderClassReplace {

    @DataProvider(name = "dataForReplaceSymbol")
    public static Object[][] dataForReplaceSymbol() {
        return new Object[][]{
                {"Shut up, Tara! Hey look, here they come!", '*', 3, "Sh*t up, Ta*a! He* lo*k, he*e th*y co*e!"},
                {"1+1=2", '*', 3, "1+1=2"},
                {"он вздрогнул: лицо его приняло обычно-спокойное и твердое выражение.", 'R', 7,
                        "он вздрогRул: лицо его принялR обычноRспокойное и твердоR выражеRие."}
        };
    }

    @DataProvider(name = "dataForReplaceLetterAfterSuitableLetter")
    public static Object[][] dataForReplaceLetterAfterSuitableLetter() {
        return new Object[][]{
                {"The palice soon tied up the contents of the dead." +
                        "She could pasitively identify the body." +
                        "(She had already reparted her husband as missing.)", 'p', 'a', 'o',
                        "The police soon tied up the contents of the dead." +
                                "She could positively identify the body." +
                                "(She had already reported her husband as missing.)"},
                {"Раман; равный край; прАвИнцИЯ...пра",
                        'р', 'а', 'о',
                        "Роман; ровный крой; прОвИнцИЯ...про"}
        };
    }

    @DataProvider(name = "dataForReplaceWordSuitableLength")
    public static Object[][] dataForReplaceWordSuitableLength() {
        return new Object[][]{
                {"There were doors all round the hall, but they were all locked.", 4, "New substring",
                        "There New substring doors all round the New substring, " +
                                "but New substring New substring all locked."},
                {"Целый день был жаркий, где-то собиралась гроза, " +
                        "но только небольшая тучка брызнула на пыль дороги " +
                        "и на сочные листья", 6, "ДА",
                        "Целый день был ДА, ДА собиралась гроза, " +
                                "но ДА небольшая тучка брызнула на пыль ДА " +
                                "и на ДА ДА"}
        };
    }
}