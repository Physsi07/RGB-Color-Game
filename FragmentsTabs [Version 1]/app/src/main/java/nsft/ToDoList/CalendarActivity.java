package nsft.ToDoList;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    // XML VARIABLES //
    CalendarView calendarView;
    TextView     textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // INITIALIZING THE XML VARIABLES //
        calendarView = findViewById(R.id.calendar_id);
        textView     = findViewById(R.id.date_id);

        // SETTING THE CALENDAR LISTENER FOR WHEN IS CLICK //
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                // IF A DATE IS CLICK IN THE CALENDAR I WILL BE PUTTING IT IN THE TEXTVIEW //
                String date = (dayOfMonth + "/" + month + "/" + year );
                textView.setText(date);
            }
        });
    }
}
