package com.example.myapplication.ui.home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentNewHomeFirstBinding;

public class NewStoryFragmentFirst extends Fragment {

    private FragmentNewHomeFirstBinding binding;
    private NewStoryViewModelFirst newHomeViewModelFirst;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewHomeFirstBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        newHomeViewModelFirst = new ViewModelProvider(this).get(NewStoryViewModelFirst.class);
        //TextView textView = binding.textNewHomeFirst;
        final TextView textView = binding.maintext;
        newHomeViewModelFirst.getText().observe(getViewLifecycleOwner(), textView::setText);


        Button button = binding.buttonK1;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNewHomeFragmentSecond();
            }
        });


        setToolbarTitle("Разговор");
        setHasOptionsMenu(true);
        return root;
    }

    private void setToolbarTitle(String title) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(title);
        }
    }
    private void navigateToNewHomeFragmentSecond() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.action_newHomeFragmentFirst_to_newHomeFragmentSecond);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Обработка нажатия на стрелочку назад
            requireActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
