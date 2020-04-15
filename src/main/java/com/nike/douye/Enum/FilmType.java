package com.nike.douye.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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
    @Getter
    @Setter
    private String value;


    @JsonValue
    public String getValue() {
        return value;
    }

    public static List<FilmType> StringToEnum(List<String> strs){
        List filmTypes = new ArrayList<FilmType>();
        for (String str :strs){
            switch (str){
                case "剧情" :
                    filmTypes.add(TYPE_PLOT);
                    break;
                case "喜剧" :
                    filmTypes.add(TYPE_COMEDY);
                    break;
                case "动作" :
                    filmTypes.add(TYPE_ACTION);
                    break;
                case "爱情" :
                    filmTypes.add(TYPE_LOVE);
                    break;
                case "科幻" :
                    filmTypes.add(TYPE_SCIENCE_FICTION);
                    break;
                case "动画" :
                    filmTypes.add(TYPE_ANIMATION);
                    break;
                case "悬疑" :
                    filmTypes.add(TYPE_SUSPENSE);
                    break;
                case "惊悚" :
                    filmTypes.add(TYPE_THRILLER);
                    break;
                case "恐怖" :
                    filmTypes.add(TYPE_TERROR);
                    break;
                case "犯罪" :
                    filmTypes.add(TYPE_CRIME);
                    break;
                case "同性" :
                    filmTypes.add(TYPE_HOMOSEXUAL);
                    break;
                case "音乐" :
                    filmTypes.add(TYPE_MUSIC);
                    break;
                case "歌舞" :
                    filmTypes.add(TYPE_SONGANDDANCE);
                    break;
                case "传记" :
                    filmTypes.add(TYPE_BIOGRAPHY);
                    break;
                case "历史" :
                    filmTypes.add(TYPE_HISTORY);
                    break;
                case "战争" :
                    filmTypes.add(TYPE_WARFARE);
                    break;
                case "西部" :
                    filmTypes.add(TYPE_WEST);
                    break;
                case "奇幻" :
                    filmTypes.add(TYPE_FANTASY);
                    break;
                case "灾难" :
                    filmTypes.add(TYPE_DISASTER);
                    break;
                case "冒险" :
                    filmTypes.add(TYPE_ADVENTURE);
                    break;
                case "武侠" :
                    filmTypes.add(TYPE_KNIGHT_ERRANT);
                    break;
                case "情色" :
                    filmTypes.add(TYPE_EROTIC);
                    break;
                default:
                    break;
            }
        }
        return filmTypes;
    }
}
