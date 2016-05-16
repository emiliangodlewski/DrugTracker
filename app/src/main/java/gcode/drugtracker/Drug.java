package gcode.drugtracker;

import com.orm.SugarRecord;

import java.util.List;

public class Drug extends SugarRecord {
    String name;
    String description;

    List<DrugDoseTime> getDoses() {
        return DrugDoseTime.find(DrugDoseTime.class, "drug = ?", getId().toString());
    }

    public Drug() {
    }

    public Drug(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
