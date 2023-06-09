package com.example.myapplication.ui.dashboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentNewDashboard1Binding;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.User;
import com.example.myapplication.db.UserDao;

import java.util.List;

public class NewPerson1Fragment extends Fragment {

    private FragmentNewDashboard1Binding binding;
    private NewPerson1ViewModel newDashboard1ViewModel;
    private EditText editTextName, editTextAge, editTextDescription;
    private UserDao userDao;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newDashboard1ViewModel = new ViewModelProvider(this).get(NewPerson1ViewModel.class);

        binding = FragmentNewDashboard1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNewDashboard1;
        newDashboard1ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        editTextName = binding.editTextName;
        editTextAge = binding.editTextAge;
        editTextDescription = binding.editTextDescription;

        Button buttonSave = binding.buttonSave;
        Button buttonLoad = binding.buttonLoad;
        Button buttonClear = binding.buttonClear;

        userDao = AppDatabase.getInstance(getContext()).userDao();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                String description = editTextDescription.getText().toString();

                User user = new User();
                user.name = name;
                user.age = age;
                user.description = description;

                new InsertUserTask().execute(user);
            }
        });

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadUserTask().execute();
            }
        });


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ClearDatabaseTask().execute();
            }
        });


        setHasOptionsMenu(true);
        setToolbarTitle("Редактор персонажа");
        return root;
    }

    private void setToolbarTitle(String title) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        //AppDatabase.getInstance(getContext()).close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // AppDatabase.getInstance(getContext()).close(); // Удалите эту строку
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

    private class InsertUserTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private class LoadUserTask extends AsyncTask<Void, Void, User> {
        @Override
        protected User doInBackground(Void... voids) {
            List<User> users = userDao.getAllUsers();
            if (!users.isEmpty()) {
                return users.get(0); // Загружаем только первого пользователя из списка
            }
            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            if (user != null) {
                editTextName.setText(user.name);
                editTextAge.setText(String.valueOf(user.age));
                editTextDescription.setText(user.description);
            }
        }
    }

    private class ClearDatabaseTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            List<User> users = userDao.getAllUsers();
            if (users.size() == 1) {
                userDao.delete(users.get(0));
            } else {
                userDao.deleteAll();
            }
            return null;
        }
    }

}
