package gcode.drugtracker;

import com.orm.SugarRecord;

import org.joda.time.DateTime;

import java.sql.Time;

public class DrugDoseTime extends SugarRecord {
    int dayOfWeek;
    DateTime doseTime;

    Drug drug;

    public DrugDoseTime() {
    }

    public DrugDoseTime(int dayOfWeek, DateTime doseTime, Drug drug) {
        this.dayOfWeek = dayOfWeek;
        this.doseTime = doseTime;
        this.drug = drug;
    }
}
