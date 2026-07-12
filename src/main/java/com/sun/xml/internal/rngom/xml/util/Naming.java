package com.sun.xml.internal.rngom.xml.util;

/* loaded from: target.jar:com/sun/xml/internal/rngom/xml/util/Naming.class */
public class Naming {
    private static final int CT_NAME = 1;
    private static final int CT_NMSTRT = 2;
    private static final String nameStartSingles = ":_ΆΌϚϜϞϠՙەऽলਫ਼ઍઽૠଽஜೞะຄຊຍລວະຽᄀᄉᄼᄾᅀᅌᅎᅐᅙᅣᅥᅧᅩᅵᆞᆨᆫᆺᇫᇰᇹὙὛὝιΩ℮〇";
    private static final String nameStartRanges = "AZazÀÖØöøÿĀıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁΈΊΎΡΣώϐϖϢϳЁЌЎяёќўҁҐӄӇӈӋӌӐӫӮӵӸӹԱՖաֆאתװײءغفيٱڷںھۀێېۓۥۦअहक़ॡঅঌএঐওনপরশহড়ঢ়য়ৡৰৱਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹਖ਼ੜੲੴઅઋએઑઓનપરલળવહଅଌଏଐଓନପରଲଳଶହଡ଼ଢ଼ୟୡஅஊஎஐஒகஙசஞடணதநபமவஷஹఅఌఎఐఒనపళవహౠౡಅಌಎಐಒನಪಳವಹೠೡഅഌഎഐഒനപഹൠൡกฮาำเๅກຂງຈດທນຟມຣສຫອຮາຳເໄཀཇཉཀྵႠჅაჶᄂᄃᄅᄇᄋᄌᄎᄒᅔᅕᅟᅡᅭᅮᅲᅳᆮᆯᆷᆸᆼᇂḀẛẠỹἀἕἘἝἠὅὈὍὐὗὟώᾀᾴᾶᾼῂῄῆῌῐΐῖΊῠῬῲῴῶῼKÅↀↂぁゔァヺㄅㄬ가힣一龥〡〩";
    private static final String nameSingles = "-.़়्ֿٰׄািৗਂ਼ਾਿ઼଼ௗൗัັ༹༵༷༾༿ྗྐྵ゙゚⃡·ːˑ·ـๆໆ々";
    private static final String nameRanges = "ֹֻֽׁׂًْ֑֣̀҃҆֡ۖۜ͠͡ͅ\u06dd۪ۭ۟۠ۤۧۨँःाौ॑॔ॢॣঁঃীৄেৈো্ৢৣੀੂੇੈੋ੍ੰੱઁઃાૅેૉો્ଁଃାୃେୈୋ୍ୖୗஂஃாூெைொ்ఁఃాౄెైొ్ౕౖಂಃಾೄೆೈೊ್ೕೖംഃാൃെൈൊ്ิฺ็๎ິູົຼ່ໍ྄ཱ༘༙྆ྋྐྕྙྭྱྷ〪〯⃐⃜09٠٩۰۹०९০৯੦੯૦૯୦୯௧௯౦౯೦೯൦൯๐๙໐໙༠༩〱〵ゝゞーヾ";
    private static final byte[][] charTypeTable = new byte[256];

    private Naming() {
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [byte[], byte[][]] */
    static {
        for (int i = 0; i < nameSingles.length(); i++) {
            setCharType(nameSingles.charAt(i), 1);
        }
        for (int i2 = 0; i2 < nameRanges.length(); i2 += 2) {
            setCharType(nameRanges.charAt(i2), nameRanges.charAt(i2 + 1), 1);
        }
        for (int i3 = 0; i3 < nameStartSingles.length(); i3++) {
            setCharType(nameStartSingles.charAt(i3), 2);
        }
        for (int i4 = 0; i4 < nameStartRanges.length(); i4 += 2) {
            setCharType(nameStartRanges.charAt(i4), nameStartRanges.charAt(i4 + 1), 2);
        }
        byte[] other = new byte[256];
        for (int i5 = 0; i5 < 256; i5++) {
            if (charTypeTable[i5] == null) {
                charTypeTable[i5] = other;
            }
        }
    }

    private static void setCharType(char c, int type) {
        int hi = c >> '\b';
        if (charTypeTable[hi] == null) {
            charTypeTable[hi] = new byte[256];
        }
        charTypeTable[hi][c & 255] = (byte) type;
    }

    private static void setCharType(char min, char max, int type) {
        char c;
        byte[] shared = null;
        do {
            if ((min & 255) == 0) {
                while (min + 255 <= max) {
                    if (shared == null) {
                        shared = new byte[256];
                        for (int i = 0; i < 256; i++) {
                            shared[i] = (byte) type;
                        }
                    }
                    charTypeTable[min >> '\b'] = shared;
                    if (min + 255 != max) {
                        min = (char) (min + 256);
                    } else {
                        return;
                    }
                }
            }
            setCharType(min, type);
            c = min;
            min = (char) (min + 1);
        } while (c != max);
    }

    private static boolean isNameStartChar(char c) {
        return charTypeTable[c >> '\b'][c & 255] == 2;
    }

    private static boolean isNameStartCharNs(char c) {
        return isNameStartChar(c) && c != ':';
    }

    private static boolean isNameChar(char c) {
        return charTypeTable[c >> '\b'][c & 255] != 0;
    }

    private static boolean isNameCharNs(char c) {
        return isNameChar(c) && c != ':';
    }

    public static boolean isName(String s) {
        int len = s.length();
        if (len == 0 || !isNameStartChar(s.charAt(0))) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (!isNameChar(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNmtoken(String s) {
        int len = s.length();
        if (len == 0) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (!isNameChar(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNcname(String s) {
        int len = s.length();
        if (len == 0 || !isNameStartCharNs(s.charAt(0))) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (!isNameCharNs(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isQname(String s) {
        int len = s.length();
        if (len == 0 || !isNameStartCharNs(s.charAt(0))) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (!isNameChar(c)) {
                if (c != ':') {
                    return false;
                }
                int i2 = i + 1;
                if (i2 < len && isNameStartCharNs(s.charAt(i2))) {
                    do {
                        i2++;
                        if (i2 >= len) {
                            return true;
                        }
                    } while (isNameCharNs(s.charAt(i2)));
                    return false;
                }
                return false;
            }
        }
        return true;
    }
}
