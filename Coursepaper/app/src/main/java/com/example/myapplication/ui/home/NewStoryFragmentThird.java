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
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentNewHomeThirdBinding;

public class NewStoryFragmentThird extends Fragment {

    private FragmentNewHomeThirdBinding binding;

    private TextView textViewAdditionalData, textViewExtraData;


    private Button buttonPrevious;
    private Button buttonNext;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewHomeThirdBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textViewAdditionalData = binding.textViewAdditionalData;
        textViewExtraData = binding.textViewExtraData;
        buttonPrevious = binding.buttonPrevious;
        buttonNext = binding.buttonNext;

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNewHomeFragmentFourth();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNewHomeFragmentFifth();
            }
        });
        setToolbarTitle("Выбор вариантов");
        setHasOptionsMenu(true);
        return root;
    }

    private void setToolbarTitle(String title) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(title);
        }
    }
    private void navigateToNewHomeFragmentFourth() {
        // Навигация к NewStoryFragmentFourth
        NavHostFragment.findNavController(this).navigate(R.id.action_newHomeFragmentThird_to_newHomeFragmentFourth);
    }

    private void navigateToNewHomeFragmentFifth() {
        // Навигация к NewStoryFragmentFifth
        NavHostFragment.findNavController(this).navigate(R.id.action_newHomeFragmentThird_to_newHomeFragmentFifth);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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
