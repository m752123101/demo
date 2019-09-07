package com.example.demo.mapping.bean;

/**
 * city
 *
 * @author hanwen.dong
 * @date 2019/9/3 19:10
 * @Description auto
 */
public class City {
    //  `ID` int(11) NOT NULL AUTO_INCREMENT,
    //  `Name` char(35) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
    //  `CountryCode` char(3) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
    //  `District` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
    //  `Population` int(11) NOT NULL DEFAULT '0',
    //  PRIMARY KEY (`ID`) USING BTREE,
    //  KEY `CountryCode` (`CountryCode`) USING BTREE,
    private Integer id;
    private String name;
    private String countryCode;
    private String district;
    private Integer population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
        StringBuilder bulider = new StringBuilder("City [");
        bulider.append("    id=")
                .append(id);
        bulider.append(",    name=")
                .append(name);
        bulider.append(",    countryCode=")
                .append(countryCode);
        bulider.append(",    district=")
                .append(district);
        bulider.append(",    population=")
                .append(population);
        bulider.append(']');
        return bulider.toString();
    }
}
