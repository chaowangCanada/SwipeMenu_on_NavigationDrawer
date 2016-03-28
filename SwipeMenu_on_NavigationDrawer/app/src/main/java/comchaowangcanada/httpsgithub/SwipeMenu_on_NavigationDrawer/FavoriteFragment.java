package comchaowangcanada.httpsgithub.SwipeMenu_on_NavigationDrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private  BoundedArrayList<String> favoriteStack;
    private  CustomAdapter favoriteAdapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);


        favoriteStack = new BoundedArrayList<String>(10);
        for (int i = 0; i < 16; i++) {
            favoriteStack.add("favorite" + Integer.toString(i));
        }
        //favoriteAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, favoriteStack);
        favoriteAdapter = new CustomAdapter(getActivity(), favoriteStack);

        SwipeMenuListView favoriteListview = (SwipeMenuListView) rootView.findViewById(R.id.favorite_listView);
        favoriteListview.setAdapter(favoriteAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,
                        0x3F)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.drawable.ic_action_important);
                menu.addMenuItem(item1);

                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                item2.setWidth(dp2px(90));
                item2.setIcon(R.drawable.ic_action_discard);
                menu.addMenuItem(item2);
                }
            };

        favoriteListview.setMenuCreator(creator);

        favoriteListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String item = favoriteStack.get(position);
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
//					delete(item);
                        favoriteStack.remove(position);
                        favoriteAdapter.notifyDataSetChanged();
//                        ((Activity)getContext()).runOnUiThread(new Runnable() {
//                            public void run() {
//                                notifyDataSetChanged();
//                            }
//                        });
                        break;
                }
                return false;
            }
        });

        favoriteListview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String str = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_LONG).show();
                    }
                }
        );

        return rootView;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}