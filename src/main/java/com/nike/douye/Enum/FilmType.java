package com.nike.douye.Enum;

public enum FilmType {
    //剧情
    TYPE_PLOT("剧情"),
    //喜剧
    TYPE_COMEDY("喜剧"),
    //动作
    TYPE_ACTION("动作"),
    //爱情
    TYPE_LOVE("爱情"),
    //科幻
    TYPE_SCIENCE_FICTION("科幻"),
    //动画
    TYPE_ANIMATION("动画"),
    //悬疑
    TYPE_SUSPENSE("悬疑"),
    //惊悚
    TYPE_THRILLER("惊悚"),
    //恐怖
    TYPE_TERROR("恐怖"),
    //犯罪
    TYPE_CRIME("犯罪"),
    //同性
    TYPE_HOMOSEXUAL("同性"),
    //音乐
    TYPE_MUSIC("音乐"),
    //歌舞
    TYPE_SONGANDDANCE("歌舞"),
    //传记
    TYPE_BIOGRAPHY("传记"),
    //历史
    TYPE_HISTORY("历史"),
    //战争
    TYPE_WARFARE("战争"),
    //西部
    TYPE_WEST("西部"),
    //奇幻
    TYPE_FANTASY("奇幻"),
    //灾难
    TYPE_DISASTER("灾难"),
    //冒险
    TYPE_ADVENTURE("冒险"),
    //武侠
    TYPE_KNIGHT_ERRANT("武侠"),
    //情色
    TYPE_EROTIC("情色");
    private final String value;


    public String getValue() {
        return value;
    }

    FilmType(String value) {
        this.value = value;
    }
}
