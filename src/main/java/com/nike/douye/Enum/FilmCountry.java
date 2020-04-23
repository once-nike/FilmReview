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
public enum FilmCountry {

    //中国大陆
    COUNTRY_CHINESE_MAINLAND("中国大陆"),
    //中国香港
    COUNTRY_CHINA_HONGKONG("中国香港"),
    //中国台湾
    COUNTRY_CHINE_TAIWAN("中国台湾"),
    //美国
    COUNTRY_AMERICA("美国"),
    //日本
    COUNTRY_JAPAN("日本"),
    //韩国
    COUNTRY_KPREA("韩国"),
    //英国
    COUNTRY_ENGLAND("英国"),
    //法国
    COUNTRY_FRANCE("法国"),
    //德国
    COUNTRY_GERMANY("德国"),
    //意大利
    COUNTRY_ITALY("意大利"),
    //西班牙
    COUNTRY_SPAIN("西班牙"),
    //印度
    COUNTRY_INDIA("印度"),
    //泰国
    COUNTRY_THAILAND("泰国"),
    //俄罗斯
    COUNTRY_RUSSIA("俄罗斯"),
    //伊朗
    COUNTRY_IRAN("伊朗"),
    //加拿大
    COUNTRY_CANADA("加拿大"),
    //澳大利亚
    COUNTRY_AUSTRALIA("澳大利亚"),
    //爱尔兰
    COUNTRY_IRELAND("爱尔兰"),
    //瑞典
    COUNTRY_SWEDEN("瑞典"),
    //巴西
    COUNTRY_BRAZIL("巴西"),
    //丹麦
    COUNTRY_DENMARK("丹麦");
    @Getter
    @Setter
    private String value;


    @JsonValue
    public String getValue() {
        return value;
    }

    public static List<FilmCountry> stringToEnum(List<String> strs){
        List filmCountry = new ArrayList<FilmCountry>();
        for (String str : strs){
            switch (str){
                case "丹麦":
                    filmCountry.add(COUNTRY_DENMARK);
                    break;
                case "巴西":
                    filmCountry.add(COUNTRY_BRAZIL);
                    break;
                case "瑞典":
                    filmCountry.add(COUNTRY_SWEDEN);
                    break;
                case "爱尔兰":
                    filmCountry.add(COUNTRY_IRELAND);
                    break;
                case "澳大利亚":
                    filmCountry.add(COUNTRY_AUSTRALIA);
                    break;
                case "加拿大":
                    filmCountry.add(COUNTRY_CANADA);
                    break;
                case "伊朗":
                    filmCountry.add(COUNTRY_IRAN);
                    break;
                case "俄罗斯":
                    filmCountry.add(COUNTRY_RUSSIA);
                    break;
                case "泰国":
                    filmCountry.add(COUNTRY_THAILAND);
                    break;
                case "印度":
                    filmCountry.add(COUNTRY_INDIA);
                    break;
                case "西班牙":
                    filmCountry.add(COUNTRY_SPAIN);
                    break;
                case "意大利":
                    filmCountry.add(COUNTRY_ITALY);
                    break;
                case "德国":
                    filmCountry.add(COUNTRY_GERMANY);
                    break;
                case "法国":
                    filmCountry.add(COUNTRY_FRANCE);
                    break;
                case "英国":
                    filmCountry.add(COUNTRY_ENGLAND);
                    break;
                case "韩国":
                    filmCountry.add(COUNTRY_KPREA);
                    break;
                case "日本":
                    filmCountry.add(COUNTRY_JAPAN);
                    break;
                case "美国":
                    filmCountry.add(COUNTRY_AMERICA);
                    break;
                case "中国台湾":
                    filmCountry.add(COUNTRY_CHINE_TAIWAN);
                    break;
                case "中国香港":
                    filmCountry.add(COUNTRY_CHINA_HONGKONG);
                    break;
                case "中国大陆":
                    filmCountry.add(COUNTRY_CHINESE_MAINLAND);
                    break;
                default:
                    break;
            }
        }
        return filmCountry;
    }
}
