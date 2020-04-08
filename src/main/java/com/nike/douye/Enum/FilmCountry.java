package com.nike.douye.Enum;

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
    private final String value;


    public String getValue() {
        return value;
    }

    FilmCountry(String value) {
        this.value = value;
    }
}
