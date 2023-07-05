package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.JurnalListViewModel;

public class JurnalListFragment extends Fragment {


    private static final String TAG = "JurnalListFragment";
    private JurnalListViewModel mJurnalListViewModel;
    private RecyclerView mJurnalRecyclerView;
    private JurnalAdapter mAdapter;
    private List<Jurnal> mJurnals;

    public interface Callbacks {
        public void onJurnalSelected(int jurnalId);
    }

    private Callbacks mCallbacks = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach called");
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach called");
        mCallbacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "JurnalListFragment.onCreate() called");
        setHasOptionsMenu(true);
        mJurnalListViewModel = new ViewModelProvider(this)
                .get(JurnalListViewModel.class);
        mAdapter = new JurnalAdapter(Collections.<Jurnal>emptyList());
        //Log.d(TAG, "Total Jurnal: " + mJurnalListViewModel.getJurnals().size());
    }

    private void updateUI(List<Jurnal> jurnals){
        //List<User> users = mUserListViewModel.getUsers();
        Log.i(TAG, "updateUI called");
        mAdapter = new JurnalAdapter(jurnals);
        mJurnalRecyclerView.setAdapter(mAdapter);
        mJurnals = jurnals;
    }

    public static JurnalListFragment newInstance(){
        return new JurnalListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "UserListFragment.onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_jurnal_list,
                container, false);
        mJurnalRecyclerView = (RecyclerView)
                view.findViewById(R.id.jurnal_recycler_view);
        mJurnalRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        //updateUI();
        mJurnalRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "UserListFragment.onViewCreated() called");
        mJurnalListViewModel.getJurnals().observe(
                getViewLifecycleOwner(),
                new Observer<List<Jurnal>>() {
                    @Override
                    public void onChanged(List<Jurnal> jurnals) {
                        //Update the cached copy of
                        updateUI(jurnals);
                        Log.i(TAG, "Got Jurnals: " + jurnals.size());
                    }
                }
        );
    }

    private class JurnalHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mNamaTextView;

        private TextView mTanggalTextView;
        private Jurnal mJurnal;

        public JurnalHolder (LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_jurnal_item, parent, false));
            this.itemView.setOnClickListener(this);
            //buat ubah
            mNamaTextView = this.itemView.findViewById(R.id.nama_jurnal);
            mTanggalTextView = this.itemView.findViewById(R.id.konten_jurnal);



        }

        public void bind(Jurnal jurnal){
            mJurnal = jurnal;
            mTanggalTextView.setText(String.valueOf(mJurnal.getTanggal_jurnal()));
            mNamaTextView.setText(String.valueOf(mJurnal.getNama_jurnal()));
        }

        @Override
        public void onClick(View v) {


            Toast.makeText(getActivity(),
                    mJurnal.getNama_jurnal() + " clicked!",
                    Toast.LENGTH_SHORT)
                    .show();
            mCallbacks.onJurnalSelected(mJurnal.getId_jurnal());
        }
    }

    private class JurnalAdapter extends RecyclerView.Adapter<JurnalHolder>{

        private List<Jurnal> mJurnalList;

        public JurnalAdapter(List<Jurnal> jurnals){
            mJurnalList = jurnals;
        }

        @Override
        public JurnalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
            return new JurnalHolder(layoutinflater, parent);
        }

        @Override
        public void onBindViewHolder(JurnalHolder holder, int position) {
            Jurnal jurnal = mJurnalList.get(position);
            holder.bind(jurnal);
        }

        @Override
        public int getItemCount() {
            return mJurnalList.size();
        }


    }
    
}
