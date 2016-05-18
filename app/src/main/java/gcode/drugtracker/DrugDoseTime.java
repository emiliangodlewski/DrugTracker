package gcode.drugtracker;

import com.orm.SugarRecord;

import org.joda.time.DateTime;

import java.sql.Time;

public class DrugDoseTime extends SugarRecord {
    int dayOfWeek;
    int doseHour;
    int doseMinutes;

    Drug drug;

    public DrugDoseTime() {
    }

    public DrugDoseTime(int dayOfWeek, int doseHour, int doseMinutes, Drug drug) {
        this.dayOfWeek = dayOfWeek;
        this.doseHour = doseHour;
        this.doseMinutes = doseMinutes;
        this.drug = drug;
    }

}
