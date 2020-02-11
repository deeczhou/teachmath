package chinese;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Optional;

public class PinyinGen {

    public static void main(String[] args) {
        //System.out.println("我 " + toPinyin("我").get());

    }

    public static Optional<String> toPinyin(String chineseChar) {
        String pinyin = null;
        try {
            HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
            outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

            pinyin = PinyinHelper.toHanYuPinyinString(chineseChar, outputFormat, null, true);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return Optional.ofNullable(pinyin);
    }
}
