package com.example.rootshareapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rootshareapp.model.Local_Location;
import com.example.rootshareapp.model.Local_Route;
import com.example.rootshareapp.room.LocationDataRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class LocationDataViewModel extends AndroidViewModel {

    private LocationDataRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Local_Route>> mLatestRoutes;
    private LiveData<List<Local_Location>> mLatestLocations;
    private Local_Location mSelectedLocation;

    public LocationDataViewModel(Application application) {
        super(application);
        mRepository = new LocationDataRepository(application);
    }


    public LiveData<List<Local_Route>> getLatestRoutes() {
        mLatestRoutes = mRepository.getLatestRoutes();
        return mLatestRoutes;
    }

    public LiveData<List<Local_Location>> getLatestLocations(int route_id) {
        mLatestLocations = mRepository.getLatestLocations(route_id);
        return mLatestLocations;
    }

    public Long insertRoute(Local_Route local_route) throws ExecutionException, InterruptedException {
        return mRepository.insertRoute(local_route);
    }

    public void updateRoute(Local_Route local_route) {
        mRepository.updateRoute(local_route);
    }

    public void deleteRoute(Local_Route local_route) {
        mRepository.deleteRoute(local_route);
    }

    public void deleteAllRoutes(Local_Route local_route) {
        mRepository.deleteAllRoutes(local_route);
    }

    public void insertLocation(Local_Location local_location) {
        mRepository.insertLocation(local_location);
    }

    public void updateLocation(Local_Location local_location) {
        mRepository.updateLocation(local_location);
    }

    public void deleteLocation(Local_Location local_location) {
        mRepository.deleteLocation(local_location);
    }

    public void deleteAllLocations() {
        mRepository.deleteAllLocations();
    }

    public void setSelectedLocation(Local_Location local_location) {
        mSelectedLocation = local_location;
    }

    public Local_Location getSelectedLocation() {
        return mSelectedLocation;
    }

}

//class MyRoutesFragment extends Fragment {
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        LocationDataViewModel model = ViewModelProviders.of(getActivity()).get(LocationDataViewModel.class);
//        model.getSelectedLocation();
////        model.getSelected().observe(this, { item ->
////                // Update the UI.
////        });
//
//    }
//}