package tab.com.au.codetest.races;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;
import tab.com.au.codetest.R;
import tab.com.au.codetest.RaceUtils;
import tab.com.au.codetest.data.Race;

/**
 * View holder for a single list item in the Race list.
 */
public class RaceListItemViewHolder extends RecyclerView.ViewHolder {

	private static final String LOG_TAG = RaceListItemViewHolder.class.getSimpleName();

	@BindView(R.id.tv_race_name)
	TextView raceNameTextView;

	@BindView(R.id.tv_race_time)
	TextView raceTimeTextView;

	@BindView(R.id.iv_race_thumbnail)
	ImageView raceImageView;

	public RaceListItemViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void bind(Race race) {
		// Title = "Race Name (race number)"
		String formattedTitle = raceNameTextView.getContext().getString(R.string.race_name_number,
				race.getRaceName(), race.getNumber());
		raceNameTextView.setText(RaceUtils.capitalize(formattedTitle));

		// Race time like "7:08 AM"
		DateTime startDateTime = DateTime.parse(race.getRaceStartTime().toString());
		DateTimeFormatter formatter = DateTimeFormat.shortTime();
		String str = formatter.print(startDateTime);
		raceTimeTextView.setText(str);

		int raceTimeColor = 0;

		// Race starts inside an hour from now = red text. Otherwise grey text.
		// Note we ignore time zone information as it seems all race start times come
		// back from the server with time zone "Z".
		Log.d(LOG_TAG, "Datetime.now()=" + DateTime.now() + ", Datetime.now().toLocalDateTime()=" +
				DateTime.now().toLocalDateTime());
		Log.d(LOG_TAG, "startDateTime=" + startDateTime + ", startDatetime.toLocalDateTime()=" + startDateTime
				.toLocalDateTime());
		if (RaceUtils.isWithinAnHourBefore(DateTime.now().toLocalDateTime(),
				startDateTime.toLocalDateTime())) {
			raceTimeColor = ContextCompat.getColor(raceTimeTextView.getContext(),
					R.color.colorRaceTimeSoon);
		} else {
			raceTimeColor = ContextCompat.getColor(raceTimeTextView.getContext(),
					R.color.colorRaceTimeNotSoon);
		}

		raceTimeTextView.setTextColor(raceTimeColor);

		// Icon indicates race type
		switch (race.getMeeting().getRaceType()) {
			case HARNESS:
				raceImageView.setImageResource(R.mipmap.ic_harness);
				break;

			case GREYHOUND:
				raceImageView.setImageResource(R.mipmap.ic_greyhound);
				break;

			case THOROUGHBRED:
				raceImageView.setImageResource(R.mipmap.ic_horse);
				break;

			case UNKNOWN:
				raceImageView.setImageResource(0);
				break;
		}
	}
}
