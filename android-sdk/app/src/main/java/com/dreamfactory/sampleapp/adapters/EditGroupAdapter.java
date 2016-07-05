package com.dreamfactory.sampleapp.adapters;

import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.GroupRecord;

import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.activities.BaseActivity;


import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditGroupAdapter extends CreateGroupAdapter {

    protected GroupRecord record;

    protected BitSet inGroupSet;

    public EditGroupAdapter(final BaseActivity activity, List<ContactRecord> records, GroupRecord record) {
        super(activity, records);

        this.record = record;

        inGroupSet = new BitSet(selectedSet.size());

        final ContactGroupService contactGroupService = DreamFactoryAPI.getInstance().getService(ContactGroupService.class);

        contactGroupService.getGroupContacts("contact_group_id=" + record.getId()).enqueue(new Callback<Resource<ContactsRelationalRecord>>() {
            @Override
            public void onResponse(Call<Resource<ContactsRelationalRecord>> call, Response<Resource<ContactsRelationalRecord>> response) {
                if(response.isSuccessful()) {
                    List<ContactRecord> contactRecords = new ArrayList<>();
                    for(ContactsRelationalRecord record : response.body().getResource()){
                        contactRecords.add(record.getContact());
                    }

                    // sort so we can find these guys in the big contacts list in ~ linear time
                    Collections.sort(contactRecords, new SortByLastName());

                    int j = 0;
                    for(int i = 0; i < mRecordsList.size() && j < contactRecords.size(); i++){
                        if(mRecordsList.get(i).getId() == contactRecords.get(j).getId()){
                            // mark the contacts already in the group
                            // use inGroupSet so we can tell how things changed later
                            inGroupSet.set(i);
                            j++;
                        }
                    }

                    selectedSet.or(inGroupSet);
                    notifyDataSetChanged();
                } else {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                }
            }

            @Override
            public void onFailure(Call<Resource<ContactsRelationalRecord>> call, Throwable t) {
                activity.showError("Error while updating contact info.", t);
            }
        });
    }

    @Override
    public List<Long> getSelectedContacts() {
        // deselect the contacts already in group first
        BitSet tmp = selectedSet.get(0, selectedSet.size());
        selectedSet.andNot(inGroupSet);
        // remove the selected contacts from the in group set
        inGroupSet.andNot(tmp);

        return super.getSelectedContacts();
    }

    public List<Long> getContactsToRemove() {
        // called by groupActivity to delete contacts
        selectedSet = inGroupSet;
        return super.getSelectedContacts();
    }

    public boolean didGroupChange(){
        // checked if the group members changed
        if(inGroupSet.cardinality() != selectedSet.cardinality()){
            return true;
        }
        compareSet.clear();
        compareSet.or(inGroupSet);
        compareSet.and(selectedSet);

        // true if a contact is in the group set but not selected
        return compareSet.cardinality() != inGroupSet.cardinality();
    }
}
