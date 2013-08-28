package cn.heu.hmp.activity.tel;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
/**
 * A list view example where the 
 * data for the list comes from an array of strings.
 */
public class TelList extends ListActivity {
    private String[] mStrings = {"ÂíÈð", "¹ËµÂÀñ",""};
    private String[] telss = {"15804602865", "15945195877"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
        getListView().setTextFilterEnabled(true);
        getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View listitem, int position,long arg3) {
				startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+telss[position])));
			}
		});
    }   
}
