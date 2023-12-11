package org.ulearn.analytics.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "studentsCities")
public class StudentCityEntity {
    public static final String NAME_COLUMN = "name";
    public static final String SURNAME_COLUMN = "surname";
    public static final String GROUP_COLUMN = "group";
    public static final String CITY_COLUMN = "city";

    @DatabaseField(generatedId = true)
    private long studentId;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField()
    private String surname;

    @DatabaseField(canBeNull = false)
    private String group;

    @DatabaseField(canBeNull = true)
    private String city;

    public StudentCityEntity(){}

    public StudentCityEntity(String name, String surname, String group, String city){
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.city = city;
    }

    @Override
    public String toString(){
        return String.format("StudentCityEntity{%nname=%s%nsurname=%s%ngroup=%s%ncity=%s%n}%n",
                name, surname, group, city);
    }
}
