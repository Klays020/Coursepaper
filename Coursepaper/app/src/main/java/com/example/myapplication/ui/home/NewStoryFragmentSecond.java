package com.example.myapplication.ui.home;

import android.os.AsyncTask;
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
import com.example.myapplication.databinding.FragmentNewHomeSecondBinding;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.User;
import com.example.myapplication.db.UserDao;

import java.util.List;

public class NewStoryFragmentSecond extends Fragment {

    private FragmentNewHomeSecondBinding binding;
    private NewStoryViewModelSecond newHomeViewModelSecond;

    private TextView textViewName, textViewAge, textViewDescription;

    private TextView textViewCombinedData;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewHomeSecondBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textViewName = binding.textViewName;
        textViewAge = binding.textViewAge;
        textViewDescription = binding.textViewDescription;
        textViewCombinedData = binding.textViewCombinedData;
        loadDataFromDatabase();

        newHomeViewModelSecond = new ViewModelProvider(this).get(NewStoryViewModelSecond.class);
        final TextView textView = binding.textNewHomeSecond;
        newHomeViewModelSecond.getText().observe(getViewLifecycleOwner(), textView::setText);

        setHasOptionsMenu(true);
        Button button = binding.buttonNext;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNextFragment();
            }
        });
        setToolbarTitle("Начало истории");
        return root;
    }

    private void setToolbarTitle(String title) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(title);
        }
    }
    private void loadDataFromDatabase() {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... voids) {
                AppDatabase db = AppDatabase.getInstance(getContext());
                UserDao userDao = db.userDao();
                return userDao.getAllUsers();
            }

            @Override
            protected void onPostExecute(List<User> users) {
                if (!users.isEmpty()) {
                    User user = users.get(0);
                    textViewName.setText(user.name);
                    textViewAge.setText(String.valueOf(user.age));
                    textViewDescription.setText(user.description);

                    String additionalText1 = "Маша быстрым шагом направляется домой, поскольку позади себя она увидела странного и очень неприятного мужчину. Приглядевшись, она поняла, что это ее старый знакомый ";
                    String additionalText2 = "На вид ему было";
                    String additionalText3 = "И хотя реальной опасности он не представлял, Машу можно понять, ведь за этим человеком ходят дурные слухи, говорят будто он";
                    String combinedData = additionalText1 + textViewName.getText().toString() + ". " + additionalText2 +
                            " " + textViewAge.getText().toString() + ". " + additionalText3 +
                            " " + textViewDescription.getText().toString() + ".";

                    textViewCombinedData.setText(combinedData);
                }
            }
        }.execute();
    }

    private void navigateToNextFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.action_newHomeFragmentSecond_to_newHomeFragmentThird);
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
