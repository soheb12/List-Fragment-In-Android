package com.example.skyscraper.listfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener , FragmentManager.OnBackStackChangedListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_list_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.list_array,android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),"Item "+(position+1)+" Clicked" ,Toast.LENGTH_SHORT).show();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        manager.addOnBackStackChangedListener(this);
        transaction.addToBackStack("ITEM " + (position+1));
        transaction.commit();
    }

    @Override
    public void onBackStackChanged() {
        Log.i("back","changed");
        FragmentManager manager = getFragmentManager();
        int pos = manager.getBackStackEntryCount();
        if(pos > 0)
        {
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(pos - 1);

            Toast.makeText(getActivity(),"Current BackStack is : " + entry.getName(),Toast.LENGTH_SHORT).show();
        }

    }
}
