package comchaowangcanada.httpsgithub.SwipeMenu_on_NavigationDrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        Stack<String> historyStack = new Stack<String>();
        for (int i=0 ; i< 16; i++){
            historyStack.push("history" + Integer.toString(i));
        }

        ArrayList<String> historyList = new ArrayList<String>();
        for (int i =0 ; i<16;  i++){
            historyList.add(i, historyStack.pop());
        }

        ListAdapter historyAdapter =new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, historyList);
        ListView historyListview = (ListView) rootView.findViewById(R.id.ListView_history);
        historyListview.setAdapter(historyAdapter);

        historyListview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String str = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_LONG).show();
                    }
                }
        );
        // Inflate the layout for this fragment
        return rootView;
    }

}
