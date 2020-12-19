package com.elevatelab.ontimepro.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.elevatelab.ontimepro.Fragments.IndividualSignUpFragment;
import com.elevatelab.ontimepro.Fragments.OrganizationSignUpFragment;

public class SignUpFragmentStateAdapter extends FragmentStateAdapter {

    public SignUpFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new OrganizationSignUpFragment();
        } else {
            return new IndividualSignUpFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
