package com.jcaseydev.bart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jcaseydev.bart.Model2.Stations.Station;
import com.jcaseydev.bart.R;
import java.util.List;

public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.CustomViewHolder> {

  private List<Station> stationList;
  private Context context;
  private onStationListener onStationListener;

  public StationListAdapter(Context context, List<Station> stationList,
      onStationListener onStationListener) {
    this.context = context;
    this.stationList = stationList;
    this.onStationListener = onStationListener;
  }

  @NonNull
  @Override
  public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.list_item_station, parent, false);
    return new CustomViewHolder(view, onStationListener);
  }

  @Override
  public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
    String city = stationList.get(position).getCity();
    String zipCode = stationList.get(position).getZipcode();
    holder.stationName.setText(stationList.get(position).getName());
    holder.stationCity.setText(context.getString(R.string.station_location, city, zipCode));
  }

  @Override
  public int getItemCount() {
    return stationList.size();
  }

  class CustomViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    public final View mView;
    TextView stationName;
    TextView stationCity;
    onStationListener onStationListener;

    public CustomViewHolder(@NonNull View itemView, onStationListener onStationListener) {
      super(itemView);

      mView = itemView;
      stationName = mView.findViewById(R.id.station_name);
      stationCity = mView.findViewById(R.id.station_city);
      this.onStationListener = onStationListener;

      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      onStationListener.onStationClick(getAdapterPosition());
    }
  }

  public interface onStationListener {

    void onStationClick(int position);
  }
}
