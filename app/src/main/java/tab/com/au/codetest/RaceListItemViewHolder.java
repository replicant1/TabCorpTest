package tab.com.au.codetest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import tab.com.au.codetest.data.Race;

/**
 * View holder for a single list item in the Race list.
 */
public class RaceListItemViewHolder extends RecyclerView.ViewHolder {

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
		raceNameTextView.setText(capitalize(formattedTitle));

		// Race time like "7:08 AM"
		DateTime startDateTime = DateTime.parse(race.getRaceStartTime().toString());
		DateTimeFormatter formatter = DateTimeFormat.shortTime();
		String str = formatter.print(startDateTime);
		raceTimeTextView.setText(str);

		// Icon = race type
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
				break;
		}
	}

	private String capitalize(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, " ");
		StringBuffer result = new StringBuffer();

		while (tokenizer.hasMoreTokens()) {
			result.append(firstLetterToUpperCase(tokenizer.nextToken()));
			result.append(" ");
		}

		return result.toString().trim();
	}

	private String firstLetterToUpperCase(String word) {
		Character firstLetterUpperCase = Character.toUpperCase(word.charAt(0));
		String remainderLowerCase = word.subSequence(1, word.length()).toString().toLowerCase();
		return firstLetterUpperCase + remainderLowerCase;
	}
}
