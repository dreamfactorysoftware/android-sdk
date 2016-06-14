package com.dreamfactory.sampleapp.adapters;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;

import com.dreamfactory.sampleapp.R;

public class DeletableContactListAdapter implements AbsListView.MultiChoiceModeListener {
    private ContactListAdapter adapter;

    public DeletableContactListAdapter(ContactListAdapter adapter) {
        super();
        this.adapter = adapter;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        // set if the item is checked or not
        adapter.set(position, checked);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_contact_list, menu);
        adapter.deselectAll();
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        adapter.removeAllSelected();
        mode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
