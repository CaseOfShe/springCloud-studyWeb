package com.mrone.commons;


/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-09-14 19:24
 **/
public enum Verse {
    VERSE1("言者无罪， 闻者足戒。——诗经大序"),
    VERSE2("铁肩担道义， 妙手著文章。——杨继盛"),
    VERSE3("燕雀安知鸿鹄之志哉。——史记陈涉世家"),
    VERSE4("天下兴亡， 匹夫有责。——顾炎武日知录"),
    VERSE5("投我以桃， 报之以李。——诗经大雅抑"),
    VERSE6("血沃中原肥劲草， 寒凝大地发春华。——鲁迅"),
    VERSE7("人学始知道， 不学非自然。——孟郊《劝学》"),
    VERSE8("新松恨不高千尺， 恶竹应须斩万竿。——杜甫"),
    VERSE9("天作孽， 犹可违， 自作孽， 不可活。——尚书"),
    VERSE10("传屐朝寻药， 分灯夜读书。——于鹄《题邻居》"),
    VERSE11("月上柳梢头， 人约黄昏后。——朱淑真生查子"),
    VERSE12("循序而渐进， 熟读而精思。——朱熹读书之要"),
    VERSE13("闲门向山路， 深柳读书堂。——刘昚虚《阙题》"),
    VERSE14("桐花万里丹山路， 雏凤清于老凤声。——李商隐"),
    VERSE15("由俭入奢易， 由奢入俭难。——司马光训俭示康"),
    VERSE16("只要功夫深， 铁杵磨成针。——虞韶《日记故事》"),
    VERSE17("天时不如地利， 地利不如人和。——孟子公孙丑"),
    VERSE18("立身以立学为先，立学以读书为本。——欧阳修"),
    VERSE19("常日好读书， 晚年学垂纶。——高適《答侯少府》"),
    VERSE20("欲穷千里目， 更上一层楼。——王之涣《登鹳雀楼》"),
    VERSE21("志士不饮盗泉之水， 廉者不受嗟来之食。——后汉书"),
    VERSE22("旧书不厌百回读，熟读精思子自知。————苏轼"),
    VERSE23("及时当勉励，岁月不待人。 --陶渊明"),
    VERSE24("长风破浪会有时，直挂云帆济沧海。--李白"),
    VERSE25("仰天大笑出门去，我辈岂是蓬蒿人。--李白"),
    VERSE26("时人不识凌云木，直待凌云始道高。--杜荀鹤"),
    VERSE27("少年辛苦终身事，莫向光阴惰寸功。--杜牧"),
    VERSE28("不经一番寒彻骨，怎得梅花扑鼻香。--黄檗禅师"),
    VERSE29("苔花如米小，也学牡丹开。--袁枚"),
    VERSE30("窈窕淑女君子好逑。--诗经");

    Verse(String verse) {
        Verse = verse;
    }

    public String getVerse() {
        return Verse;
    }

    public void setVerse(String verse) {
        Verse = verse;
    }

    String Verse;


    public static Verse randomType(Verse[] values){
        return values[(int)(Math.random()*values.length)];
    }

}
