package com.example.ssenjoymusicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ssenjoymusicapp.Activity.DanhSachBaiHatActivity;
import com.example.ssenjoymusicapp.Activity.DanhSachPLaylistActivity;
import com.example.ssenjoymusicapp.Adapter.PlaylistTodayAdapter;
import com.example.ssenjoymusicapp.Model.Playlist;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lvPlaylist;
    TextView  txtViewTilePlaylist, txtViewXemThemPlaylist;
    PlaylistTodayAdapter playlistAdapter;
    ArrayList<Playlist> arrayPlaylistToday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        lvPlaylist =  view.findViewById(R.id.listViewPlaylist);
        txtViewTilePlaylist = view.findViewById(R.id.textviewtitleplaylist);
        txtViewXemThemPlaylist = view.findViewById(R.id.textViewViewMorePlaylist);


        getData();
        txtViewXemThemPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachPLaylistActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return  view;
    }

    private void getData() {
        //Khởi tạo retrofit và gửi cấu hình retrofit cách tương tác dữ liệu.
        DataService dataService = APIService.getService();
        //Gọi lại function call.
        Call<List<Playlist>> callback = dataService.GetDataPlaylistToday();
        //Sự kiện lắng nghe của dữ liệu trả về.
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                //Lấy dữ liệu ra.
                arrayPlaylistToday= (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistTodayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayPlaylistToday);
                lvPlaylist.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(lvPlaylist);
                lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                        intent.putExtra("itemplaylist", arrayPlaylistToday.get(i));
                        getActivity().startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
    //Fit lại chiều cao của fragment (mã nguồn trên mạng)
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
